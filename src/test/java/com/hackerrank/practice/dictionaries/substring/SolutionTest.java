package com.hackerrank.practice.dictionaries.substring;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Test;

import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.DefaultChallengeResult;

public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<String>> {

    @Test
    public void runTestCase0() throws IOException  {
        this.runTestCase("substring/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("substring/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("substring/TestCase2.test");
    }
    @Test
    public void runTestCase3() throws IOException {
        this.runTestCase("substring/TestCase3.test");
    }
    @Test
    public void runTestCase4() throws IOException {
        this.runTestCase("substring/TestCase4.test");
    }
    @Test
    public void runTestCase5() throws IOException {
        this.runTestCase("substring/TestCase5.test");
    }
    @Test
    public void runTestCase6() throws IOException {
        this.runTestCase("substring/TestCase6.test");
    }

    @Override
    protected TestCase<Input,DefaultChallengeResult<String>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {

            String s1 = bufferedReader.readLine();
            String s2 = bufferedReader.readLine();
            Input input = new Input(s1,s2);

            String expected = bufferedReader.readLine();
            DefaultChallengeResult<String> result = new DefaultChallengeResult<>(expected);
            
            bufferedReader.close();

            return new TestCase<>(input,result);
        } 
    }

    @Override
    protected ChallengeSolution<Input,DefaultChallengeResult<String>> getSolution() {
        return new Solution();
    }
}