// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler014/problem

import java.io.*;
import java.util.*;

public class Solution {
    // Maximum value as input + 1
    static int MAX_SIZE = 5000001;
    // Array to store the steps count starting from each index i to reach the end which is '1'
    static int[] stepsCount = new int[MAX_SIZE];
    // Array to store the number less than i with the maximum no: of steps to reach the end
    static int[] result = new int[MAX_SIZE];
    
    public static void main(String[] args) {
        // Pre-compute the value at each index i with the value that is less than i and has maximum no: of steps
        precompute();
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            // The value less than n and with maximum no: of steps would be avaiable at the nth index
            System.out.println(result[n]);
        }
    }
    
    private static void precompute(){
        int maxSteps = 1, maxValue = 1;
        // Derive the value at index i (from 1..Max Size) as the value less than i with maximum no: of steps 
        for(int i=1; i<MAX_SIZE; i++){
            // Get the no: of steps needed to reach 1 starting from i
            int steps = getStepsCount(i);
            
            // If the no: of steps is greater than max steps till that index, 
            // then update that as max steps and also update the max value as the current index
            if(steps >= maxSteps){
                maxSteps = steps;
                maxValue = i;
            }
            
            // Populate the value with the max. no: of steps till the index i as the result
            result[i] = maxValue;
        }
    }
    
    private static int getStepsCount(long n){
        Stack<Long> sequence = new Stack<Long>();
        
        int steps = 0;
        // Add the set of numbers in the Collatz sequence to a Stack, 
        // till we reach a number for which the Step Count is determined already
        while(n > 1){
            if(n < MAX_SIZE && stepsCount[(int) n] != 0){
                steps = stepsCount[(int) n] - 1;
                break;
            }
            // Derive the next number of the Collatz sequence and add it to the Stack
            n = (n % 2 == 0) ? (n / 2) : (3 * n + 1);
            sequence.push(n);
        }
        // Update the Step Count for all the numbers in the Stack
        while(!sequence.empty()){
            n = sequence.pop();
            steps++;
            if(n < MAX_SIZE)
                stepsCount[(int) n] = steps;
        }
        
        // Return the no: of steps to reach 1 starting from n
        return steps;
    }
}