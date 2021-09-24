package com.hackerrank.practice.strings.special;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

/**
 * Class Solution. This class implements a solution to
 * the challenge "Special String Again" published in
 * Hacker Rank here:
 * 
 * https://www.hackerrank.com/challenges/special-palindrome-again
 * 
 * The implementation uses a list that packs the consecutive
 * occurrences of the same character in a single block and
 * the scan the list of blocks to add all substrings for each
 * block plus possible palindromes by using a look-ahead of
 * 1 and 2 blocks.
 */
public class Solution extends DefaultChallengeSolution<Input,Long> {



    public static class Sequence {
        public char c;
        public long f;
        public Sequence next;

        public Sequence(char c, long f) {
            this.c = c;
            this.f = f;
        }
    }

    @Override
    public DefaultChallengeResult<Long> execute(Input input) {
        long value = this.substrCount(input.getN(), input.getText());
        return new DefaultChallengeResult<Long>(value);
    }

    protected long substrCount(int n, String s) { 

        char[] chars = s.toCharArray();
        Sequence head = new Sequence(chars[0], 1);
        Sequence current = head;
        
        for(int i=1; i < n; i++) {
            char c = chars[i];
            if (current.c == c) {
                current.f++;
            } else {
                current.next = new Sequence(c, 1);
                current = current.next;
            }   
        }
        long total = 0;
        Sequence scan = head;
        
        // at this point we have the list of
        // entries we need compressed based
        // on the number of occurrences;
        while(scan != null) {

            // for this sequence we add all the 
            // possible substrings.
            total += scan.f*(scan.f+1)/2;
            
            // we check whether we have a palindrome
            // moving forward and if so we add all the
            // simmetric substrings, which are given
            // by the minimum of the two sides of the 
            // palindrome (left and right)
            if (scan.next != null &&
                scan.next.f == 1 &&
                scan.next.next != null &&
                scan.next.next.c == scan.c) {
                    
                total += Math.min(scan.f, scan.next.next.f);
            }
            scan = scan.next;
        }
        return total;
    }
}