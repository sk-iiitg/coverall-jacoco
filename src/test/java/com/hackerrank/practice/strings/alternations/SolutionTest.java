package com.hackerrank.practice.strings.alternations;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Test;

import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;
import com.hackerrank.core.TestCase;


public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Integer>> {

    @Test
    public void runTestCase0() throws IOException  {
        this.runTestCase("character-alternations/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException  {
        this.runTestCase("character-alternations/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException  {
        this.runTestCase("character-alternations/TestCase2.test");
    }
    @Test
    public void runTestCase3() throws IOException {
        this.runTestCase("character-alternations/TestCase3.test");
    }
    @Test
    public void runTestCase4() throws IOException  {
        this.runTestCase("character-alternations/TestCase4.test");
    }
    @Test
    public void runTestCase5() throws IOException  {
        this.runTestCase("character-alternations/TestCase5.test");
    }
    @Test
    public void runTestCase6() throws IOException  {
        this.runTestCase("character-alternations/TestCase6.test");
    }

    @Override
    protected TestCase<Input,DefaultChallengeResult<Integer>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {
            
            String s = bufferedReader.readLine();

            String result = bufferedReader.readLine();
            int expected = Integer.parseInt(result);

            bufferedReader.close();

            return new TestCase<>(new Input(s), new DefaultChallengeResult<Integer>(expected));
        } 
    }

    @Override
    protected ChallengeSolution<Input,DefaultChallengeResult<Integer>> getSolution() {
        return new Solution();
    }
}