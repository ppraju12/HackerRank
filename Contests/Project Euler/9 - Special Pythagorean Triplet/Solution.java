// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler009/problem

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
            
            /* Equations to consider for a Pythagorean triplet:
                1. a + b + c = n
                2. If we derive equation for c, it would be c = n - a - b
                3. If we substitute the equation for c in the 1st equation, 
                   we would get the equation as:
                   b = (a^2 - (a-n)^2)/(2*(a-n)) */
            
            int maxProduct = 0;
            // For each number 'a' in the range of 1..n, check if we can find a 'b' which is a whole number
            for(int i=1; i<n; i++){
                // Based on the formula derived above, derive the numerator and denominator
                int numerator = (i * i) - (int)Math.pow(i-n, 2);
                int denominator = 2 * (i-n);
                // If the numerator is divisible by the denominator,
                // then check if the values of a, b & c are greater than zero.
                if(numerator % Math.abs(denominator) == 0){
                    int a = i;
                    int b = numerator / denominator;
                    int c = n - a - b;
                    // If all 3 are greater than zero, then they form a Pythagorean triplet.
                    // Hence, calculate the product and store the maximum product
                    if(a > 0 && b > 0 && c > 0){
                        int product = a * b * c;
                        maxProduct = Math.max(maxProduct, product);
                    }
                }
            }
            
            // If the maximum product is zero, 
            // it implies that a Pythagorean triplet cannot be formed with the given N
            maxProduct = maxProduct == 0 ? -1 : maxProduct;
            
            System.out.println(maxProduct);
        }
    }
}
