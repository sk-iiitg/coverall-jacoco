package com.hackerrank.practice.arrays.manipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Solution2 extends AbstractSolution {
	
	
	
	private class Accumulator {
		public long[][] split = null;
		public boolean isLast = false;
		public long max = 0;
	}
	
	
	public Accumulator addAndSplit(Accumulator acc, long[] bucket, long[] operation) {
		
		long bLower = bucket[0];
		long bUpper = bucket[1];
		long bSum = bucket[2];
		
		long oLower = operation[0];
		long oUpper = operation[1];
		long oSum = operation[2];
		
		// if the bucket ends at the same
		// spot or after the operation range
		// we have completed the search in
		// current list.
		acc.isLast = bUpper >= oUpper;
		
		// bucket is after the operation.
		// we have reached the end of the
		// search with this split.
		if (bLower > oUpper) {
			
			acc.split = new long[2][];
			acc.split[0] = operation;
			acc.split[1] = bucket;
			
			if (acc.max < bSum || acc.max < oSum) {
				acc.max = bSum > oSum ? bSum : oSum;
			}
			
			return acc;
		} 
		
		// bucket is before the operation
		// we have to keep going and move
		// it to the next.
		if (bUpper < oLower) {

			acc.split = new long[2][];
			acc.split[0] = bucket;
			acc.split[1] = operation;
			
			if (acc.max < bSum || acc.max < oSum) {
				acc.max = bSum > oSum ? bSum : oSum;
			}
			
			return acc;
		}
		
		long sum = bSum + oSum;
		// if we got here there is one intersection
		// for sure, and we don't care where it is
		// we simply check whether the sum is bigger
		// than the maximum.
		//
		if (acc.max < sum) {
			acc.max = sum;
		}
		
		if (bLower < oLower) {
			
			// it is implicit bUpper >= oLower, so we
			// don't need to check that otherwise we
			// would have gone out before.
			
			long[] smaller = new long[] { bLower, oLower-1, bSum};
			
			if (bUpper < oUpper) {
				
				acc.split = new long[3][];
				acc.split[0] = smaller;
				acc.split[1] = new long[] {oLower, bUpper, sum };
				acc.split[2] = new long[] {bUpper+1, oUpper, oSum };
				
			} else if (bUpper == oUpper) {
				
				acc.split = new long[2][];
				acc.split[0] = smaller;
				acc.split[1] = new long[] {oLower, bUpper, sum };
				
			} else {
				
				acc.split = new long[3][];
				acc.split[0] = smaller;
				acc.split[1] = new long[] {oLower, oUpper, sum};
				acc.split[2] = new long[] {oUpper+1, bUpper, bSum};
			}
			
			
		} else if (bLower == oLower) {
			
			if (bUpper < oUpper) {
				
				acc.split = new long[2][];
				acc.split[0] = new long[] {bLower, bUpper, sum};
				acc.split[1] = new long[] {bUpper+1, oUpper, oSum};
				
			} else if (bUpper == oUpper) { 
				
				acc.split = new long[1][];
				acc.split[0] = bucket;
				acc.split[0][2] = bSum + oSum;
				
			} else {
				
				acc.split = new long[2][];
				acc.split[0] = new long[] {bLower, oUpper, sum};
				acc.split[1] = new long[] {oUpper+1, bUpper, bSum};
				
			}
			
		} else {
			
			// this is bLower > oLower
			long[] smaller = new long[] {oLower, bLower-1, oSum};
			
			if (bUpper < oUpper) {
				
				acc.split = new long[3][];
				acc.split[0] = smaller;
				acc.split[1] = new long[] {bLower, bUpper, sum};
				acc.split[2] = new long[] {bUpper+1, oUpper, oSum};
				
			} else if (bUpper == oUpper) {
				
				acc.split = new long[2][];
				acc.split[0] = smaller;
				acc.split[1] = new long[] {bLower, bUpper, sum};
				
			} else {
				
				acc.split = new long[3][];
				acc.split[0] = smaller;
				acc.split[1] = new long[] {bLower, oUpper, sum};
				acc.split[2] = new long[] {oUpper+1, bUpper, bSum};
				
			}
		}
		
		
		
		return acc;
	}
	
	private void add(Accumulator acc, List<long[]> buckets, long[] operation) {
		
		int position = 0;
		while(position < buckets.size()) {
						
			long[] bucket = buckets.remove(position);
			
			acc = addAndSplit(acc, bucket, operation);
			
			int i=0;
			for(; i<acc.split.length-1; i++) {
				buckets.add(position, acc.split[i]);
				position++;
			}
			operation = acc.split[i];
			
			// we are now checking whether the accumulator
			// has identified that this is the last split
			// for the current operation, or we reached the
			// end of the list.
			if (acc.isLast || position == buckets.size()) {
				buckets.add(position, operation);
				break;
			}
		}
	}
	
	

	@Override
	protected long arrayManipulation(int n, int[][] queries) {
		
		// initialise the buckets with the
		// first entry in the operations.
		List<long[]> buckets = new ArrayList<>();
		buckets.add(new long[] {queries[0][0]-1, queries[0][1]-1, queries[0][2]});
		
		// initialise the accumulator
		//
		Accumulator acc = new Accumulator();
		acc.max = queries[0][2];
		
		for(int i=1; i<queries.length; i++) {

			long[] operation = new long[] { queries[i][0]-1, queries[i][1]-1, queries[i][2] }; 
			System.out.printf("%d: %s => %d, %s\n", i, Arrays.toString(operation), acc.max, this.toString(buckets));

			add(acc, buckets, operation);
		} 
		return acc.max;
	}
	
	protected StringBuilder toString(List<long[]> buckets) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		sb.append(buckets.stream()
			   .map(Arrays::toString)
			   .collect(Collectors.joining(", ")));
		sb.append("}");
		return sb;
	}
	
	

}
