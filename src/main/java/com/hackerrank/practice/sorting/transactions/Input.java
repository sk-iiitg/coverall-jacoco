package com.hackerrank.practice.sorting.transactions;

import java.util.List;
import com.hackerrank.core.ChallengeInput;


public class Input implements ChallengeInput {

    protected List<Integer> expenditure;
    protected int days;
    
    public Input(List<Integer> expenditure, int days) {
        this.expenditure = expenditure;
        this.days = days;
    }

    public List<Integer> getExpenditure() { return this.expenditure; }
    public int getDays() { return this.days; }
    
}
