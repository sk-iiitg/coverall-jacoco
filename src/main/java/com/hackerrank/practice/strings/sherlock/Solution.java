package com.hackerrank.practice.strings.sherlock;

import java.util.Map;
import java.util.Map.Entry;

import com.hackerrank.core.DefaultChallengeResult;

import java.util.Collection;
import java.util.HashMap;

import com.hackerrank.core.DefaultChallengeSolution;

/**
 * Class Solution. This class implements a solution to the programming
 * challenge "Sherlock and the Valid String":
 * 
 * https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem
 * 
 * The implementation distributes the characters of the string into a
 * frequency histogram, by reasoning about the values in the histogram
 * we can derive whether the string is valid. A valid string has all
 * the values of the histogram the same, and there is only one that
 * can differ only by 1 unit (f(c) = 1 is a corner case).
 */
public class Solution extends DefaultChallengeSolution<Input,String> {

    @Override
    public DefaultChallengeResult<String> execute(Input input) {

        String result = this.isValid(input.getText());
        return new DefaultChallengeResult<>(result);
    }


    protected String isValid(String s) {
        
        int n = s.length();
        // for n == 1 it is obvious
        // for n == 2 we can have ab or aa (both valid)
        // for n == 3 we can have aab aba baa abc, (aab, aba, baa) are valid with the 
        //            removal of one character.
        // for n == 4 we can have aabc, abcd, aabb, aaaa the only odd one is aabc which
        //            can be normalised by removing one a.
        if (n < 5) {
            return "YES";
        }
        Map<Character,Integer> frequencies = new HashMap<>();
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            Integer fc = frequencies.get(c);
            fc = fc == null ? 1 : fc+1;
            frequencies.put(c, fc);
        }
    
        // this loop is at most 26 so we don't really
        // make things bad.   
        //
        Collection<Integer> values = frequencies.values();
        int distinct = values.size();   
             
        // this is the banal case where all the characters are
        // the same or all different (n=26). We don't need to
        // check in this case about the distribution because we
        // can guess it.
        if (distinct == 1 || distinct == n) {
            return "YES";
        }
        
        // ok this is the general case
        //
        
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        int[] w = new int[3];
        
        for(int v : values) {
            if (v < min1) {
                min2 = min1;
                w[1] = w[0];
                min1 = v;
                w[0] = 1;
            } else if (v == min1) {
                w[0] = w[0] + 1;
            } else if (v < min2) {
                w[1] = 1;
                min2 = v;
            } else if (v == min2) {
                w[1] = w[1] + 1;
            }
            
            if (v > max) {
                w[2] = 1;
                max = v;
            } else if (v == max) {
                w[2] = w[2] + 1;
            }
        }
        
        for(Entry<Character,Integer> e : frequencies.entrySet()) {
            System.out.println( e.getKey() + ": " + e.getValue());
        }
        System.out.println("min1: " + min1 + ", f: " + w[0]);
        System.out.println("min2: " + min2 + ", f: " + w[1]);
        System.out.println("max: " + max + ", f: " + w[2]);
        
            
        // at the end of this computation we have
        // computed min1, min2, and max, with the
        // corresponding occurrences of these values.
        
        // if min1 == max it follows that all the
        // occurrences are the same, we don't need
        // to check anything else.
        
        // if max - min1 == 1, we are ok if the corresponding weigths
        // are either w[0] = values - 1 && w[2] = 1 
        //         or w[0] = 1 && w[2] = values - 1
        // this means that we can remove one of the two and the 
        // others are all the same.
        
        // if max - min1 > 1, we are ok if:
        // - min1 = 1
        // - max = min2 (all the other elements are max)
        
        boolean allTheSame = (max - min1 == 0);
        boolean differByOne = (max - min1 == 1 && ((w[0] == 1 && w[2] == distinct - 1) || (w[0] == distinct-1 && w[2] == 1)));
        boolean allTheSamePlusOne = (max - min2 == 0 && min1==1 && w[0]==1);
        
        System.out.println("max(" + max + ") - min1(" + min1 +") == 0 : " + allTheSame);
        System.out.println("max(" + max + ") - min1(" + min1 +") == 1 && " + 
                           "((w[0](" + w[0] + ") == 1 && w[2](" + w[2] + ") == " + (distinct - 1) + ") || " +
                           " (w[0](" + w[0] + ") == " + (distinct - 1) + " && w[2](" + w[2] + ") == 1)) : " + differByOne);
        System.out.println("max(" + max + ") - min2(" + min2 + ") == 0 && min1(" + min1 + ") == 1 && w[0](" + w[0] + ") == 1 : " + allTheSamePlusOne);
        
        
        return  allTheSame || differByOne || allTheSamePlusOne ? "YES" : "NO";
        
    }
}