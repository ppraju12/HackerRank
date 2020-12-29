// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler003/problem

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
            long n = in.nextLong();
            
            // Initialize the longest prime factor with the given number
            long longestPrimeFactor = n;
            // Start off with the divisor 2
            long divisor = 2;
            // 1. Divide the given number with any number as long as it is divisible by another,
            //    till we reach the square root of it
            // 2. If it isn't divisible by a number, then increment it till we reach the square root */
            while(divisor <= Math.floor(Math.sqrt(longestPrimeFactor))){
                if(longestPrimeFactor % divisor == 0)
                    longestPrimeFactor /= divisor;
                else
                    divisor++;
            }

            System.out.println(longestPrimeFactor);
        }
    }
}