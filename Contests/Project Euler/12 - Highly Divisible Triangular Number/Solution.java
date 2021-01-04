// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler012/problem

import java.io.*;
import java.util.*;

public class Solution {
    // The first triangle number with over 1000 divisors is 842161320 which has 1024 factors
    static int MAX_FACTORS = 1024;
    // And the number 842161320 is the sum of 41040 numbers, i.e., 41040 * 41041 / 2 = 842161320
    static int MAX_TRIANGLE_NUM = 41040;
    // Index at which the maximum prime number less than the maximum no: of factors is available
    // 1021 is the 172nd prime number. To store nth prime number at index n, store zero as well.
    static int MAX_PRIME_SIZE = 173;
    // Array to store all prime numbers
    static int[] primeNumbers = new int[MAX_PRIME_SIZE];
    // Array to store the first number that has over N divisors
    static long[] firstNDivisors = new long[MAX_FACTORS];
    
    public static void main(String[] args) {
        // Pre-compute the numbers that have over N divisors at index N and
        // store them in an array
        precompute();
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            // The First Triangle number with over N divisors 
            // would be available at index N in the precomputed array
            System.out.println(firstNDivisors[n]);
        }
    }
    
    private static void loadPrimeNumbers(){
        // Create an array to keep track of all the numbers that are prime
        boolean [] isPrime = new boolean[MAX_FACTORS];
        // Fill the array as true for all the numbers at the beginning.
        // Later update the flag as false for the composite numbers
        Arrays.fill(isPrime, true);
            
        for (int p = 2; p * p < MAX_FACTORS; p++){
            // If isPrime[p] is unchanged till the end, it implies that the number is a prime number
            if(isPrime[p] == true){
                // Update the flag for all multiples of p as false, indicating they are composite numbers,
                // as they are divisible by p
                for (int i = p * p; i < MAX_FACTORS; i += p) 
                    isPrime[i] = false;
            }
        }
        int index = 1;
        // Add all the numbers with the flag true to the list of Prime numbers
        for (int p = 2; p < MAX_FACTORS; p++)
            if (isPrime[p]) 
                primeNumbers[index++] = p;
    }
    
    private static void precompute(){
        long triangleNumber;
        int numberOfDivisors;
        // Load all the Prime numbers less than 1024
        loadPrimeNumbers();
        // Loop through each number till we reach the Triangle Number with over 1000 divisors
        for(int i=2; i<=MAX_TRIANGLE_NUM; i++){
            // Triangle number is formed by adding up all the numbers till the current number
            // Sum of 'n' natural numbers -> 1 + 2 + 3 + ... + n = n * (n+1) / 2
            triangleNumber = i * (i+1) / 2;
            // Get the No: of Divisors for the Triangle number
            numberOfDivisors = getNumberOfDivisors(triangleNumber);
            // If a triangle number with higher number of divisors is found,
            // update it in all the unassigned indices prior to that 
            // till we find an index with triangle number assigned
            if(numberOfDivisors <= MAX_FACTORS && firstNDivisors[numberOfDivisors-1] == 0){
                int x = numberOfDivisors - 1;
                
                while(x >= 1 && firstNDivisors[x] == 0)
                    firstNDivisors[x--] = triangleNumber;
            }
        }
    }
    
    private static int getNumberOfDivisors(long triangleNumber){
        int product = 1;
        // No: of divisors of a number can be derived 
        // by finding the number of exponents of prime numbers that are factors of the number
        // If a number can be calculated as a^m * b^n, where a & b are prime factors,
        // then the no: of divisors can be derived as (m+1)(n+1)
        for(int i=1; i<MAX_PRIME_SIZE; i++){
            int count = 0;
            // Get the number of exponents of a prime number with which the triangle number is divisible
            while(triangleNumber % primeNumbers[i] == 0){
                triangleNumber /= primeNumbers[i];
                count++;
            }
            
            product *= count + 1;
        }
        
        return product;
    }
}