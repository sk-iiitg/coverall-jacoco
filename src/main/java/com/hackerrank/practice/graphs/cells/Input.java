package com.hackerrank.practice.graphs.cells;

import java.util.List;
import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {
    
    protected List<List<Integer>> grid;

    public Input(List<List<Integer>> grid) {
        this.grid = grid;
    }

    public List<List<Integer>> getGrid() {
        return this.grid;
    }
}
