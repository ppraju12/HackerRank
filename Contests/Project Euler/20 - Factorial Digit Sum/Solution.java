// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler020/problem

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            
            // Derive the Factorial of n
            BigInteger factorial = new BigInteger("1");
            for(int i=1; i<=n; i++)
                factorial = factorial.multiply(new BigInteger(String.valueOf(i)));
                
            // Store the sum of all the digits of the resultant factorial number
            String factorialString = factorial.toString();
            int sumOfDigits = 0;
            for(int i=0; i<factorialString.length(); i++)
                sumOfDigits += Integer.parseInt(factorialString.substring(i, i+1));
                
            System.out.println(sumOfDigits);
        }
        in.close();
    }
}