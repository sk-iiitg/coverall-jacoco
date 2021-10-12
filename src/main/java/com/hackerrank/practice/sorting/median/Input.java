package com.hackerrank.practice.sorting.median;

import java.util.List;

import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {

    protected List<Integer> array;

    public Input(List<Integer> arr) {
        this.array = arr;
    }

    public List<Integer> getArray() {
        return this.array;
    }
    
}
