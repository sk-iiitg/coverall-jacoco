package com.hackerrank.practice.graphs.libraries;

import com.hackerrank.core.ChallengeInput;
import java.util.List;

public class Input implements ChallengeInput {
    
    protected int libraryCost;
    protected int roadCost;
    protected int citiesCount;
    protected List<List<Integer>> cities;

    public Input(int citiesCount, int libraryCost, int roadCost, List<List<Integer>> cities) {

        this.citiesCount = citiesCount;
        this.libraryCost = libraryCost;
        this.roadCost = roadCost;
        this.cities = cities;
    }

    public List<List<Integer>> getCities() { return this.cities; }
    public int getLibraryCost() { return this.libraryCost; }
    public int getRoadCoast() { return this.roadCost; }
    public int getCitiesCount() { return this.citiesCount; }
}
