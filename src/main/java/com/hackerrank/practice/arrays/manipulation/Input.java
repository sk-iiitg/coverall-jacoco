package com.hackerrank.practice.arrays.manipulation;

import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {
    
    protected int n;
    protected int m;
    protected int[][] queries;

    public Input(int n, int m, int[][] queries) {
        this.n = n;
        this.m = m;
        this.queries = queries;
    }

    public int getN() { return this.n; }
    public int getM() { return this.m; }
    public int[][] getQueries() { return this.queries; }
}
