package com.hackerrank.practice.graphs.clones;

import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {
    
    protected int graphNodes;
    protected int[] graphFrom;
    protected int[] graphTo;
    protected long[] ids;
    protected int value;
    


    public Input(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int value) {
        this.graphNodes = graphNodes;
        this.graphFrom = graphFrom;
        this.graphTo = graphTo;
        this.ids = ids;
        this.value = value;
    }

    public int getGraphNodes() {
        return this.graphNodes;
    }

    public int[] getGraphFrom() {
        return this.graphFrom;
    }

    public int[] getGraphTo() {
        return this.graphTo;
    }

    public long[] getIds() {
        return this.ids;
    }

    public int getValue() {
        return this.value;
    }
}
