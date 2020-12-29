// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler005/problem

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
            
            /*  Smallest multiple is nothing but LCM
                To find LCM, we need to multiply the numbers and then divide the product with their GCD  */
            int lcm = 1;
            for(int i=1; i<=n; i++){
                int product = i * lcm;
                lcm = product / gcd(i, lcm);
            }
            
            System.out.println(lcm);
        }
    }
    
    private static int gcd(int a, int b){
        if(b == 0)
            return a;
        return gcd(b, a % b);
    }
}
