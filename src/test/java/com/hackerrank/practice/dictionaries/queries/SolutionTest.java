package com.hackerrank.practice.dictionaries.queries;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;


import org.junit.Test;

import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;


public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<List<Integer>>> {

    @Test
    public void runTestCase0() throws IOException {
        this.runTestCase("queries/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("queries/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("queries/TestCase2.test");
    }
    @Test
    public void runTestCase3() throws IOException {
        this.runTestCase("queries/TestCase3.test");
    }
    @Test
    public void runTestCase4() throws IOException {
        this.runTestCase("queries/TestCase4.test");
    }

    @Override
    protected TestCase<Input,DefaultChallengeResult<List<Integer>>> readTestCase(InputStream testInput) throws IOException {

         try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testInput))) {

            int q = Integer.parseInt(bufferedReader.readLine().trim());

            List<int[]> queries = new ArrayList<>();
            try {
                for(int i=0; i<q; i++) {
                    String[] s = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                    queries.add(new int[] {Integer.parseInt(s[0]), Integer.parseInt(s[1]) });
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            List<Integer> expected = new ArrayList<>();
            String answer = null;
            boolean moreToRead = true;
            while (moreToRead) {
                answer = bufferedReader.readLine();
                if (answer != null) {
                    answer = answer.replaceAll("\\s+$", "");
                    expected.add(Integer.parseInt(answer));

                } else {
                    moreToRead = false;
                }
            }


            bufferedReader.close();


            Input input = new Input(queries);
            DefaultChallengeResult<List<Integer>> result = new DefaultChallengeResult<>(expected);

            return new TestCase<>(input,result);

        } 
    }

    @Override
    protected ChallengeSolution<Input,DefaultChallengeResult<List<Integer>>> getSolution() {
        return new Solution();
    }
}