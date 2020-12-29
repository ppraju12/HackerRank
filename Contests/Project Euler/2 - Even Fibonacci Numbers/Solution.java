// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler002/problem

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
            // Base case 1: when n equals to 1, there wouldn't be any even numbers
            if(n==1){
                System.out.println("0");
                continue;
            }
            // Base case 2: when n equals to 2, there would only be one even number i.e., 2
            if(n==2){
                System.out.println("1");
                continue;
            }
            
            long sum = 0;
            long left = 0;
            long right = 1;
            long value = 2;
            // Add all the even values to the sum, till it reaches n
            while(value<n){
                if(value%2 == 0)
                    sum += value;
                // After adding the value to the sum, consider the previous value as left and new value as right
                left = right;
                right = value;
                // Add left and right to get the next Fibonacci number
                value = left + right;
            }
            System.out.println(sum);
        }
    }
}