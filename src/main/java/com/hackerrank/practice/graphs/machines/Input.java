package com.hackerrank.practice.graphs.machines;

import java.util.List;
import com.hackerrank.core.ChallengeInput;

public class Input implements ChallengeInput {

    protected List<List<Integer>> roads;
    protected List<Integer> machines;

    public Input(List<List<Integer>> roads, List<Integer> machines) {
        this.roads = roads;
        this.machines = machines;
    }

    public List<List<Integer>> getRoads() { return this.roads; }
    public List<Integer> getMachines() { return this.machines; }
}