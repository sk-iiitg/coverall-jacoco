package com.hackerrank.practice.graphs.distances;

import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.ChallengeSolution;


public class SolutionTest extends SolutionTestBase<Input,Result> {

    @Test
    public void runTestCase0() throws IOException  {
        this.runTestCase("shortest-distances/TestCase0.test");
    }
    @Test
    public void runTestCase1() throws IOException {
        this.runTestCase("shortest-distances/TestCase1.test");
    }
    @Test
    public void runTestCase2() throws IOException {
        this.runTestCase("shortest-distances/TestCase2.test");
    }
    @Test
    public void runTestCase3() throws IOException {
        this.runTestCase("shortest-distances/TestCase3.test");
    }
    @Test
    public void runTestCase4() throws IOException {
        this.runTestCase("shortest-distances/TestCase4.test");
    }

    @Override
    protected TestCase<Input,Result> readTestCase(InputStream testInput) throws IOException {

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
        
    
            int startNode = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            
            String[] result = scanner.nextLine().split(" ");
            long[] expected = new long[result.length];
            for(int i=0; i<result.length; i++) {
                expected[i] = Long.parseLong(result[i]);
            }

            Input input = new Input(graphNodes, graphFrom, graphTo, startNode);
            Result r = new Result(expected);
            return new TestCase<>(input,r);

        } 
    }


    @Override
    protected ChallengeSolution<Input,Result> getSolution() { return new Solution(); };
}