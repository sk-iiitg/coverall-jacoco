package com.hackerrank.practice.sorting.toys;

import java.util.List;
import java.util.Map;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

import java.util.HashMap;

/**
 * This class provides a solution to the "Mark and Toys"
 * problem proposed by Hacker Rank here:
 * 
 * https://www.hackerrank.com/challenges/mark-and-toys/problem
 */
public class Solution extends DefaultChallengeSolution<Input,Integer> {


    @Override
    public DefaultChallengeResult<Integer> execute(Input input) {
        int toys = this.maximumToys(input.getPrices(), input.getBudget());
        return new DefaultChallengeResult<>(toys);
    }
    /*
     * Complete the 'maximumToys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY prices
     *  2. INTEGER k
     */

    protected int maximumToys(List<Integer> prices, int k) {
        int minPrice = Integer.MAX_VALUE;
        int maxPrice = Integer.MIN_VALUE;
        
        Map<Integer,Integer> screen = new HashMap<>();
        for(int price : prices) {
            if (price > k) {
                // the toy is not within budget
                // we don't need to consider it
                continue;
            }
            if (price < minPrice) {
                minPrice = price;
            }
            if (price > maxPrice) {
                maxPrice = price;
            }
            Integer toys = screen.get(price);
            if (toys == null) {
                screen.put(price,1);
            } else {
                screen.put(price, toys + 1);
            }
        }
        
        // we have not found a single
        // toy that is within budget.
        if (screen.size() == 0) {
            return 0;
        }
        int total = 0;
        // the rest
        int price=minPrice;
        while(k > 0 && price <= maxPrice) {
            Integer toys = screen.get(price);
            if (toys != null) {
                // we found a toy with this price.
                // we may have multiple toys with
                // the same price. Hence we need to
                // check how many we can affor with
                // the budget we have.
                int howMany = k / price;
                
                // now, we can only get the smaller 
                // of the two numbers. If howMany is
                // smaller that is as much we can afford
                // if toys is smaller there aren't enough
                // toys with that 
                if (howMany < toys) {
                   total += howMany;
                   k = k - howMany*price;
                } else {
                   total += toys; 
                   k = k - toys*price;
                }
            }
            // we now check the next price.
            price++;
        }
        
        return total;
    }

}

