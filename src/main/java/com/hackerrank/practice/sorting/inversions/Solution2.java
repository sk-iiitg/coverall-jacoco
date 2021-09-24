package com.hackerrank.practice.sorting.inversions;

import java.util.List;

// class <b>Solution2</b>. By using the stabilised MergeSort
// with in-place variant. The key idea here is that merge sort
// minimises the adjacent swaps because of the following:
//
// - the base use case is ordering between adjacent elements
// - if we know the number of inversions in both left and right
//   array, the total number of inversions is their sum plus 
//   the inversions required to account for the crossing of
//   elements in the right which are smaller than elements in 
//   the left
//
// How do we compute the number of adjacent swaps required for
// merge sort the two sorted arrays?
//
//          a                         b
//  | 1 | 4 | 7 | 12 |  --- | 1 | 2 | 3 | 30 | 50 |
//    0   1   2    3          4   5   6   7    8
// 
// step k: (a=i, b=j) 
//         if a(i) > b(j) then a(k>i) are all bigger than b(j)
//         which means the number of swaps are those that take
//         b(j) to move before a(i) this are the number of 
//         elements between a(i) and the end of the left array.
//         We don't need to count for b(l) l<j because we have
//         already moved them into place.
//
// step 0: (a=0, b=0) a(0):1 > b(0):1 no (we don't need any swap)
//          a                         b
//  | 1 | 4 | 7 | 12 |  --- | 1 | 2 | 3 | 5 | 50 |
//    0   1   2    3          4   5   6   7    8
//        i                   j
// step 1: (a=1, b=0) a(1):4 > b(0):1 yes (we need three swaps) = an - ai + bj - 0 + 1 = mid - ai + bj + 1 = 3 - 1 + 0 + 1 = 3
//
//          a                         b
//  | 1 | 1 | 4 | 7 |  --- | 12 | 2 | 3 | 5 | 50 |
//    0   1   2    3          4   5   6   7    8
//            i                   j
//
// step 2: (a=2, b=1) a(2):4 > b(1):2 yes (we need three swaps) = an - ai + bj - 0 + 1 = mid - ai + bj + 1 = 3 - 2 + 1 + 1 = 3
//
//          a                         b
//  | 1 | 1 | 2 | 4 |  --- | 7 | 12 | 3 | 5 | 50 |
//    0   1   2   3          4   5    6   7    8
//                i                   j
// step 3: (a=3, b=3) a(i):4 > b(2):3 yes (we need three swaps) = an - ai + bj - 0 +1 = mid - ai + bj + 1 = 3 - 3 + 2 + 1 = 3
//
//          a                         b
//  | 1 | 1 | 2 | 3 |  --- | 4 | 7 | 12 | 5 | 50 |
//    0   1   2   3          4   5    6   7    8
//                i                   j
public class Solution2 extends AbstractSolution {


    /**
     * This function simply calls its recursive version. 
     */
    @Override
    protected long countInversions(List<Integer> arr) {

        return countInversions(arr, 0, arr.size()-1);
    }

    /**
     * This function computes the numbers of inversions (adjacent swaps) at
     * the general recurisve step for the subarray [lower, upper] and also
     * sorts it, while counting. The logic is the following:
     * <ul>
     * <li><i>lower >= upper</i>: 0 inversions</li>
     * <li><i>lower+1 = upper</i>: 1 inversion if arr(upper) < arr(lower)</li>
     * <li><i>lower < upper - 1</i>: enable the recursion on sub-arrays</li>
     * </ul>
     * Outside the edge cases the number of inversions is computed as the sum
     * of the number of inversions in the left array + the number of inversions
     * in the right array + the number of cross swaps to sort the two arrays into
     * a single array. This last step is computed by the function countCrossSwaps. 
     */
    private long countInversions(List<Integer> arr, int lower, int upper) {
        if (upper <= lower) {
            return 0;
        }

        if (upper == lower + 1) {
            int i = arr.get(lower);
            int j = arr.get(upper);
            if (j < i) {
                arr.set(lower, j);
                arr.set(upper, i);

                return 1;
            }            

            return 0;
        }

        // in all the other cases, we need to recurse.
        int mid = (upper + lower) / 2;
        long left = countInversions(arr, lower, mid);
        long right = countInversions(arr, mid+1, upper);

        return left + right + countCrossSwaps(arr, lower, mid, upper);
    }

    /**
     * This function counts the cross swaps required to provide a sorted array and
     * at the same time it sorts the array in place. Because we sort the array in
     * place the boundaries of left and right arrays change over time and therefore
     * the swaps are simply computed by executing the different between the indices
     * which are mapped to the range [0, n-1] of the entire array.
     */
    private long countCrossSwaps(List<Integer> arr, int lower, int mid, int upper) {

        int i=lower;
        int j=mid+1;
        int swaps = 0;
        while(i<j && j<=upper) {

            int left = arr.get(i);
            int right = arr.get(j);

            if (left > right) {
                swaps += j - i;
                arr.remove(j);
                arr.add(i, right);
                j++;
            } 

            i++;
        }

        return swaps;
    }
}