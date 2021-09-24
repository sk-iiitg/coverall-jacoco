package com.hackerrank.practice.dictionaries.queries;

import java.util.List;
import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {
    
    protected List<int[]> queries;

    public Input(List<int[]> queries) {
        this.queries = queries;
    }

    public List<int[]> getQueries() {
        return this.queries;
    }
}
