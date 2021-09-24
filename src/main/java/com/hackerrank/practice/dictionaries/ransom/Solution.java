package com.hackerrank.practice.dictionaries.ransom;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

/**
 * This class provides a solution to the "Ransom Note"
 * problem proposed by Hacker Rank here:
 * 
 * https://www.hackerrank.com/challenges/ctci-ransom-note/problem
 */
public class Solution extends DefaultChallengeSolution<Input,String> {


    @Override
    public DefaultChallengeResult<String> execute(Input input) {

        String answer = this.checkMagazine(input.getMagazine(), input.getNote());
        return new DefaultChallengeResult<>(answer);
    }
    /*
     * Complete the 'checkMagazine' function below.
     *
     * The function accepts following parameters:
     *  1. STRING_ARRAY magazine
     *  2. STRING_ARRAY note
     */

    protected String checkMagazine(List<String> magazine, List<String> note) {

        if (note.size() > magazine.size()) {
            return "No";
        }

        Map<String,Integer> bucket = new HashMap<>();
        for(String word : magazine) {
            Integer count = bucket.get(word);
            if (count == null) {
                bucket.put(word, 1);
            } else {
                bucket.put(word, count + 1);
            }
        }
        for(String wordNote : note) {
            Integer count = bucket.get(wordNote);
            if (count == null) {
                return "No";
            }
            int left = count.intValue() - 1;
            if (left == 0) {
                bucket.remove(wordNote);
            } else {
                bucket.put(wordNote, left);
            }
        }
       return "Yes";
    }
}