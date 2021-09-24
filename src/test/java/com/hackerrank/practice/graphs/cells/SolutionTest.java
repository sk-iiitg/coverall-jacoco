package com.hackerrank.practice.graphs.cells;

import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.DefaultChallengeResult;


import org.junit.Test;


public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Integer>> {

    @Test
    public void runTestCase0() throws IOException  {
        this.runTestCase("connected-cells/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("connected-cells/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("connected-cells/TestCase2.test");
    }


    @Override
    protected TestCase<Input,DefaultChallengeResult<Integer>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) { 

            int n = Integer.parseInt(bufferedReader.readLine().trim());
            int m = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> grid = new ArrayList<>();
            for(int i=0; i<n; i++) {
                List<Integer> row = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                          .map(Integer::parseInt)
                                          .collect(Collectors.toList());
                grid.add(row);
            }
            
            int expected = Integer.parseInt(bufferedReader.readLine().trim());

            return new TestCase<>(new Input(grid), new DefaultChallengeResult<>(expected));

        } 
    }



    @Override
    protected ChallengeSolution<Input,DefaultChallengeResult<Integer>> getSolution() { 
        return new Solution(); 
    }
}