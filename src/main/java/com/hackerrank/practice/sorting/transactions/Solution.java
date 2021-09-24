package com.hackerrank.practice.sorting.transactions;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;


// Class <b>Solution</b>. Implements the solution to the Hacker Rank problem
// Fraudolent Financial Transactions:
//
// https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem
//
// The solution uses a sliding window that is updated by taking into account the
// oldest and the newest element to add remove/add. Insertion and removal are
// performed with binary search on the sliding window since it is always ordered.
//
// complexity is O(n*2logn)
//
public class Solution extends DefaultChallengeSolution<Input,Integer> {



    @Override
    public DefaultChallengeResult<Integer> execute(Input input) {
        int notifications = this.activityNotifications(input.getExpenditure(), input.getDays());
        return new DefaultChallengeResult<>(notifications);
    }
    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */

    protected int activityNotifications(List<Integer> expenditure, int d) {
    // Write your code here
        int alerts = 0;
        // we prepare the initial list because
        // this is only needed once.
        List<Integer> trail = new ArrayList<>();
        int oldest = expenditure.get(0);
        trail.add(oldest);
        for(int t=1; t<d; t++) {
            trail.add(expenditure.get(t));
        }
        Collections.sort(trail);
        int before = Integer.MIN_VALUE;
        int after = Integer.MAX_VALUE;
        int pivot = d/2;
        
        float median = trail.get(pivot);
        boolean isEven = d % 2 == 0;
        if (isEven) {
            median = (median + trail.get(pivot-1)) / 2;
        }
        // now we loop for the remaining items.
        int n = expenditure.size();
        for(int i=d; i<n; i++) {
            if (i>d) {
                int next = expenditure.get(i-1);
                
                // we need now to update the median, the behaviour
                // changes based on whether the size is either odd
                // or even.
                if (oldest != next) {
                    updateTrail(trail, next, oldest);
                    median = trail.get(pivot);
                    if (isEven) {
                        median = (median + trail.get(pivot-1)) /  2;
                    }
                }
                oldest = expenditure.get(i-d);
            }
            int current = expenditure.get(i);
            if (current >= 2*median) {
                // System.out.println("alert: " + current);
                alerts++;
            }
            // System.out.println("i: " + i + ", oldest: "+ oldest + ", trail: " + trail + ", median: " + median + ", value: " + current);
            

        }
        return alerts;
    }
    
    
    private static void updateTrail(List<Integer> trail, int next, int oldest) {
        
        if (next != oldest) {
            
            // ok we are now adding and 
            // removing two different values, hence
            // the median changes.
            
            // we can use bisection to add / remove
            // items from the list.
            int d = trail.size();
            int position = -1;
            int lower = 0;
            int upper = d-1;
            boolean done = false;

            // System.out.println("--Remove(o: " + oldest + ")-- [l: " + lower + ", u: " + upper + "]");
            
            while (!done) {
                position = (upper + lower) / 2;
                int candidate = trail.get(position);
                if (oldest < candidate) {
                    upper = position-1;
                } else if (oldest > candidate) {
                    lower = position+1;
                } else {
                    // in this case we have the
                    // same value we can add at
                    // the same position.
                    trail.remove(position);
                    done = true;
                }
            }

            // System.out.println("--Remove(o: " + oldest + ")-- [p: " + position +"]");
            
            // at this point we have a trail of size d-1
            // this implies the following:
            // - smallest index is max(position-1, 0)
            // - biggest index is d-2

            // ok now we can insert the next
            // item can leverage the position that
            // was computed by removing the oldest
            // item because the list is sorted.
            
            if (next > oldest) {

                // next is bigger than oldest, hence
                // we need to work from the item after
                // oldest until the end of the array.
                //
                lower = position > d-2 ? d-2 : position;
                upper = d-2;
            } else {

                // next is smaller than oldest, then the
                // last item we want to consider is position-1
                // because now at position sits an item that
                // is bigger than oldest.
                //
                lower = 0;
                upper = position < 1 ? 0 : position-1;
            }


            // System.out.println("--Insert(n: " + next + ")-- [l: " + lower + ", u: " + upper + "]");
            
            // simple check:
            int sample = trail.get(lower);
            if (next < sample) {
                trail.add(lower, next);
                return;
            }
            
            sample = trail.get(upper);
            if (next > sample) {
                trail.add(next);
                return;
            }
            
            
            done = false;
            while(!done) {
                position = (lower + upper) / 2;
                int candidate = trail.get(position);
                if (next < candidate) {
                    upper = position-1;
                    if (lower == upper) {
                        trail.add(position, next);
                        done = true;
                    }
                } else if (next > candidate) {
                    position++;
                    lower = position;
                    if (lower == upper) {
                        trail.add(position, next);
                        done = true;
                        
                    }
                } else{
                    
                    trail.add(position, next);
                    done = true;
                }
            }

            // System.out.println("--Insert(n: " + next + ")-- [p: " + position + "]");
            // System.out.println("-------------------------------------------------");
            //
            // for(int i=1; i<d; i++) {
            //    if (trail.get(i-1) > trail.get(i)) {
            //        throw new RuntimeException("Exception sorting failure");
            //    }
            // }
        }
    }

}


