// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler015/problem

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {
    // Modulous - 10^9 + 7
    static BigInteger MODULO = new BigInteger("1000000007");
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            // Read No: of Rows and Columns
            int n = in.nextInt();
            int m = in.nextInt();
            // Store the Factorials of n, m & (n+m) in the indices 0, 1 & 2 respectively
            // n! & m! can be placed in either of 0 or 1, doesn't matter which index it is
            // but (n+m)! needs to be stored in index 2
            BigInteger[] factorials = new BigInteger[3];
            Arrays.fill(factorials, new BigInteger("1"));
            
            BigInteger product = new BigInteger("1");
            int k = 0;
            for(int i=1; i<=(n+m); i++){
                product = product.multiply(BigInteger.valueOf(i));
                
                if(i == n && n == m){
                    // If n = m,  then store the same Factorial Value in both of the indices 0 & 1
                    factorials[k++] = product;
                    factorials[k++] = product;
                }
                else if(i == n || i == m || i == n+m){
                    // If n != m, then store the Factorials of n & m in the indices 0 & 1 respectively
                    factorials[k++] = product;
                }
            }
            
            // No: of Routes can be derived as (n+m)! / ( n! * m! )
            BigInteger numberOfRoutes = factorials[2].divide( factorials[0].multiply(factorials[1]) );
            // Since the No: of Routes may be large, derive its modulous of 10^9 + 7
            int result = numberOfRoutes.mod(MODULO).intValue();
            System.out.println(result);
        }
    }
}