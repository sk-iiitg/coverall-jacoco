package com.hackerrank.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * Class <b>TestCaseTest</b>. This class tests the implemented 
 * behaviour of {@link TestCase}. The implemented logic is the
 * one of a simple pojo that has two attributes: input and
 * result, which can be {@literal null}.
 */
public class TestCaseTest {

    /**
     * Class <i>DummyInput</i>. This class is a simple dummy
     * used to verify the implemented logic of the {@link TestCase}
     * class.
     */
    public static class DummyInput implements ChallengeInput {

    }

    /**
     * Class <i>DummyResult</i>. This class is a simple dummy
     * used to verify the implemented logic of the {@link TestCase}
     * class.
     */
    public static class DummyResult implements ChallengeResult {

    }

    @Test
    public void testConstructor() {

        DummyInput di = new DummyInput();
        DummyResult dr = new DummyResult();        

        TestCase<DummyInput, DummyResult> subject = new TestCase<>(di, dr);
        Assert.assertEquals(di, subject.input);
        Assert.assertEquals(dr, subject.result);

    }

    @Test
    public void testInput() {


        DummyInput di = new DummyInput();
        TestCase<DummyInput, DummyResult> subject = new TestCase<>(di, null);
        Assert.assertEquals(di, subject.getInput());

    }

    @Test
    public void testResult() {

        DummyResult dr = new DummyResult();
        TestCase<DummyInput, DummyResult> subject = new TestCase<>(null, dr);
        Assert.assertEquals(dr, subject.getResult());
    }
    
}
