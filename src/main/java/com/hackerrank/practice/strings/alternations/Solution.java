package com.hackerrank.practice.strings.alternations;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

/**
 * Class Solution. This class implements a solution for the 
 * programming challengere "Alternating Characters":
 * 
 * https://www.hackerrank.com/challenges/alternating-characters/problem
 * 
 * The implementation scans the string and determines how
 * many duplicates of the same character occurr consecutively
 * and it sums the up. The string only contains A or B.
 */
public class Solution extends DefaultChallengeSolution<Input,Integer> {

    @Override
    public DefaultChallengeResult<Integer> execute(Input input) {
        int alternations = this.alternatingCharacters(input.getText());
        return new DefaultChallengeResult<>(alternations);
    }

    /*
     * Complete the 'alternatingCharacters' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    protected int alternatingCharacters(String s) {
        // Write your code here
        char leader = s.charAt(0);
        int deletions = 0;
        for(int i=1; i<s.length(); i++) {
            char candidate = s.charAt(i);
            if (leader == candidate) {
                deletions++;
            } else {
                leader = candidate;
            }
        }
        return deletions;
    }
}