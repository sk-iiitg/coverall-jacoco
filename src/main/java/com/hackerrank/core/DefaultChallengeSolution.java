package com.hackerrank.core;

/**
 * Class <b>DefaultChallengeSolution</b>. This provides the common base
 * implementation for most of the solutions used in HackerRank, where the
 * result has a single value and there is no limit to the tests being 
 * executed. Inherited classes, may decide to override the method {@link 
 * #accept(String)} to exclude some test cases.
 */
public abstract class DefaultChallengeSolution<I extends ChallengeInput,V> implements ChallengeSolution<I,DefaultChallengeResult<V>> {

    /**
     * {@inheritDoc}
     * 
     * This implementation returns {@literal true} for all values of the
     * test case identifier. Inherited classes may want to override this
     * method if they require to exclude some test cases.
     * 
     * @return {@literal true} 
     */
    @Override
    public boolean accept(String testCaseId) {
        return true;
    }
    
}
