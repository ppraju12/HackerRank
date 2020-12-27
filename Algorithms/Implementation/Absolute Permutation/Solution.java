// Problem Link: https://www.hackerrank.com/challenges/absolute-permutation/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the absolutePermutation function below.
    static int[] absolutePermutation(int n, int k) {
        int[] result = new int[n];
        int[] errorResult = {-1};
        
        // If k=0, then we can just print all the numbers in sequence, starting from 1 to n
        if(k==0){
            for(int i=0; i<n; i++)
                result[i]=i+1;
            
            return result;
        }
        // The permutation is not possible in the below 2 cases.
        // If n is an odd number or  
        // If n % 2k is non-zero, because if it is non-zero 
        // it implies that we cannot have both i+k and i-k in the list
        if(n%2 != 0 || n % (k*2) != 0)
            return errorResult;
        
        Set<Integer> permutationSet = new HashSet<Integer>();
        for(int i=0; i<n; i++){
			// Since the permutation is supposed to start from 1, need to add 1 to the difference and sum between i & k
            int diff = i+1 - k;	
            int sum = i+1 + k;
			// If i-k is greater than zero and not included in the permutation already,
			// then add that difference to the permutation
            if(diff>0 && !permutationSet.contains(diff)){
                result[i] = diff;
                permutationSet.add(diff);
            }
            else if(sum<=n && !permutationSet.contains(sum)){
				// If i+k is less than or equal n and not included in the permutation already,
				// then add that sum to the permutation
                result[i] = sum;
                permutationSet.add(sum);
            }
            else{
                return errorResult;
            }
        }
        
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] result = absolutePermutation(n, k);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
