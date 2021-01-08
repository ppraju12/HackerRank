// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler018/problem

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            // Read the input values for the triangle
            int[][] triangle = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j <= i; j++)
                    triangle[i][j] = in.nextInt();
            }
            // Get the maximum sum of the triangle starting from the top i.e., index (0,0)
            int maxSum = getMaxSum(triangle, 0, 0);
                
            System.out.println(maxSum);
        }
    }
    
    private static int getMaxSum(int[][] triangle, int i, int j){
        int n = triangle.length;
        // Base Case: Return the value at the index (i,j) when we reach the last row
        if(i == n-1)
            return triangle[i][j];
        // Add the current triangle number to each of the adjacent numbers of the next row moving downwards
        int maxSumOnLeft = triangle[i][j] + getMaxSum(triangle, i+1, j);
        int maxSumOnRight = triangle[i][j] + getMaxSum(triangle, i+1, j+1);
        // Return the maximum sum among those two adjacent numbers of the next row
        int maxSum = Math.max(maxSumOnLeft, maxSumOnRight);
        return maxSum;
    }
}}