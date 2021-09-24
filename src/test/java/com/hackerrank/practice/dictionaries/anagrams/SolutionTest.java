package com.hackerrank.practice.dictionaries.anagrams;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.DefaultChallengeResult;


import org.junit.Test;


public class SolutionTest extends SolutionTestBase<Input, DefaultChallengeResult<Integer>> {

    @Test
    public void runTestCase0() throws IOException  {
        this.runTestCase("sherlock-and-anagrams/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("sherlock-and-anagrams/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("sherlock-and-anagrams/TestCase2.test");
    }
    @Test
    public void runTestCase3() throws IOException {
        this.runTestCase("sherlock-and-anagrams/TestCase3.test");
    }
    @Test
    public void runTestCase4() throws IOException {
        this.runTestCase("sherlock-and-anagrams/TestCase4.test");
    }


    @Override
    protected TestCase<Input,DefaultChallengeResult<Integer>> readTestCase(InputStream testInput) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput));

        String s = bufferedReader.readLine();

        String expected = bufferedReader.readLine();
        int result = Integer.parseInt(expected);

        bufferedReader.close();

        return new TestCase<>(new Input(s), new DefaultChallengeResult<>(result));
    }


    protected ChallengeSolution<Input,DefaultChallengeResult<Integer>> getSolution() {
        return new Solution();
    }
}

