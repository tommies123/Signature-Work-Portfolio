
/**
 * CISC 380
 * Algorithms Assignment 3 EXTRA CREDIT
 * 
 * Driver file for the MaxSum class
 * Tests the maxSumSubarray method.
 * 
 * 
 *  This driver file contains a single example test case to get you started.
 *  You should include more test cases to ensure that your implemenetation works correctly.
 *  You do NOT need to submit this file.
 * 
 * @author Maddie McGreevy and Jacob Sandish
 * Due Date: 10/29/23
 */



public class MaxSumDriver{


    public static void main(String[] args){
        try{


            int[] array = {4,-6,3,1,9,-8,2,-1,13};

            System.out.println( MaxSum.maxSumSubarray(array) );
        

        }catch( Exception e){
            e.printStackTrace();
        }
       
    }//main


}