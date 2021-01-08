// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler016/problem

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            // Store 2^n in variable of Type BigInteger, as the value will be large when n reaches close to 10^4
            BigInteger power = new BigInteger("2");
            power = power.pow(n);
            // Convert the power value from BigInteger to String
            String powerString = power.toString();
            // Add all the digits to derive the sum
            int sum = 0;
            for(int i=0; i<powerString.length(); i++)
                sum += Integer.parseInt(powerString.substring(i, i+1));
            System.out.println(sum);
        }
    }
}