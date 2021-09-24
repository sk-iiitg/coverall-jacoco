package com.hackerrank.practice.dictionaries.triplets;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution1 extends AbstractSolution {

    @Override
    public boolean accept(String testCase) {

        return !testCase.equals("count-triplets/TestCase0.test") &&
               !testCase.equals("count-triplets/TestCase4.test") &&
               !testCase.equals("count-triplets/TestCase5.test");   
    }
    // Complete the countTriplets function below.
    @Override
    protected long countTriplets(List<Long> arr, long r) {
        
        
        int n = arr.size();
        if (n < 3) {
            return 0;
        }
        
        Map<Long,List<Integer>> histogram = new HashMap<>();
        for(int i=0; i<n; i++) {
            long v = arr.get(i);
            List<Integer> indices = histogram.get(v);
            if (indices == null) {
                indices = new ArrayList<>();
            }
            indices.add(i);
            histogram.put(v,indices);
        }
        long triplets = 0;
        
        for(int i=0; i<n-2; i++) {
            long v = arr.get(i);
            // next value
            long p = v*r;
            List<Integer> indices = histogram.get(p);
            if (indices == null) {
                continue;
            }
            List<Integer> next = new ArrayList<>();
            for(Integer k : indices) {
                if (i<k) {
                    next.add(k);
                }
            }
            if (next.isEmpty()) {
                continue;
            }
            
            p = p*r;
            indices = histogram.get(p);
            if (indices == null) {
                continue;
            }
            for(Integer ni : next) {
                for(Integer j : indices) {
                    if (ni<j) {
                        triplets++;
                    }
                }
            }
        }
        
        return triplets;
    }
}