// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler004/problem

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
            
            int largestPalindrome = 0;
            // Multiple all possible 3 digit numbers and
            // find the product which is a largest palindrome number that is less than N
            for(int i=100; i<=999; i++){
                for(int j=100; j<=999; j++){
                    // Derive the Product
                    int product = i * j;
                    // Reverse the Product value
                    int number = product;
                    int reversedNumber = 0;
                    while(number!=0){
                        reversedNumber *= 10;
                        reversedNumber += number % 10;
                        number /= 10;
                    }
                    // If the product is the same as the reversed number and less than N, 
                    // update the largest palindrome number, when it finds a greater product
                    if(product == reversedNumber && product < n)
                        largestPalindrome = Math.max(largestPalindrome, product);
                }
            }
            
            System.out.println(largestPalindrome);
        }
    }
}