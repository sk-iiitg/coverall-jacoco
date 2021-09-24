package com.hackerrank.practice.graphs.libraries;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.DefaultChallengeResult;


public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Long>> {

    @Test
    public void runTestCase0() throws IOException {
        this.runTestCase("road-and-libraries/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException  {
        this.runTestCase("road-and-libraries/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException  {
        this.runTestCase("road-and-libraries/TestCase2.test");
    }
    @Test
    public void runTestCase3() throws IOException  {
        this.runTestCase("road-and-libraries/TestCase3.test");
    }
    @Test
    public void runTestCase4() throws IOException  {
        this.runTestCase("road-and-libraries/TestCase4.test");
    }
    @Test
    public void runTestCase5()  throws IOException {
        this.runTestCase("road-and-libraries/TestCase5.test");
    }
    @Test
    public void runTestCase6() throws IOException  {
        this.runTestCase("road-and-libraries/TestCase6.test");
    }
    @Test
    public void runTestCase7() throws IOException  {
        this.runTestCase("road-and-libraries/TestCase7.test");
    }
    @Test
    public void runTestCas8() throws IOException  {
        this.runTestCase("road-and-libraries/TestCase8.test");
    }
    @Test
    public void runTestCase9() throws IOException {
        this.runTestCase("road-and-libraries/TestCase9.test");
    }
    @Test
    public void runTestCase10() throws IOException {
        this.runTestCase("road-and-libraries/TestCase10.test");
    }
    @Test
    public void runTestCase11() throws IOException {
        this.runTestCase("road-and-libraries/TestCase11.test");
    }
    @Test
    public void runTestCase12() throws IOException {
        this.runTestCase("road-and-libraries/TestCase12.test");
    }
    @Test
    public void runTestCase13() throws IOException {
        this.runTestCase("road-and-libraries/TestCase13.test");
    }

    @Override
    protected TestCase<Input,DefaultChallengeResult<Long>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int m = Integer.parseInt(firstMultipleInput[1]);

            int c_lib = Integer.parseInt(firstMultipleInput[2]);

            int c_road = Integer.parseInt(firstMultipleInput[3]);

            List<List<Integer>> cities = new ArrayList<>();
            for(int i=0; i<m; i++) {
                String[] parts = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                List<Integer> roads = new ArrayList<>();
                int v = Integer.parseInt(parts[0]);
                int u = Integer.parseInt(parts[1]);
                roads.add(v);
                roads.add(u);

                cities.add(roads);
            }


            String result = bufferedReader.readLine();
            long expected = Long.parseLong(result);
             
            return new TestCase<>(new Input(n, c_lib, c_road, cities), new DefaultChallengeResult<>(expected));

        } 
    }

    @Override
    protected ChallengeSolution<Input,DefaultChallengeResult<Long>> getSolution() {
        return new Solution();
    }
}