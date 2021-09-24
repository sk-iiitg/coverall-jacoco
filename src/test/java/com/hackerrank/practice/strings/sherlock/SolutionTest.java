package com.hackerrank.practice.strings.sherlock;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;


public class SolutionTest {

    @Test
    public void runTestCase0() {
        this.runTestCase("sherlock-valid/TestCase0.test");
    }
    @Test
    public void runTestCase1() {
        this.runTestCase("sherlock-valid/TestCase1.test");
    }
    @Test
    public void runTestCase2() {
        this.runTestCase("sherlock-valid/TestCase2.test");
    }

    protected void runTestCase(String testCase) {

        try(InputStream input = getTestCase(testCase)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
            
            String s = bufferedReader.readLine();

            Solution solution = this.getSolution();
            String actual = solution.isValid(s);

            String expected = bufferedReader.readLine();
            if (expected == null || expected.isEmpty()) {
                Assert.fail("Test case does not have solution (value computed: " + actual + ")");
            } else {
                Assert.assertEquals("TestCase should return correct value.", expected, actual);
            }

            bufferedReader.close();

        } catch(IOException ioex) {

            Assert.fail("Test failed: cannot read test case: " + ioex.getMessage());
        }
    }

    protected InputStream getTestCase(String testCase) {
		
		return this.getClass()
				   .getClassLoader()
				   .getResourceAsStream(testCase);
    }

    protected Solution getSolution() {
        return new Solution();
    }
}