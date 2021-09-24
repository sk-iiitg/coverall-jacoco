package com.hackerrank.practice.sorting.toys;

import java.util.List;
import com.hackerrank.core.ChallengeInput;


public class Input implements ChallengeInput {

    protected List<Integer> prices;
    protected int budget;


    public Input(List<Integer> prices, int budget) {
        this.budget = budget;
        this.prices = prices;
    }
    
    public List<Integer> getPrices() {
        return this.prices;
    }

    public int getBudget() {
        return this.budget;
    }
}
