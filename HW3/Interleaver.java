import java.util.Arrays;

/**
 * CISC 380
 * Algorithms Assignment 3
 *
 * Implements a dynamic programming solution to the Interleaving Strings problem.
 *
 * @author Jacob Sandish and Maddie McGreevy
 * Due Date: 10/29/23
 */

public class Interleaver{

    // create a private boolean 2D array for the memoization table
    private Boolean[][] memo;

    public Interleaver(){

    }//constructor

    /**
     * Finds if the two strings x and y are an interleaving of string z
     *
     * The string Z is an interleaving of X and Y if it can be obtained
     * by interleaving the characters in X and Y in a way that
     * maintains the left-to-right order of the c in X and Y:
     *
     * This algorithm runs in O(n x m) time. Creating the memoization table takes O(n x m) time.
     * The recursive call runs n and m times, while checking each conidition takes constant time.
     * This means that the recursive call runs in O(n x m) time. Everything else in the algorithm
     * runs in constant time. Thus, the overall runtime for this algorithm is O(n x m).
     *
     * @param x the first string that composes an interleaving
     * @param y the second string that composes an interleaving
     * @param z the string to check for an interleaving
     * @return True, if the string z is an interleaving of x and y. False otherwise.
     *
     */
    public Boolean isInterleaved(String x, String y, String z){

        // initialize the table lengths of n and m
        int n = x.length();
        int m = y.length();

        // Instantiate the memoization table.
        memo = new Boolean[n + 1][m + 1];

        // base case: if z does not equal the length of x+y, return false
        if (z.length() != x.length() + y.length()) {
            return false;
        }

        // call the recursive method isInterleavedRecursive
        return isInterleavedRecursive(x, y, z, n, m, memo);

    }//isInterleaved

    /**
     * Helper method for the isInterleaved method
     */
    private Boolean isInterleavedRecursive(String x, String y, String z, int i, int j, Boolean[][] memo) {
        // check if the result for this subproblem is already memoized
        if (memo[i][j] != null) {
            // if so then return that result
            return memo[i][j];
        }

        // base Case: if x and y are both empty, return true
        if (i == 0 && j == 0) {
            return true;
        }

        // if the base cases are not fulfilled, start with the result = false
        boolean result = false;

        // Subproblem 1: z character matches only x character
        // Also covers Subproblem 3: z character matches both x and y
        if (i > 0 && z.charAt(i + j - 1) == x.charAt(i - 1)) {
            result = isInterleavedRecursive(x, y, z, i - 1, j, memo);
        }

        // Subproblem 2: z character matches y character.
        if (j > 0 && z.charAt(i + j - 1) == y.charAt(j - 1)) {
            result = isInterleavedRecursive(x, y, z, i, j - 1, memo);
        }

        // Subproblem 4: z character matches neither x or y
        //  (covered by initial declaration of result variable)
        memo[i][j] = result;

        return result;
    }

    /**
     * Returns a string representation of the solution of the interleaved string problem.
     *
     * The return value is a string whose length is equal to z.
     * All characters in z are replaced by character "x" if they come from the string "x",
     * and all characters in z are replaced by the character "y" if they come from the string y.
     *
     * For example, on an input of x = "ab", y = "cd", and z = "abcd", then the output shall be the string "xxyy".
     *
     * @param x the first string that composes an interleaving
     * @param y the second string that composes an interleaving
     * @param z the string to check for an interleaving
     * @return A string representation of the solution, is there is no solution, return "NA"
     */
    public String getSolution(String x, String y, String z){
        // call isInterleaved to check if the string is interleaved
        if (isInterleaved(x, y, z)) {
            // if it is interleaved, call the method that will put the solution together
            return constructSolution(x, y, z, memo);
        } else {
            // if there is no solution, return NA
            return "NA";
        }

    }

    /**
     * Method that will construct the solution if the string is found to be interleaved
     */
    private String constructSolution(String x, String y, String z, Boolean[][] memo) {
        // initialize the indices for input strings x, y, and z
        int i = x.length();
        int j = y.length();
        int k = z.length();
        // initialize a string builder to the length of the interleaving string
        StringBuilder solution = new StringBuilder(z.length());

        // loop until the interleaved string (z) is fully complete
        while (k > 0) {
            // if there are still letters in x and x matches the current character in z or there
            // are no letters left in y, put x into the solution
            if (i > 0 && memo[i - 1][j] != null && z.charAt(k - 1) == x.charAt(i - 1) || j == 0) {
                // insert x into the solution string
                solution.insert(0, 'x');
                // decrement x index
                i--;
            // if there are characters in y, then add that into the solution
            } else {
                // insert y into the solution string
                solution.insert(0, 'y');
                // decrement y index
                j--;
            }
            // decrement z index
            k--;
        }

        // return the interleaved string created from x and y
        return solution.toString();
    }

}//class