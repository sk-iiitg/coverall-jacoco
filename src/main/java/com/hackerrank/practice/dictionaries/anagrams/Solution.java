package com.hackerrank.practice.dictionaries.anagrams;


import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

/**
 * Class <b>ChallengeSolution</b>. This class implements the solution to 
 * the Sherlock Anagrams challenge published in Hacker Rank:
 * 
 * https://www.hackerrank.com/challenges/sherlock-and-anagrams
 */
public class Solution extends DefaultChallengeSolution<Input,Integer> {


    public DefaultChallengeResult<Integer> execute(Input input) {

        int anagrams = this.sherlockAndAnagrams(input.getText());
        return new DefaultChallengeResult<>(anagrams);
    }
    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
    protected int sherlockAndAnagrams(String s) {
        
        // the number of all possible substrings of any size 
        // excluding the entire string, for a string of size
        // n>1 is given by the following number:
        // 
        // N(s,n) = n(n+1)/2 - 1 = (n*n + n - 2)/2
        //
        // this number includes all the possible true substrings
        // (i.e. len(substring) < len(s)) that can be generated.
        //
        // example: N(s,1) = 0
        // example: N(s,2) = s[0], s[1] = 2:1 = 2
        // example: N(s,3) = s[0], s[1], s[2], s[0,1] s[1,2] = 3:1 + 2:2 = 5
        // example: N(s,4) = s[0], s[1], s[2], s[3], s[0,1], s[1,2], s[2,3], s[1,2,3], s[2,3,4] = 4:1 + 3:2 + 2:3 = 9
        // 
        // it is easy to observe that:
        // N(s,1) = 0
        // N(s,n) = N(s, n-1) + n
        //
        // from this we derive:
        //
        // N(s,1) = 0, 
        // N(s,2) = N(s,1) + 2 = 0 + 2
        // N(s,3) = N(s,2) + 3 = 0 + 2 + 3 = 5
        // N(s,4) = N(s,3) + 4 = 5 + 4 = 0 + 2 + 3 + 4 = 9
        //
        // it is easy to observe that N(s,n) = n(n+1)/2 - 1.
        //
        // Now that we proved the relationship, we can do the following:
        // 
        // - given the string we generate all the possible substrings.
        // - and group them by sizes, within each size we sort alphabetically each 
        //   substring, compute the hash value and use it as a key for a counter.
        // - we build a histogram with the above keys
        // - for wach value in the histogram we compute the possible number of pairs
        //   these can be computed as n(n-1)/2 where n is the number of elements.
        // - we sum all the pairs
        
        int size = s.length();
        if (size < 2) {
            return 0;
        }
        if (size == 2) {
            return s.charAt(0) == s.charAt(1) ? 1 : 0;
        }
        // this map will contain the frequency of anagrams
        // for each collection of distinct substrings that
        // are anagrams, there will be an entry containing
        // the number of substrings.
        Map<Integer,Integer> histogram = new HashMap<>();
        
        // we treat substring of size one differently because
        // we don't have to sort them.
        //
        for(int k=0; k<size; k++) {
            char[] c = new char[] { s.charAt(k) };
            int h = Arrays.hashCode(c);
            Integer frequency = histogram.get(h);
            histogram.put(h, frequency == null ? 1 : frequency + 1);
            
            // System.out.println("1 - a: " + c[0] + " h: " + h);
        }
        
        
        // ok this is the standard case, we first convert 
        // it into an array since we need to sort substrings
        for(int i=2; i<size; i++) {
            
            // this outer loop processes the class of substring
            // of size i: 2,3,4,....n-1
            
            
            // this is the temporary array that we use to
            // store the substring. It will be used to sort
            // it so that we can compute a uniform hash for
            // all anagrams.
            char[] anagram = new char[i];
            for(int j=0; j<size-i+1; j++) {
                
                
                // this inner loop processes all the substring
                // of size i (all those that can be generated
                // from the string once we fix the size to i)
                //
                s.getChars(j,j+i, anagram, 0);
                
                // StringBuilder sb = new StringBuilder();
                // sb.append("a: ");
                // sb.append(anagram);
                
                Arrays.sort(anagram);
                int h = Arrays.hashCode(anagram);
                Integer frequency = histogram.get(h);
                histogram.put(h, frequency == null ? 1 : frequency + 1);
                
                // sb.append(" h: ");
                // sb.append(h);
                // System.out.println(i + " - " + sb.toString());
            }
        }
        
        // now that we have computed all the frequencies for all
        // the possible substrings, we need to scan the histogram
        // and pick all the entries that are bigger than 1.
        
        int total = 0;
        
        for(int frequency : histogram.values()) {
            
            if (frequency == 1) {
                continue;
            } else if (frequency == 2) {
                // this is special hence we simplify the
                // the sum to just the value. 2*1/2 = 1
                total += 1;
                
            } else{
                
                total += (frequency)*(frequency - 1) / 2;
            }
            
        }
        
        return total;
    }

}