package com.hackerrank.practice.sorting.median;

import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

public class SolutionTest extends SolutionTestBase<Input, DefaultChallengeResult<Integer>> {

    @Test
    public void runTestCase0() throws IOException {

        this.runTestCase("sorting-median/testCase0.test");
    }

    @Test
    public void runTestCase1() throws IOException {

        this.runTestCase("sorting-median/testCase1.test");
    }

    @Override
    protected TestCase<Input, DefaultChallengeResult<Integer>> readTestCase(InputStream testInput) throws IOException {
        
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                      .map(Integer::parseInt)
                                      .collect(Collectors.toList());

            String result = bufferedReader.readLine();
            int expected = Integer.parseInt(result);

            return new TestCase<>(new Input(arr), new DefaultChallengeResult<>(expected));
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    protected ChallengeSolution<Input, DefaultChallengeResult<Integer>> getSolution() {
        
        return new Solution();
    }
    
}
