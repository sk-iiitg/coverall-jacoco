package com.hackerrank.practice.dictionaries.ransom;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.stream.Stream;


import java.util.stream.Collectors;

import org.junit.Test;

import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;
import com.hackerrank.core.TestCase;


public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<String>> {

    @Test
    public void runTestCase0() throws IOException {
        this.runTestCase("ransom-note/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("ransom-note/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("ransom-note/TestCase2.test");
    }

    protected TestCase<Input,DefaultChallengeResult<String>> readTestCase(InputStream testInput) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int m = Integer.parseInt(firstMultipleInput[0]);
    
            int n = Integer.parseInt(firstMultipleInput[1]);
    
            List<String> magazine = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                          .collect(Collectors.toList());
    
            List<String> note = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                      .collect(Collectors.toList());

            String expected = bufferedReader.readLine();
          

            bufferedReader.close();

            Input input = new Input(magazine, note);
            DefaultChallengeResult<String> result = new DefaultChallengeResult<>(expected);
            return new TestCase<>(input,result);

        } 
    }

    protected ChallengeSolution<Input,DefaultChallengeResult<String>> getSolution() {
        return new Solution();
    }
}