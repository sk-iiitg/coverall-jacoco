package com.hackerrank.practice.dictionaries.triplets;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.stream.Stream;


import com.hackerrank.core.DefaultChallengeResult;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.SolutionTestBase;

import java.util.stream.Collectors;

import org.junit.Test;


public abstract class AbstractSolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Long>> {

    @Test
    public void runTestCase0() throws IOException  {
        this.runTestCase("count-triplets/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException  {
        this.runTestCase("count-triplets/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException  {
        this.runTestCase("count-triplets/TestCase2.test");
    }
    @Test
    public void runTestCase3() throws IOException  {
        this.runTestCase("count-triplets/TestCase3.test");
    }
    @Test
    public void runTestCase4() throws IOException  {
        this.runTestCase("count-triplets/TestCase4.test");
    }
    @Test
    public void runTestCase5() throws IOException {
        this.runTestCase("count-triplets/TestCase5.test");
    }


    @Override
    protected TestCase<Input,DefaultChallengeResult<Long>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {

            String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(nr[0]);
    
            long r = Long.parseLong(nr[1]);
    
            List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());


            Input input = new Input(r,arr);

            String expected = bufferedReader.readLine();
            long expectedSum = Long.parseLong(expected);
            DefaultChallengeResult<Long> result = new DefaultChallengeResult<>(expectedSum);


            return new TestCase<>(input,result);

        }
    }
}

