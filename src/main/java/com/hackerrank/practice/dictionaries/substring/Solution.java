package com.hackerrank.practice.dictionaries.substring;

import java.util.HashSet;
import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

public class Solution extends DefaultChallengeSolution<Input,String> {


    @Override 
    public DefaultChallengeResult<String> execute(Input input) {

        String answer = this.twoStrings(input.getS1(), input.getS2());
        return new DefaultChallengeResult<>(answer);
    }

    /*
     * Complete the 'twoStrings' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

    protected String twoStrings(String s1, String s2) {
    
        // given that we do not have to check what is the largest substring
        // but rather to see whether there is any overlap, we can focus on
        // whether we have at least one alphabet letter in common. These are
        // only 26, hence we can easily build a hashset with the letters of
        // the first string, and check if any character in the second string
        // has at least one character in the hashset.
        
        
        if (s1.isEmpty() || s2.isEmpty()) {
            return "NO";
        }
        
        HashSet<Character> set = new HashSet<>();
        for(char c : s1.toCharArray()) {
            set.add(c);
        }
        
        for(char c: s2.toCharArray()) {
           if (set.contains(c)) {
               return "YES";
           }
        }
        
        return "NO";
    }
    

}
