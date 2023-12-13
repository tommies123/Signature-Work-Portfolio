import java.util.Arrays;

/**
 * CISC 380
 * Algorithms Assignment 3 EXTRA CREDIT
 * 
 * 
 * Implements an iterative dynamic programming solution to find the subarray of maximum sum, of a given array of numbers.
 * 
 * @author Jacob Sandish and Maddie McGreevy
 * Due Date: 10/29/23
 * 
 */


public class MaxSum{

    /**
     * Returns the sum of the subarray with the maximum sum from the given array of integers.
     * 
     * 
     * @param a an array of integers
     * @return the sum of the subarray with the maximum sum.
     */
    public static int maxSumSubarray(int[] a){

        // initialize array d to store the contiguous array that holds the max sum
        int[] d = new int[a.length];

        // base case: the first element of the array is the maximum subarray
        d[0] = a[0];

        // initialize maxSum to store the maximum value found
        int maxSum = d[0];

        // loop through input array to calculate each subarray max
        for (int i = 1; i < a.length; i++) {
            // this compares the current element (a[i]) to the previous sum subarray + the current element (d[i-1] + a[i]),
            // whichever is larger will be stored in d[i]
            d[i] = Math.max(a[i], d[i-1] + a[i]);
        }

        // loop through the calculated maxes to find which subarray will hold the greatest max
        for(int i = 1; i < d.length; i++) {
            // if the calculated sum subarray is greater than the current maxSum
            if(d[i] > maxSum) {
                // set the new maxSum to the calculated sum subarray
                maxSum = d[i];
            }
        }

        // return the maximum sum subarray found
        return maxSum;

    }//maxSubArray

}//class