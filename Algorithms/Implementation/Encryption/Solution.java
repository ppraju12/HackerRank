// Problem Link: https://www.hackerrank.com/challenges/encryption/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the encryption function below.
    static String encryption(String s) {
        String result="";
        int rows, columns;
        
        // Remove blank spaces
        s = s.trim();
        
        // Since the Floor * Ceil of the Square Root doesn't always be >= Length,
        // We need to Round of the Square Root and use that as Rows
        rows = (int)Math.round(Math.sqrt(s.length()));
        // If the Rows count is >= Square Root value, then we need to use the same count for columns,
        // Else, we need to increment it by 1 and use that as columns, so that Rows * Columns would be >= Length
        columns = (rows >= Math.sqrt(s.length())) ? rows : rows+1;
        
        System.out.println(s.length() + " -> "+ rows + " * " + columns);
        
        for(int c=0; c<columns; c++){
            for(int r=c; r<s.length(); r+=columns){
                result += s.substring(r,r+1);
            }
            result+=" ";
        }
        
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
