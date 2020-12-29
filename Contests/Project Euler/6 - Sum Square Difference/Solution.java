// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler006/problem

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            
            // 1 + 2 + 3 + ... + n = n(n+1)/2
            // Derive the sum of 1..n and get the square of it
            long sum = (long) n * (n+1) / 2;
            long squareOfSum = (long) Math.pow(sum, 2);
            
            // 1^2 + 2^2 + 3^2 + ... + n^2 = n(n+1)(2n+1/6
            long sumOfSquares = (long) n * (n+1) * (2*n + 1) / 6;
            
            // Calculate the Absolute Difference by subtracting both
            long absoluteDifference = Math.abs(squareOfSum - sumOfSquares);
            System.out.println(absoluteDifference);
        }
    }
}
