package com.hackerrank.practice.sorting.toys;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.stream.Stream;

import java.util.stream.Collectors;

import org.junit.Test;

import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;


public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Integer>> {

    @Test
    public void runTestCase0() throws IOException {
        this.runTestCase("maximum-toys/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("maximum-toys/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("maximum-toys/TestCase2.test");
    }
    @Test
    public void runTestCase3() throws IOException {
        this.runTestCase("maximum-toys/TestCase3.test");
    }
    @Test
    public void runTestCase4() throws IOException {
        this.runTestCase("maximum-toys/TestCase4.test");
    }
    @Test
    public void runTestCase5() throws IOException {
        this.runTestCase("maximum-toys/TestCase5.test");
    }
    
    @Override 
    protected TestCase<Input,DefaultChallengeResult<Integer>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);
    
            int k = Integer.parseInt(firstMultipleInput[1]);
    
            List<Integer> prices = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

            String result = bufferedReader.readLine();
            int expected = Integer.parseInt(result.trim());


            bufferedReader.close();

            return new TestCase<>(new Input(prices, k), new DefaultChallengeResult<>(expected));

        } 
    }

    @Override
    protected ChallengeSolution<Input,DefaultChallengeResult<Integer>> getSolution() {
        return new Solution();
    }
}