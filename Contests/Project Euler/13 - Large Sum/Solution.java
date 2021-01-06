// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler013/problem

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {
        // Since we need to store 50 digit numbers, we need to use the data type BigInteger to store that
        BigInteger sum = new BigInteger("0");
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            // Read the input value as BigInteger and add it to the sum
            BigInteger value = in.nextBigInteger();
            sum = sum.add(value);
        }
        // Convert the BigInteger to String and store the first 10 chars as result
        String result = sum.toString().substring(0,10);
        System.out.println(result);
    }
}