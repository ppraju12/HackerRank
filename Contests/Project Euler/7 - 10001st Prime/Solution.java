// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler007/problem

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    // Initializing the max value as 10^4 th prime number + 1
    static int MAX_SIZE = 104730;
     
    // ArrayList to store all prime numbers
    static ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
       
    public static void main(String[] args) {
        // Add all the Prime Numbers to a list,
        // so that we can look up the index entered as input to get the Nth prime number
        loadPrimeNumbers();
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            // Get the Prime Number at the index entered as input
            System.out.println(primeNumbers.get(n-1));
        }
    }
    
    private static void loadPrimeNumbers(){
        // Create an array to keep track of all the numbers that are prime
        boolean [] isPrime = new boolean[MAX_SIZE];
        // Fill the array as true for all the numbers at the beginning.
        // Later update the flag as false for the composite numbers
        Arrays.fill(isPrime, true);
            
        for (int p = 2; p * p < MAX_SIZE; p++){
            // If isPrime[p] is unchanged till the end, it implies that the number is a prime number
            if(isPrime[p] == true){
                // Update the flag for all multiples of p as false, indicating they are composite numbers,
                // as they are divisible by p
                for (int i = p * p; i < MAX_SIZE; i += p) 
                    isPrime[i] = false;
            }
        }
        
        // Add all the numbers with the flag true to the list of Prime numbers
        for (int p = 2; p < MAX_SIZE; p++)
            if (isPrime[p] == true) 
                    primeNumbers.add(p);
    }
}