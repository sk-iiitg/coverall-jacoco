package com.hackerrank.practice.graphs.machines;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;


import com.hackerrank.core.DefaultChallengeResult;
import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;

import org.junit.Test;



public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Integer>> {


    @Test
    public void runTestCase0() throws IOException {
        this.runTestCase("matrix-machines/testCase0.test"); 
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("matrix-machines/testCase1.test"); 
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("matrix-machines/testCase2.test"); 
    }
    @Test
    public void runTestCase3() throws IOException {
        this.runTestCase("matrix-machines/testCase3.test"); 
    }
    @Test
    public void runTestCase4() throws IOException {
        this.runTestCase("matrix-machines/testCase4.test"); 
    }
    @Test
    public void runTestCase5() throws IOException {
        this.runTestCase("matrix-machines/testCase5.test"); 
    }
    @Test
    public void runTestCase6() throws IOException {
        this.runTestCase("matrix-machines/testCase6.test");
    }


    @Override
    protected TestCase<Input,DefaultChallengeResult<Integer>> readTestCase(InputStream testInput) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int k = Integer.parseInt(firstMultipleInput[1]);

            List<List<Integer>> roads = new ArrayList<>();

            IntStream.range(0, n - 1).forEach(i -> {
                try {
                    roads.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            List<Integer> machines = IntStream.range(0, k).mapToObj(i -> {
                try {
                    return bufferedReader.readLine().replaceAll("\\s+$", "");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

            String result = bufferedReader.readLine();
            int expected = Integer.parseInt(result);

            return new TestCase<>(new Input(roads, machines), new DefaultChallengeResult<>(expected));

        }


    }
	
    @Override
	protected ChallengeSolution<Input,DefaultChallengeResult<Integer>> getSolution() {
        return new Solution();
    }

}