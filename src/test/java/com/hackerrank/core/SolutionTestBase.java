package com.hackerrank.core;

import java.io.InputStream;
import java.io.IOException;

import org.junit.Assume;
import org.junit.Assert;

/**
 * Class <b>SolutionTestBase</b>. This class implements the generic scaffold
 * used to execute a test. The core logic is implemented in the method
 * {@link #runTestCase(String)} which extracts the test data mapping the
 * given test case identifier, executes the solution to the challenge 
 * provided by the test data and compares the result with the expected one.
 * Inherited classes are expected to define the logic to read the test data
 * and return the solution used to solve the challenge.
 */
public abstract class SolutionTestBase<I extends ChallengeInput, R extends ChallengeResult> {



    /**
     * Executes the test case that is referenced by <i>testCaseId</i>. The method
     * retrieves the input stream associated to the resource that is mapped by
     * <i>testCaseId</i>, it retrieves the {@link Solution} implementation and
     * checks whether the solution can accept the specified test case by invoking
     * {@link ChallengeSolution#accept(String)}. If the test is successful than an 
     * instance of {@link TestCase} is read from the input stream and the method 
     * {@link ChallengeSolution#execute(Input)} is invoked agains the input contained 
     * in the test case, the result is then compared with {@link TestCase#getResult()}.
     * 
     * @param testCaseId    a {@link String} representing the test case identifier.
     *                      It can neither be {@literal null} nor an empty string.
     * 
     * @throws IOException  if there is any I/O error while reading the test case
     *                      data.
     * 
     */
    protected void runTestCase(String testCaseId) throws IOException {


        ChallengeSolution<I,R> solution = this.getSolution();

        Assume.assumeTrue(String.format("Solution (type: %s) does not accept test case: %s,", solution.getClass().getName(), testCaseId),
                          solution.accept(testCaseId));

        try(InputStream testInput = this.getTestCase(testCaseId)) {

            TestCase<I,R> testCase = this.readTestCase(testInput);

            R actualResult = solution.execute(testCase.getInput());

            Assert.assertEquals(String.format("Solution (type: %s) failed test case: %s.", solution.getClass().getName(), testCaseId),
                                testCase.getResult(), actualResult);
        }
        
    }

    /**
     * This method returns an {@link InputStream} implementation that allows the
     * access to the resource file that is mapped by <i>testCaseId</i>. 
     * 
     * @param testCaseId    a {@link String} representing the path to the file
     *                      containing the test data in the test resources. It
     *                      can neither be {@literal null} nor an empty string.
     * 
     * @return  a {@link InputStream} implementation providing access to the
     *          resource file mapped by <i>testCaseId</i>.
     * 
     * @throws IllegalArgumentException if <i>testCaseId</i> is {@literal null} or
     *                                  an empty string.
     */
    protected InputStream getTestCase(String testCaseId) {

        if (testCaseId == null || testCaseId.isEmpty()) {
            throw new IllegalArgumentException("Test case identifier cannot be null or an empty string.");
        }

		
		return this.getClass()
				   .getClassLoader()
				   .getResourceAsStream(testCaseId);
	}

    /**
     * This is a hook for concrete test classes to implement the logic required
     * to read the information accessed via the {@link InputStream} implementation
     * and create an instance of {@link TestCase}.
     * 
     * @param testInput     a {@link InputStream} implementation providing access to
     *                      the test data. It is expected to not to be {@literal null}.
     * 
     * @return  a {@link TestCase} instance that is specialised and configured with
     *          the test data and the associated result.
     * 
     * @throws IOException  if there is any I/O error while reading from the stream.
     */
    protected abstract TestCase<I,R> readTestCase(InputStream testInput) throws IOException;
	
    /**
     * This is a hook for concrete test classes to implement the logic required to
     * return an instance of the test subject. 
     * 
     * @return  a concrete instance of {@link ChallengeSolution}, which represents 
     *          the subject under test.
     */
	protected abstract ChallengeSolution<I,R> getSolution();
}
