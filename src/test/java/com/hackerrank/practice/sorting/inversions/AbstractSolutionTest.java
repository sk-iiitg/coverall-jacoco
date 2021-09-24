package com.hackerrank.practice.sorting.inversions;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.stream.Stream;


import java.util.stream.Collectors;

import org.junit.Test;

import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.DefaultChallengeResult;
import com.hackerrank.core.TestCase;


public abstract class AbstractSolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Long>> {

    @Test
    public void runTestCase0() throws IOException {
        this.runTestCase("count-inversions/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("count-inversions/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("count-inversions/TestCase2.test");
    }
    @Test
    public void runTestCase3() throws IOException {
        this.runTestCase("count-inversions/TestCase3.test");
    }
    @Test
    public void runTestCase4() throws IOException {
        this.runTestCase("count-inversions/TestCase4.test");
    }
    @Test
    public void runTestCase5() throws IOException {
        this.runTestCase("count-inversions/TestCase5.test");
    }
    @Test
    public void runTestCase6() throws IOException {
        this.runTestCase("count-inversions/TestCase6.test");
    }
    @Test
    public void runTestCase7() throws IOException {
        this.runTestCase("count-inversions/TestCase7.test");
    }
    @Test
    public void runTestCase8() throws IOException {
        this.runTestCase("count-inversions/TestCase8.test");
    }

    @Override
    protected TestCase<Input,DefaultChallengeResult<Long>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


            String result = bufferedReader.readLine();
            long expected = Integer.parseInt(result.trim());

            bufferedReader.close();

            return new TestCase<>(new Input(arr), new DefaultChallengeResult<>(expected));

        } 
    }
}