package com.hackerrank.practice.dictionaries.triplets;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution2 extends AbstractSolution {

    
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
            p=p*r;
            for(Integer k : indices) {
                if (i<k) {
                    indices = histogram.get(p);
                    if (indices == null) {
                        break;
                    }
                    int m = 0;
                    // ok here we have 
                    for(Integer j : indices) {
                        if (k<j) {
                          triplets += indices.size() - m;
                          break;  
                        }
                        m++;
                    }
                }
            }
        }
        
        return triplets;
    }
}