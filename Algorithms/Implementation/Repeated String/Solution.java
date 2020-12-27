// Problem Link : https://www.hackerrank.com/challenges/repeated-string/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
	
	// Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        //Get the count of A's in the string given
        long countOfA = s.chars().filter(ch -> ch == 'a').count();
        //Get the No: of characters that we need to consider in the last repitition of the string
        long rem = n % s.length();
        //Get the count of A's in all the repititive strings that were completely included
        long result = countOfA * ( n - rem ) / s.length();
        //If some of the characters were left out, then find the count of A's in that substring and add it to the result
        if(rem != 0)
            result += s.substring(0, (int)rem). chars().filter(ch -> ch == 'a').count();
        
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
