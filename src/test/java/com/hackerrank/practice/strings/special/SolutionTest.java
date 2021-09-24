package com.hackerrank.practice.strings.special;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.hackerrank.core.DefaultChallengeResult;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.SolutionTestBase;


public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Long>> {

    @Test
    public void runTestCase0() throws IOException {
        this.runTestCase("special-palindrome/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("special-palindrome/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("special-palindrome/TestCase2.test");
    }

    @Override
    protected TestCase<Input,DefaultChallengeResult<Long>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) { 
            
            String size = bufferedReader.readLine();
            int n = Integer.parseInt(size);
            String s = bufferedReader.readLine();

            String result = bufferedReader.readLine();
            long expected = Long.parseLong(result);


            bufferedReader.close();

            return new TestCase<>(new Input(n,s), new DefaultChallengeResult<>(expected));

        } 
    }

    @Override
    protected ChallengeSolution<Input,DefaultChallengeResult<Long>> getSolution() {
        return new Solution();
    }
}