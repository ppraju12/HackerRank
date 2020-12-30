// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler010/problem

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    // Initializing the max value as 10^6
    static int MAX_SIZE = 1000000;
     
    // Array to store A[i] with the sum of all prime numbers not greater than i
    static long[] sumOfNPrimeNumbers = new long[MAX_SIZE+1];
       
    public static void main(String[] args) {
        // Add the sum of all prime numbers not greater than 'i' and store it in an array at index 'i' i.e., A[i]
        // so that we can look up the index entered as input to get the sum
        loadPrimeNumbers();
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            // Lookup the index entered as input to get the sum
            System.out.println(sumOfNPrimeNumbers[n]);
        }
    }
    
    private static void loadPrimeNumbers(){
        // Create an array to keep track of all the numbers that are prime
        boolean [] isPrime = new boolean[MAX_SIZE+1];
        // Fill the array as true for all the numbers at the beginning.
        // Later update the flag as false for the composite numbers
        Arrays.fill(isPrime, true);
        
        for (int p = 2; p * p <= MAX_SIZE; p++){
            // If isPrime[p] is unchanged till the end, it implies that the number is a prime number
            if(isPrime[p] == true){
                // Update the flag for all multiples of p as false, indicating they are composite numbers,
                // as they are divisible by p
                for (int i = p * p; i <= MAX_SIZE; i += p) 
                    isPrime[i] = false;
            }
        }
        
        // All the numbers with the flag true are Prime numbers
        // Hence add the sum of all prime numbers not greater than i.
        // If a number is composite, fill it with the previous sum of prime numbers just to reuse the same value
        for (int p = 2; p <= MAX_SIZE; p++)
            if (isPrime[p] == true)
                sumOfNPrimeNumbers[p] = sumOfNPrimeNumbers[p-1] + p;
            else
                sumOfNPrimeNumbers[p] = sumOfNPrimeNumbers[p-1];
    }
}
