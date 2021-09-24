/**
 * 
 */
package com.hackerrank.practice.arrays.manipulation;


import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


import com.hackerrank.core.SolutionTestBase;
import com.hackerrank.core.TestCase;
import com.hackerrank.core.DefaultChallengeResult;

import org.junit.Test;

/**
 * Class <b>AbstractSolutionTest</b>. This class defines the base logic required
 * to read the testcase data for the array manipulation challenge and then defer
 * to concrete classes the creation of the solution instance.
 * 
 * @author Christian Vecchiola
 *
 */
public abstract class AbstractSolutionTest extends SolutionTestBase<Input,DefaultChallengeResult<Long>> {
	
	@Test
	public void testCase4() throws IOException {
		
		this.runTestCase("array-manipulation/TestCase4.test");
	}
	
	
	@Test
	public void testCase3() throws IOException {
		
		this.runTestCase("array-manipulation/TestCase3.test");
	}
	
	@Test
	public void testCase2() throws IOException {
		
		this.runTestCase("array-manipulation/TestCase2.test");
	}
	
	@Test
	public void testCase1() throws IOException {
		
		this.runTestCase("array-manipulation/TestCase1.test");
	}
	
	@Test
	public void testCase0() throws IOException {
		
		this.runTestCase("array-manipulation/TestCase0.test");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TestCase<Input,DefaultChallengeResult<Long>> readTestCase(InputStream testInput) throws IOException {


		try(Scanner scanner = new Scanner(testInput)) {
			String[] nm = scanner.nextLine().split(" ");

			int n = Integer.parseInt(nm[0]);

			int m = Integer.parseInt(nm[1]);

			int[][] queries = new int[m][3];

			for (int i = 0; i < m; i++) {
				String[] queriesRowItems = scanner.nextLine().split(" ");
				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

				for (int j = 0; j < 3; j++) {
					int queriesItem = Integer.parseInt(queriesRowItems[j]);
					queries[i][j] = queriesItem;
				}
			}

			Input input = new Input(n,m, queries);


			long max = scanner.nextLong();

			DefaultChallengeResult<Long> result = new DefaultChallengeResult<>(max);

			return new TestCase<>(input, result);

	}
        
	}

}
