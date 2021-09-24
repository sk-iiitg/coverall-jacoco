package com.hackerrank.practice.sorting.inversions;

import java.util.List;
import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {
    
    protected List<Integer> array;

    public Input(List<Integer> array) {
        this.array = array;
    }

    public List<Integer> getArray() { return this.array; }
}
