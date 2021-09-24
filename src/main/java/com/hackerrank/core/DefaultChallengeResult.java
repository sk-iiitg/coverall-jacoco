package com.hackerrank.core;
/**
 * Class <b>DefaultChallengeResult</b>. This class implements {@link ChallengeResult}
 * and provides a simple holder for a single value. The equals method is based on 
 * testing conformance with type and the equality for the value held by this class.
 */
public class DefaultChallengeResult<T> implements ChallengeResult {

    protected T value;

    /**
     * Initialises an instance of {@link DefaultChallengeResult} with the given value.
     * 
     * @param value     the value representing the result.
     */
    public DefaultChallengeResult(T value) {
        this.value = value;
    }

    /**
     * Gets the value of the result.
     * 
     * @return the value.
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Determines whether the current instance equals the given one.
     * 
     * @param other a reference to the instance to test for equality. It
     *              can be {@literal null}.
     * 
     * @return {@literal true} if <i>other</i> is of the same type as the
     *          current instance and the value wrapped by the instance is
     *          the same as the value of this instance.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof DefaultChallengeResult<?>) {

            DefaultChallengeResult<T> otherResult = (DefaultChallengeResult<T>) other;
            T v = this.getValue();

            return (v == null ? otherResult.getValue() == null : v.equals(otherResult.getValue()));

        }
        return false;
    }
}
