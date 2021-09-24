package com.hackerrank.practice.dictionaries.triplets;

import java.util.List;

import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {
    
    protected long factor;
    protected List<Long> array;

    public Input(long factor, List<Long> array) {
        this.factor = factor;
        this.array = array;
    }

    public long getFactor() { return this.factor; }
    public List<Long> getArray() { return this.array; }
}
