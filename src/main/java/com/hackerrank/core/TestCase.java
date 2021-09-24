package com.hackerrank.core;

/**
 * Class <b>TestCase</b>. This class wraps both the input and
 * the expected result for a test case. It is a utility class
 * that is used to package together the information required
 * to verify a test case.
 */
public class TestCase<I extends ChallengeInput, R extends ChallengeResult> {
    
    /**
     * The input data for the test.
     */
    protected I input;
    /**
     * The expected result for the test.
     */
    protected R result;

    /**
     * Initialises this instance of {@link TestCase} with the
     * given input and expected result.
     * 
     * @param input     the input for the test.
     * @param result    the expected result for the given input.
     */
    public TestCase(I input, R result) {
        this.input = input;
        this.result = result;
    }

    /**
     * Gets the input data for the test.
     * 
     * @return the input data for the test.
     */
    public I getInput() {
        return this.input;
    }

    /**
     * Gets the result for the test.
     * 
     * @return the result for the test.
     */
    public R getResult() {
        return this.result;
    }
}
