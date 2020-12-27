// Problem Link: https://www.hackerrank.com/challenges/bigger-is-greater/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the biggerIsGreater function below.
    static String biggerIsGreater(String w) {
        char[] c = w.toCharArray();
        
        // Find longest non-increasing suffix
        int i = c.length - 1;
        while (i > 0 && c[i - 1] >= c[i])
            i--;
        // Now i is the head index of the suffix
        
        // Are we at the last permutation already?
        if (i <= 0)
            return "no answer";
        
        // Let c[i - 1] be the pivot
        // Find rightmost element that exceeds the pivot
        int j = c.length - 1;
        while (c[j] <= c[i - 1])
            j--;
        // Now the value c[j] will become the new pivot
        // Assertion: j >= i
        
        // Swap the pivot with j
        char temp = c[i - 1];
        c[i - 1] = c[j];
        c[j] = temp;
        
        // Reverse the suffix
        j = c.length - 1;
        while (i < j) {
            temp = c[i];
            c[i] = c[j];
            c[j] = temp;
            i++;
            j--;
        }
        
        return String.valueOf(c);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int TItr = 0; TItr < T; TItr++) {
            String w = scanner.nextLine();

            String result = biggerIsGreater(w);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
