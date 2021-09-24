package com.hackerrank.practice.strings.special;

import com.hackerrank.core.ChallengeInput;


public class Input implements ChallengeInput {

    protected String text;
    protected int n;

    public Input(int n, String text) {
        this.n = n;
        this.text = text;
    }
    
    public String getText() {
        return this.text;
    }

    public int getN() {
        return this.n;
    }
}
