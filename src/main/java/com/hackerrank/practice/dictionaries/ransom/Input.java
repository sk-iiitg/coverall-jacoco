package com.hackerrank.practice.dictionaries.ransom;

import java.util.List;
import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {
    
    protected List<String> magazine;
    protected List<String> note;

    public Input(List<String> magazine, List<String> note) {
        this.magazine = magazine;
        this.note = note;
    }

    public List<String> getMagazine() {
        return this.magazine;
    }

    public List<String> getNote() {
        return this.note;
    }
}
