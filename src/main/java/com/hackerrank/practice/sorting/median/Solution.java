package com.hackerrank.practice.sorting.median;

import java.util.List;

import com.hackerrank.core.DefaultChallengeResult;
import com.hackerrank.core.DefaultChallengeSolution;

/**
 * Class <b>Solution</b>. This class implements the solution of the 
 * Hacker Rank problem:
 * 
 * https://www.hackerrank.com/challenges/find-the-median/
 * 
 * The implementation uses quick-select to perform the fastest ordering
 * of the sub-array containing the the median value.
 */
public class Solution extends DefaultChallengeSolution<Input, Integer> {

    /**
     * @inheritDoc
     * 
     * This implementation  unwraps the array of integers from the
     * input by calling {@link Input#getArray()}, invokes the function
     * {@link #findMedian(List)} and wraps the returned value in a
     * {@link DefaultChallengeResult} instance.
     */
    @Override
    public DefaultChallengeResult<Integer> execute(Input input) {

        int value = this.findMedian(input.getArray());
        return new DefaultChallengeResult<>(value);
    }

    /**
     * This is the main entry point for the solution to the problem. The 
     * implementation selects the the value of k as the expected position
     * of the median, and then initialises the {@link #quickSelect(int[], int, int, int)}
     * function with the array converted from a list to an array.
     * 
     * @param arr   a {@link List} implementation containing the sequence of 
     *              integer that requires scanning to find the median.
     * 
     * @return  the value of the median.
     */
    protected int findMedian(List<Integer> arr) {

        int length = arr.size();

        // this will be position of the median.
        int median = length / 2;
        int i=0;
        int[] array = new int[length];
        for(int v : arr) {
            array[i++] = v;
        }

        
        return quickSelect(array,0,length,median);
    }
 
    /**
     * <p>
     * This function implements the quick select algorithm which partially
     * sorts an array according by only redirecting the effort to the 
     * portion of the array in which we know the solution lies. This function
     * is helpful to solve the median problem because if we set k=median we 
     * obtain exactly what we want.
     * </p>
     * <p>
     * The implementation uses the partition function to organise the elements
     * in the array based on the pivot, which is selected as the middle value.
     * Those on the left of the pivot are smaller than it, those on the right
     * are bigger than the pivot. Both partitions are not necessarily ordered.
     * </p>
     * 
     * @param array a {@literal int} array representing the list of integer
     *              to select the smallest <i>k<i> numbers from.
     * @param left  a {@literal int} value representing the lower index of
     *              the bounded search (inclusive).
     * @param right a {@literal int} value representing the upper index of
     *              the bounded search (exclusive).
     * @param k     a {@literal int} value representing the number of smallest
     *              elements to select from the array.
     * @return  an {@literal int} value representing the value of <i>kth</i>
     *          smallest element.
     */
    protected int quickSelect(int[] array, int left, int right, int k) {

        if (left == right - 1) {
            return array[left];
        }
        int pivotIndex = (left + right) / 2;
        pivotIndex = partition(array, left, right, pivotIndex);
        if (pivotIndex == k) {
            return array[pivotIndex];
        }
        if (k < pivotIndex) {
            return quickSelect(array, left, pivotIndex, k);
        }
        return quickSelect(array, pivotIndex+1, right, k);
    }

    /**
     * <p>
     * This function partitions the list array of values into two groups:
     * <ul>
     * <li>a[left, pivotIndex-1]: items smaller (or equal) than pivot</li>
     * <li>a[pivotIndex+1, right-1]: items bigger than pivot</li>
     * </ul>
     * The implementation scans the array from <i>left</i> to <i>right</i>. 
     * Prior to the scan the pivot is swapped with the last element of the array.
     * whenever an element bigger than the pivot is encountered this is swapped
     * with a position at the tail of the array that is then decreased. When the
     * index matches the position at the tail the scan terminates and the pivot
     * is put back into the position based on the value of the last element to 
     * check.
     * </p>
     * 
     * @param array a {@literal int} array representing the list of integer
     *              numbers to partition.
     * @param left  a {@literal int} value representing the lower bound of the
     *              subarray being partitioned (inclusive).
     * @param right a {@literal int} value representing the upper bound of the
     *              subarray being partitioned (exclusive).
     * @param pivotIndex a {@literal int} value representing the index of the
     *                   pivot.
     * @return  the updated position of the pivot after the partition of the
     *          array.
     */
    protected int partition(int[] array, int left, int right, int pivotIndex) {
        int pivot = array[pivotIndex];
        array[pivotIndex] = array[right-1];
        array[right-1] = pivot;
        int backward = right-2;
        int index=left;

        // we loop until the index starting from 
        // the left reaches the index starting 
        // from the right.
        //
        while(index<backward) {
            int value = array[index];
            if (value > pivot) {
                array[index] = array[backward];
                array[backward] = value;
                backward--;
            } else {
                index++;
            }
        }
        // at this point we have reached
        // the last element we have not
        // checked.
        //
        if (array[backward] < pivot) {
            // in this case is already in place
            // hence the pivot goes after to this.
            backward++;
        }
        // we put the pivot in position.
        //
        array[right-1] = array[backward];
        array[backward] = pivot;

        return backward;
    } 
}
