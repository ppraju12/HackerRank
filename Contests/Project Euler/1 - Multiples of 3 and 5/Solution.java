// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler001/problem

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
            // Get the number of multiples of 3, 5.
            // We may have some common numbers which are divisible by both 3 & 5.
            // Hence we need to get the number of multiples of their LCM i.e., 15
            // so that we can exclude the sum of common numbers
            long countMultiplesOf3  = (long)Math.floor((n-1)/3);
            long countMultiplesOf5  = (long)Math.floor((n-1)/5);
            long countMultiplesOf15 = (long)Math.floor((n-1)/15);
            
            // To add 3, 6, 9, ... 99
            // we can do it by taking the common multiple 3 out and calculate it as 3 * (1 + 2 + 3 + ... + 33)
            
            // Also, we know that 1 + 2 + 3 + ... + n = n * (n+1) / 2
            
            // Using these assertions, we can derive the sum of multiples of 3, 5 & 15
            long sumMultiplesOf3    = 3  * countMultiplesOf3  * ( countMultiplesOf3  + 1 ) / 2;
            long sumMultiplesOf5    = 5  * countMultiplesOf5  * ( countMultiplesOf5  + 1 ) / 2;
            long sumMultiplesOf15   = 15 * countMultiplesOf15 * ( countMultiplesOf15 + 1 ) / 2;
            
            // Add the sum of multiples of 3 & 5.
            // And then subtract the sum of multiples of 15 to exclude the sum of numbers divisible by both 3 * 5
            long sum = sumMultiplesOf3 + sumMultiplesOf5 - sumMultiplesOf15;
            System.out.println(sum);
        }
    }
}