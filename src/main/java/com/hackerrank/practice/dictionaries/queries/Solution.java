package com.hackerrank.practice.dictionaries.queries;

import java.util.List;
import java.util.Map;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

import java.util.ArrayList;
import java.util.HashMap;


public class Solution extends DefaultChallengeSolution<Input, List<Integer>> {



    @Override
    public DefaultChallengeResult<List<Integer>> execute(Input input) {

        List<Integer> frequencies = this.freqQuery(input.getQueries());
        return new DefaultChallengeResult<>(frequencies);

    }

    protected List<Integer> freqQuery(List<int[]> queries) {
        
        Map<Integer,Integer> histogram = new HashMap<>();
        Map<Integer,Integer> frequencies = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        int i = 0;
        int op = 0;
        int val = 0;


        for(int[] query : queries) {
                
            i++;
            op = query[0];
            val = query[1];
            
            if (op == 3) {
                boolean found = frequencies.containsKey(val);
                System.out.println("i: " + i + ", op: " + op + ", val: " + val + " -- found: " + found);

                result.add(found ? 1 : 0);
                continue;
            }
            
            Integer f = histogram.get(val);
            if (f == null) {
                f = 0;
            }
            // we check whether we need to remove
            // one occurrence of the value val 
            // and this is only relevant if the
            // value is in the histogram (f>0)
            if (op == 2 && f > 0) {
                
                // first we remove one item from
                // the bucket of the frequencies
                // associated to the current value
                Integer ff = frequencies.get(f);
                // this value should always be not
                // null or zero, because we have a 
                // value that is the same in the 
                // histogram.
                ff = ff-1;
                if (ff == 0) {
                    // this was the last value
                    // that has f occurrencies.
                    frequencies.remove(f);

                } else {
                    frequencies.put(f, ff);
                }
                
                // next we update the value of the
                // occurrence of val.
                f = f - 1;
                if (f == 0) {
                    histogram.remove(val);
                
                } else {
                    histogram.put(val, f);
                    // now because we have not
                    // remove it from the histogram
                    // this means that we need to
                    // update the frequencies for 
                    // a smaller bucket.
                    ff = frequencies.get(f);
                    if (ff == null) {
                        ff = 0;
                    }
                    frequencies.put(f, ff+1);
                }
                continue;
            } 
            
            if (op == 1) {
                // if we have found the value
                // that means that this has at
                // least one entry.

                Integer ff = null;
                
                if (f > 0) {
                
                    // ok there is at least one
                    // entry in the array which
                    // means there is associated
                    // frequency bucket to update.
                    
                    ff = frequencies.get(f);
                    ff = ff - 1;
                    if (ff == 0) {
                        frequencies.remove(f);
                    } else {
                        frequencies.put(f, ff);

                    }
                } 
                
                // now we address the add part.
                f = f+1;
                histogram.put(val,f);
                
                ff = frequencies.get(f);
                frequencies.put(f, ff == null ? 1 : ff+1);
                continue;
            }

        }

        return result;
    }
}