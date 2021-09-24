/**
 * 
 */
package com.hackerrank.practice.arrays.manipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 extends AbstractSolution {

    
    // Class Bucket. This class separates the integer array into its corresponding 
    // properties, lower and upper bound and the current sum. The sum is modelled
    // as a long since it is incrementally added. The class also contains the isLast
    // property which is used to determine whether we have completed the process of
    // adding.
    static class Bucket {
        public long sum;
        public int lower;
        public int upper;
        public boolean isLast = false;
        
        public Bucket(int[] interval) {
            this.lower = interval[0] - 1;
            this.upper = interval[1] - 1;
            this.sum = interval[2];
        }
        
        public Bucket(int lower, int upper, long sum) {
            this.lower = lower;
            this.upper = upper;
            this.sum = sum;
        }
        
        public Bucket[] add(Bucket interval) {
            
            this.isLast = false;
            
            Bucket[] split = null;
            
            int iLower = interval.lower;
            int iUpper = interval.upper;
            long iSum = interval.sum;
            
            // we don't need to sum anything because we have
            // disjoint intervals. Hence, we return NULL, no
            // changes are needed.
            if (this.lower > iUpper) {
                
                split = new Bucket[2];
                split[0] = new Bucket(iLower, iUpper, iSum);
                split[1] = this;
                split[1].isLast = true;
                
                return split;
            }
            
            if (this.upper < iLower) {
                split = new Bucket[2];
                split[0] = this;
                split[1] = new Bucket(iLower, iUpper, iSum);
                
                return split;
            }
            
            if (this.lower < iLower) {
                
                Bucket smaller = new Bucket(this.lower, iLower-1, this.sum);
                
                if (this.upper < iUpper) {
                    split = new Bucket[3];
                    split[0] = smaller;
                    split[1] = new Bucket(iLower, this.upper, this.sum + iSum);
                    split[2] = new Bucket(this.upper+1, iUpper, iSum);
                } else if (this.upper == iUpper){
                    split = new Bucket[2];
                    split[0] = smaller;
                    split[1] = new Bucket(iLower, iUpper, this.sum + iSum);
                    split[1].isLast = true;
                } else {
                    split = new Bucket[3];
                    split[0] = smaller;
                    split[1] = new Bucket(iLower, iUpper, this.sum + iSum);
                    split[2] = new Bucket(iUpper+1, this.upper, this.sum);
                    split[2].isLast = true; 
                }
            } else if (this.lower == iLower) {
                
                if (this.upper < iUpper) {
                    split = new Bucket[2];
                    split[0] = new Bucket(iLower, this.upper, this.sum + iSum);
                    split[1] = new Bucket(this.upper+1, iUpper, iSum);
                    
                } else if (this.upper == iUpper) { 
                    
                	this.sum += iSum;
                	this.isLast = true;
                    split = new Bucket[1];
                    split[0] = this;
                    
                } else {
                    split = new Bucket[2];
                    split[0] = new Bucket(iLower, iUpper, this.sum + iSum);
                    split[1] = new Bucket(iUpper +1, this.upper, this.sum);
                    split[1].isLast = true;
                }
                
            } else { 
            	// scenario: iLower < this.lower
                Bucket smaller = new Bucket(iLower, this.lower-1, iSum);
                
                if (this.upper < iUpper) {
                    split = new Bucket[3];
                    split[0] = smaller;
                    split[1] = new Bucket(this.lower, this.upper, this.sum + iSum);
                    split[2] = new Bucket(this.upper+1, iUpper, iSum);
                    
                } else if (this.upper == iUpper) {
                    split = new Bucket[2];
                    split[0] = smaller;
                    split[1] = new Bucket(this.lower, this.upper, this.sum + iSum);
                    split[1].isLast = true;
                    
                } else {
                    // scenario: this.upper > iUpper;
                    
                    split = new Bucket[3];
                    split[0] = smaller;
                    split[1] = new Bucket(this.lower, iUpper, this.sum + iSum);
                    split[2] = new Bucket(iUpper+1, this.upper, this.sum);
                    split[2].isLast = true;
                }
            }
            
            return split;
        }
        
        public String toString() {
            return String.format("(%d, %d, %d, %b)", this.lower, this.upper, this.sum, this.isLast);
        }
    }
    

    // this function adds the interval to the current
    // list, and return the current maximum value.
    // the behaviour is the following:
    // - if the list is empty, just adds the entry to 
    //   the list and return the maxium value.
    // - if the list is not empty, it adds and breaks the
    //   interval in portions that can be added.
    static long add(List<Bucket> spread, Bucket next, long max) {
        
        long newMax = max;
        
        if (spread.isEmpty()) {
            next.isLast = true;
            spread.add(next);
            return next.sum;
        } else {
        	int position = 0;
        	outerloop:
        	while(position < spread.size()) {
                // this will be replaced, an optimisation would be not to remove 
                // the bucket and change it in place, but it may gain very little
                // from actually removing and then adding.
                Bucket bucket = spread.remove(position);
                // we need to compensate the counter otherwise we are going to
                // skip entries in the list.
                
                // this operation can create either 1, 2, or 3 splits. In the first
                // case we have exact overlap. In the second case we either have one
                // edge matching the other, or two distinct intervals. In the third
                // case the intervals are partially overlapping with different edges.
                Bucket[] splits = bucket.add(next);
                
                
                // the list of splits is always ordered. This means that the last is
                // split is the one that either will be overlapping with the next in
                // original list or the last one. The scenario in which it is the last
                // one is when the current bucket ends after the given bucket to add.
                for(int s=0; s<splits.length; s++) {
                    Bucket split = splits[s];
                    
                    
                    if (split.sum > newMax) {
                        newMax = split.sum;
                    }
                    if (split.isLast) {
                        // in this case we have reached the last there is no point to
                        // keep going forward in the list as we have reached the end 
                        // of the overlap. We add this guy to the list and stop the 
                        // iteration.
                    	split.isLast = false;
                        spread.add(position,split);
                        // we need to stop the external iteration in this case
                        // hence we need to add the break to the label.
                        break outerloop;
                    } else if (s < splits.length - 1) {
                        spread.add(position,split);
                        position++;
                    } else {
                        // in this case we save the last for the next iteration
                        // because it may overlap with the next in the list.
                    	if (position == spread.size()) {
                    		split.isLast = true;
                    		spread.add(position,split);
                            position++;
                    	} else {
                            next = split;
                    		
                    	}
                    }
                }
            }
        }
        return newMax;
    }

    // Complete the arrayManipulation function below.
    protected long arrayManipulation(int n, int[][] queries) {
        List<Bucket> buckets = new ArrayList<>();
        long max = 0;
        
        long dumbMax = 0;
        long[] dumbArr = new long[n];
        
        
        for(int i=0; i<queries.length; i++) {
        	
            Bucket next = new Bucket(queries[i]);
            System.out.printf("%d: %s => { %d, %s }\n", i, next, max, buckets);
            max = add(buckets, next, max);
            
            long[] flattened = toDumb(n, buckets);
        	dumbMax = dumbAdd(dumbArr, queries[i], dumbMax);
            boolean hasError = max != dumbMax || !Arrays.equals(dumbArr, flattened);
            if (hasError) {
            	
            	System.out.printf("- dumb:  %d, %s\n", dumbMax, Arrays.toString(dumbArr));
            	System.out.printf("- smart: %d, %s\n ", max, Arrays.toString(flattened));
            	System.out.println(buckets);
            	
            	throw new RuntimeException("Test failed.");
            }
        }
        return max;
    }
    
    static long dumbAdd(long[] arr, int[] operation, long max) {
    	
    	for(int i=operation[0]-1; i<operation[1]; i++) {
    		long value = arr[i] + operation[2];
    		if (value > max) {
    			max = value;
    		}
    		arr[i] = value;
    	}
    	
    	return max;
    }
    
    static long[] toDumb(int n, List<Bucket> buckets) {
    	long[] flatten = new long[n];
    	for(Bucket bucket : buckets) {
    		for(int i=bucket.lower; i<=bucket.upper; i++) {
    			flatten[i] = bucket.sum;
    		}
    	}
    	
    	return flatten;
    }
    

}