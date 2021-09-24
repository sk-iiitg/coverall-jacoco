package com.hackerrank.practice.sorting.inversions;

import java.util.List;
import java.util.Arrays;


public class Solution3 extends AbstractSolution {



    /**
     * This function simply calls its recursive version. 
     */
    @Override
    protected long countInversions(List<Integer> arr) {

        int array[] = arr.stream()
                          .mapToInt(Integer::intValue)
                          .toArray();
        return countInversions(array);
    }
    
    private long countInversions(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return 0;
        }

        if (n == 2) {
            if (arr[0] > arr[1]) {
                int s = arr[1];
                arr[1] = arr[0];
                arr[0] = s;
                return 1;
            }
        }

        int mid = n / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, n);

        long ls = countInversions(left);
        long rs = countInversions(right);
        long cs = countSwaps(arr, left, right);

        return ls + rs + cs;
    }

    private static long countSwaps(int array[], int[] left, int[] right) {
        int i=0; int j=0;
        int k=0;
        long swaps = 0; 
        while(i<left.length && j<right.length) {

            int l = left[i];
            int r = right[j];
            if (l <= r) {
                array[k++] = l;
                i++;
            } else {
                array[k++] = r;
                j++;
                swaps += left.length - i;
            }
        }

        // we don't know at this stage which of
        // the two has caused the previous loop
        // to complete.
        while(i<left.length) {
            array[k++] = left[i++];
        }

        while(j<right.length) {
            array[k++] = right[j++];
        }

        return swaps;
    }
}
