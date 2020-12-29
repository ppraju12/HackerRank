// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler008/problem

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
            int k = in.nextInt();
            String num = in.next();
            
            // Convert the String into a Character Array
            char[] c = num.toCharArray();
            
            int maxProduct = 0;
            // Calculate the Product of k consecutive numbers for all possible substrings and
            // store the maximum value
            for(int i=0; i<=n-k; i++){
                int product = 1;
                for(int j=0; j<k; j++){
                    product *= Character.getNumericValue(c[i+j]);
                }
                    
                maxProduct = Math.max(maxProduct, product);
            }
            
            System.out.println(maxProduct);
        }
    }
}
