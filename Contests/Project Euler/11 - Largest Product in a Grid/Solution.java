// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler011/problem

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Pad the grid with dummy cells to avoid boundary checks
        // Since we start from the Top-left cell, we need to add 3 blank cells to the left, right and bottom.
        // We don't need to add blank cells in the top, as we wouldn't be traversing upwards
        int[][] grid = new int[23][26];
        for(int grid_i=0; grid_i < 20; grid_i++){
            // To add 3 blank cells to the left, store the input from indices 3..22, 
            // leaving indices 0, 1 & 2 on the left as zero and the indices 23, 24 & 25 on the right as zero
            for(int grid_j=3; grid_j < 20+3; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        
        long maxProduct = 0;
        for(int i=0; i<20; i++){
            for(int j=3; j<20+3; j++){
                // Start off from the top left cell of the grid,
                // Check the directions - right, bottom, bottom left diagonal and bottom right diagonal
                // Derive the product only if we can find 4 adjacent numbers in that direction
                long rightProduct = 1, bottomProduct = 1, leftDiagonalProduct = 1, rightDiagonalProduct = 1;
                
                // Get the Product of numbers to the right
                for(int k=j; k<j+4; k++)
                    rightProduct *= grid[i][k];
                        
                // Get the Product of numbers to the bottom
                for(int k=i; k<i+4; k++)
                    bottomProduct *= grid[k][j];
                    
                // Get the Product of numbers to the bottom left diagonal
                for(int k=0; k<4; k++)
                    leftDiagonalProduct *= grid[i+k][j-k];
                    
                // Get the Product of numbers to the bottom right diagonal
                for(int k=0; k<4; k++)
                    rightDiagonalProduct *= grid[i+k][j+k];
                
                // Get the maximum product comparing the product of all the directions
                long product = Math.max( Math.max(rightProduct,bottomProduct),
                                        Math.max(leftDiagonalProduct,rightDiagonalProduct));
                                        
                // If the current cell has the maximum product, update that value
                maxProduct = Math.max(maxProduct, product);
            }
        }
        
        System.out.println(maxProduct);
    }
}