package com.hackerrank.practice.strings.anagrams;


import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

/**
 * Class Solution. This class implements a solution for the 
 * programming challengere "Making Anagrams":
 * 
 * https://www.hackerrank.com/challenges/ctci-making-anagrams/problem
 * 
 * The implementation uses a map to keep an histogram of the
 * frequencies of characters. For one string we add values to
 * the frequency count of te characters, for the other string
 * we substract. The minimum number of removals to reduce the
 * two strings to a common anagram is the sum of the absolute
 * values remaining in the histogram.
 */
public class Solution extends DefaultChallengeSolution<Input,Integer> {

    @Override
    public boolean accept(String testCaseId) {
        return true;
    }

    @Override
    public DefaultChallengeResult<Integer> execute(Input input) {
        int anagrams = this.makeAnagram(input.getA(), input.getB());
        return new DefaultChallengeResult<>(anagrams);
    }

    /*
     * Complete the 'makeAnagram' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    protected int makeAnagram(String a, String b) {
        // anagrams are strings that have the exact same occurrences
        // of the letters but in any order. To determine the minimum
        // set of delation from a and b to make an anagram we can
        // simply sort the strings and then count the different 
        // characters.
        int[] histogram = new int[26];
        for(int i=0; i<a.length(); i++) {
            int c = a.charAt(i) - 'a';
            System.out.println("i: " + i  + ", c: " + a.charAt(i) + ", 'c' - 'a': " + c);
            int f = histogram[c];
            histogram[c] = f + 1;
        }
        for(int i=0; i<b.length(); i++) {
            int c = b.charAt(i) - 'a';
            int f = histogram[c];
            histogram[c] = f - 1;
        }
        // now the map contains for each
        // characters the number of deletions
        // from (a) to reach b with positive
        // number, and the number of deletions
        // from (b) to reach a with negative 
        // number.
        
        int deletions = 0;
        
        for(int i=0; i<26; i++) {
            
            deletions += Math.abs(histogram[i]);
        }
        
        return deletions;
            

    }

}
