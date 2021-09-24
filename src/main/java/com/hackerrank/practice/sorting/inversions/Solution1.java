package com.hackerrank.practice.sorting.inversions;

import java.util.List;

// class <b>Solution1</b>. Solves the counting-inversions problem
// with a double loop. At each iteration we:
// - find the minimum of the remaining array
// - move it into position
// - remove it from the remaining array
// - insert in place the element that the minimum has replaced
// - determine the swaps as the distance between the original
//   index of the minimum, and the index in the right position
// the complexity is O(n2).
//
public class Solution1 extends AbstractSolution {

    /*
     * Complete the 'countInversions' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    @Override
    protected long countInversions(List<Integer> arr) {
    // Write your code here
        long inversions = 0;
        for(int i=0; i<arr.size(); i++) {
            int pos = getMin(i, arr);
            int swaps = pos - i;
            if (swaps > 0) {
                int value = arr.remove(pos);
                arr.add(i, value);
                inversions += swaps;
            }
        }
        
        return inversions;
    }
    
    private int getMin(int start, List<Integer> arr) {
        int min = arr.get(start);
        int index = start;
        for(int i=start+1; i<arr.size(); i++) {
            int other = arr.get(i);
            if (other < min) {
                min = other;
                index = i;
            }
        }
        
        return index;
    }

}

