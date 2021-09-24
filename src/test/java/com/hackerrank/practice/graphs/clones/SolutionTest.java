package com.hackerrank.practice.graphs.clones;

import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;

import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.ChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

import org.junit.Test;


public class SolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Integer>> {

    @Test
    public void runTestCase0() throws IOException {
        this.runTestCase("nearest-clones/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("nearest-clones/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("nearest-clones/TestCase2.test");
    }
    @Test
    public void runTestCase3() throws IOException {
        this.runTestCase("nearest-clones/TestCase3.test");
    }

    @Override
    protected TestCase<Input,DefaultChallengeResult<Integer>> readTestCase(InputStream testInput) throws IOException {

        try(Scanner scanner = new Scanner(testInput)) {

            String[] graphNodesEdges = scanner.nextLine().split(" ");
            int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
            int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());
    
            int[] graphFrom = new int[graphEdges];
            int[] graphTo = new int[graphEdges];
    
            for (int i = 0; i < graphEdges; i++) {
                String[] graphFromTo = scanner.nextLine().split(" ");
                graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
                graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
            }
    
            long[] ids = new long[graphNodes];
    
            String[] idsItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    
            for (int i = 0; i < graphNodes; i++) {
                long idsItem = Long.parseLong(idsItems[i]);
                ids[i] = idsItem;
            }
    
            int val = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int expected = scanner.nextInt();

            Input input = new Input(graphNodes, graphFrom, graphTo, ids, val);
            DefaultChallengeResult<Integer> result = new DefaultChallengeResult<>(expected);

            return new TestCase<>(input,result);
        } 
    }

    @Override
    protected ChallengeSolution<Input,DefaultChallengeResult<Integer>> getSolution() { return new Solution(); };
}