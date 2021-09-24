package com.hackerrank.practice.strings.anagrams;

import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {

    protected String a;
    protected String b;

    public Input(String a, String b) {
        this.a = a;
        this.b = b;
    }
    
    public String getA() { return this.a; }
    public String getB() { return this.b; }
 }
