package com.hackerrank.practice.sorting.inversions;

import java.util.List;
import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

// Interface <b>Solution</b>. This interface defines the contract for the
// solution to the HackerRank problem (counting-inversions):
//
// https://www.hackerrank.com/challenges/ctci-merge-sort/problem
//
public abstract class AbstractSolution extends DefaultChallengeSolution<Input,Long> {


    @Override
    public DefaultChallengeResult<Long> execute(Input input) {

        long inversions = this.countInversions(input.getArray());
        return new DefaultChallengeResult<>(inversions);
    }

    protected abstract long countInversions(List<Integer> arr);
}