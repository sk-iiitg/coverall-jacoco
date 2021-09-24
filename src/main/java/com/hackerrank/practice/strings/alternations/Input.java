package com.hackerrank.practice.strings.alternations;

import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {
    
    protected String text; 

    public Input(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
