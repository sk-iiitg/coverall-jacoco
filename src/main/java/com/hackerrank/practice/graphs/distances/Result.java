package com.hackerrank.practice.graphs.distances;

import java.util.Arrays;
import com.hackerrank.core.ChallengeResult;

public class Result implements ChallengeResult {

    protected long[] distances;

    public Result(long[] distances) {
        this.distances = distances;
    }

    public long[] getDistances() {
        return this.distances;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Result) {
            return Arrays.equals(this.distances, ((Result) other).getDistances());
        }
        return false;
    }
    
}
