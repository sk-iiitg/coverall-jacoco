package com.hackerrank.practice.graphs.distances;


import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {

    protected int n;
    protected int[] graphFrom;
    protected int[] graphTo;
    protected int startNode;

    public Input(int n, int[] graphFrom, int[] graphTo, int startNode) {
        this.n = n;
        this.graphFrom = graphFrom;
        this.graphTo = graphTo;
        this.startNode = startNode;
    }

    public int getN() { return this.n; }
    public int[] getGraphFrom() { return this.graphFrom; }
    public int[] getGraphTo() { return this.graphTo; }
    public int getStartNode() { return this.startNode; }
    
}
