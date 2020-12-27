// Problem Link: https://www.hackerrank.com/challenges/the-time-in-words/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the timeInWords function below.
    static String timeInWords(int h, int m) {
        String result;
        
        // Store all possible numbers in words
        // Even though we don't use zero and fifteen, we just need to have a placeholder for those indices,
        // so that we can access the subsequent words based on the index
        String[] words = {"zero", "one", "two", "three", "four", "five", 
                          "six", "seven", "eight", "nine", "ten", "eleven", 
                          "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", 
                          "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", 
                          "twenty four", "twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine" };
        
        // Fill the time in words as needed
        if(m <= 30){
            if(m == 0)
                result = words[h]+" o' clock";
            else if(m == 15)
                result = "quarter past "+words[h];
            else if(m == 30)
                result = "half past "+words[h];
            else if(m == 1)
                result = "one minute past " + words[h];
            else
                result = words[m] + " minutes past " + words[h];
        }
        else{
            if(m == 45)
                result = "quarter to " + words[h+1];
            else if(m == 59)
                result = "one minute to " + words[h+1];
            else
                result = words[60-m] + " minutes to " + words[h+1];
        }
        
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String result = timeInWords(h, m);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
