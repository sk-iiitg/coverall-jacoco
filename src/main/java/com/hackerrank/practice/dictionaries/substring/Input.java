package com.hackerrank.practice.dictionaries.substring;

import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {
    
    protected String s1;
    protected String s2;

    public Input(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public String getS1() { return this.s1; }
    public String getS2() { return this.s2; }
}
