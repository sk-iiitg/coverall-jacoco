package com.hackerrank.practice.strings.anagrams;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.DefaultChallengeResult;
import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.TestCase;

import java.io.BufferedReader;
import java.io.IOException;
import org.junit.Test;


public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Integer>> {

    @Test
    public void runTestCase0() throws IOException {
        this.runTestCase("anagram-deletions/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("anagram-deletions/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("anagram-deletions/TestCase2.test");
    }

    @Override
    protected TestCase<Input,DefaultChallengeResult<Integer>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {
            
            String a = bufferedReader.readLine();

            String b = bufferedReader.readLine();

            String result = bufferedReader.readLine();
            int expected = Integer.parseInt(result);


            bufferedReader.close();

            return new TestCase<>(new Input(a,b), new DefaultChallengeResult<>(expected));

        } 
    }

    @Override
    protected ChallengeSolution<Input,DefaultChallengeResult<Integer>> getSolution() {
        return new Solution();
    }
}