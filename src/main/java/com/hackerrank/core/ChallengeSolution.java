package com.hackerrank.core;

/**
 * Interface <b>ChallengeSolution</b>. This interface defines the signature
 * to a generic solution to a HackerRank challenge which takes an
 * input and produces a result. The interface also provides a method
 * to determine whether the solution can be run on a specific test 
 * case.
 */
public interface ChallengeSolution<I extends ChallengeInput, R extends ChallengeResult> {

    /**
     * Determines whether the current solution can be applied to the
     * test case specified by the given identifier. This method prevents
     * that sub-optimal solutions are run on specific test cases.
     * 
     * @param testCaseId    a {@link String} representing the unique
     *                      identifier of the test case. It is expected
     *                      to not to be {@literal null}.
     * 
     * @return  {@literal true} if the solution accepts the testcase or
     *          {@literal false} otherwise.
     * 
     * @throws IllegalArgumentException if testCaseId is {@literal null} or
     *                                  an empty string.
     */
    public boolean accept(String testCaseId);

    /**
     * Executes the algorithm implemented in the solution against the given
     * input data and produces a result. 
     * 
     * @param input a concrete type implementing {@link ChallengeInput} that 
     *              is used to abstract away the set of parameters and attributes 
     *              that are required to initialise the solution to the
     *              algorithm. It is expected to not to be {@literal null}.
     * 
     * @return  a {@link ChallengeResult} implementation that contains the result 
     *          for the solution. It is guaranteed to not to be {@literal null}.
     * 
     * @throws IllegalArgumentException if input is {@literal null}.
     */
    public R execute(I input);
    
}
