package com.hackerrank.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * Class <b>DefaultChallengeResultTest</b>. This class verifies the implemented behaviour
 * of {@link DefaultChallengeResult}. The class is a simple wrapper for a value with an
 * associated getter. The core capability tested in this class is the method {@link 
 * DefaultChallengeResult#equals(Object)}, which checks type compatibility and then 
 * equality of the wrapped values. 
 */
public class DefaultChallengeResultTest {


    public class Dummy {

        protected boolean called;

        
        public boolean equals(Object other) {
            called = true;
            return super.equals(other);
        }
    }


    @Test
    public void testConstructor() {

        String expected = "This is a test";
        DefaultChallengeResult<Object> subject = new DefaultChallengeResult<>(expected);
        Assert.assertEquals("Property should be set to attribute passed to constructor.", expected, subject.value);

    }

    @Test
    public void testGetValue() {

        String expected = "This is another test.";
        DefaultChallengeResult<Object> subject = new DefaultChallengeResult<>(expected);
        Assert.assertEquals("Reference returned by getValue() should match property value.", expected, subject.getValue());
    }

    @Test
    public void testGetValueWithNull() {

        DefaultChallengeResult<Object> subject = new DefaultChallengeResult<>(null);
        Assert.assertNull("Null value should be returned by getValue() when initialised with null.", subject.getValue());

    }

    @Test
    public void testEqualsWithNull() {

        DefaultChallengeResult<Object> subject = new DefaultChallengeResult<>((int) 45);
        Assert.assertFalse("Equality test should fail for null arguments.", subject.equals(null));
    }


    @Test
    public void testEqualsCallsEquals() {

        Dummy dummy = new Dummy();
        DefaultChallengeResult<Dummy> subject = new DefaultChallengeResult<>(dummy);

        Dummy other = new Dummy();

        subject.equals(new DefaultChallengeResult<Dummy>(other));

        Assert.assertTrue("Equality test should invole equals of wrapped instance.", dummy.called);
    }
    

    @Test
    public void testEqualsWithDifferentType() {

        DefaultChallengeResult<String> subject = new DefaultChallengeResult<String>("This is type test");
        Assert.assertFalse("Equality test should fail for an argument of different type.", subject.equals(new Object()));
    }

    @Test
    public void testEqualsWithWrappedNull() {

        String s1 = null;
        String s2 = null;

        DefaultChallengeResult<String> subject = new DefaultChallengeResult<>(s1);
        DefaultChallengeResult<String> other = new DefaultChallengeResult<>(s2);

        Assert.assertTrue("Equality test should succeed for wrapped nulls.", subject.equals(other));
    }


    @Test 
    public void testEqualsWithSameValue() {

        long l1 = 45;
        long l2 = 45;

        DefaultChallengeResult<Long> subject = new DefaultChallengeResult<>(l1);
        DefaultChallengeResult<Long> other = new DefaultChallengeResult<>(l2);

        Assert.assertTrue("Equality test should succeed for the same value.", subject.equals(other));

    }


    @Test
    public void testEqualsWithDifferentValues() {

        String s1 = "this is a value";
        String s2 = "this si another value";

        DefaultChallengeResult<String> subject = new DefaultChallengeResult<>(s1);
        DefaultChallengeResult<String> other = new DefaultChallengeResult<>(s2);

        Assert.assertFalse("Equality test should fail for different values.", subject.equals(other));
    }

}
