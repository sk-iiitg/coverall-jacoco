package com.hackerrank.practice.dictionaries.triplets;

import java.util.List;
import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

/**
 * Class Solution. This class provides an interface for the
 * solution to the challenge:
 * 
 * 
 * Inherited classes provide different implementation for 
 * the solution.
 */
public abstract  class AbstractSolution extends DefaultChallengeSolution<Input,Long> {
    
    @Override
    public DefaultChallengeResult<Long> execute(Input input) {

        long triplets = this.countTriplets(input.getArray(), input.getFactor());
        return new DefaultChallengeResult<>(triplets);
    }


    protected abstract long countTriplets(List<Long> arr, long r);
}