package com.hackerrank.practice.sorting.transactions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.stream.Stream;

import java.util.stream.Collectors;
import java.util.List;


import org.junit.Test;


import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Integer>> {

    @Test
    public void runTestCase0() throws IOException {

        this.runTestCase("financial-transactions/TestCase0.test"); 
    }
    @Test
    public void runTestCase1() throws IOException {

       this.runTestCase("financial-transactions/TestCase1.test"); 
    }
    @Test
    public void runTestCase2() throws IOException {

      this.runTestCase("financial-transactions/TestCase2.test"); 
    }
    @Test
    public void runTestCase11() throws IOException {

      this.runTestCase("financial-transactions/TestCase11.test"); 
    }

    @Override
    protected TestCase<Input,DefaultChallengeResult<Integer>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))){

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);
            int d = Integer.parseInt(firstMultipleInput[1]);
            
    
            List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
            
            String content = bufferedReader.readLine();
            int expected = Integer.parseInt(content);

            bufferedReader.close();

            return new TestCase<>(new Input(expenditure,d), new DefaultChallengeResult<>(expected));
    
        } 
    }

    @Override
    protected ChallengeSolution<Input,DefaultChallengeResult<Integer>> getSolution() {
        return new Solution();
    }
}