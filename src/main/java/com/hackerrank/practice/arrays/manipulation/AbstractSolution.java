/**
 * 
 */
package com.hackerrank.practice.arrays.manipulation;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

/**
 * @author fl0yd
 *
 */
public abstract class AbstractSolution extends DefaultChallengeSolution<Input, Long> {

    @Override
    public DefaultChallengeResult<Long> execute(Input input) {

        long max = this.arrayManipulation(input.getN(), input.getQueries());
        return new DefaultChallengeResult<>(max);
    }
    
    protected abstract long arrayManipulation(int n, int[][] queries);


}
