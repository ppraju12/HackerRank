// Problem Link: https://www.hackerrank.com/challenges/kaprekar-numbers/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the kaprekarNumbers function below.
    static void kaprekarNumbers(int p, int q) {
        boolean isFound = false;
        
        for(int i=p; i<=q; i++){
            int digits = 0;
            
            // Get the No: of digits in the current number
            int number = i;
            while(number > 0){
                number /= 10;
                digits++;
            }
            
            // Derive the Product as the Square of the number
            long product = (long) i * i;
            // Derive the Offset based on the No: of digits in the intial number
            long offset = (long) Math.pow(10, digits);
            
            // Product Divided by Offset gives the number on the Left Half
            // Product Mod Offset gives the number on the Right Half
            long sum = product/offset + product%offset;
            
            // If the sum matches the initial number,
            // then it is a Modified Kaprekar number
            if(sum == i){
                System.out.print(i + " ");
                isFound = true;
            }
        }
        
        if(!isFound)
            System.out.print("INVALID RANGE");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        kaprekarNumbers(p, q);

        scanner.close();
    }
}
