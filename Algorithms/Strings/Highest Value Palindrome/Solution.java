// Problem Link: https://www.hackerrank.com/challenges/richie-rich/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    
    static Set<Integer> minReplacementSet = new HashSet<Integer>();
    static int extraReplacements = 0;
    
    // Complete the highestValuePalindrome function below.
    static String highestValuePalindrome(String s, int n, int k) {
        String result = "";
        char[] c = s.toCharArray();
        // Find the minimum no: of replacements needed to make a string as a palindrome
        int minReplacements = 0;
        for(int i=0; i<=(n-1)/2; i++){
            if(c[i] != c[n-1-i])
                minReplacements++;
        }
        
        if(minReplacements > k)
            // If the minimum no: of replacements is greater than the allowed replacements,
            // then the string cannot be a palindrome. Hence, return "-1"
            return "-1";
        else{
            // If the minimum no: of replacements is within the range of allowed replacements,
            // then replace the values at i and n-i with the greater number, if they are not the same.
            // Since the array index starts from 0, we need to consider it as i and n-1-i
            for(int i=0; i<=(n-1)/2; i++){
                if(c[i] != c[n-1-i]){
                    c[i] = c[i] < c[n-1-i] ? c[n-1-i] : c[i];
                    c[n-1-i] = c[i] > c[n-1-i] ? c[i] : c[n-1-i];
                    // Store the index at which the replacement was made,
                    // as we have a possibility to reduce extra replacements based on this
                    minReplacementSet.add(i);
                }
            }
        }
        
        System.out.println("After minimal replacements: "+Arrays.toString(c));
        // Derive the extra replacements that we can do, to get the highest possible number
        extraReplacements = k - minReplacements;
        
        // Replace the characters at every index (i) and the character at its corresponding mirror index (n-i) with 9
        // wherever possible, so that we get the highest possible number
        int left = 0;
        int right = n - 1;
        // As we're comparing both i and n-i at the same time,
        // we can just loop till we reach the center
        while(extraReplacements>0 && left<=(n-1)/2){
            if(n % 2 == 0){
                // If n is even, then there would be two digits in the center
                
                // So, if the extra replacements allowed is 1 and if that index hasn't been replaced before,
                // then we can't replace them, as we need 2 replacements.
                if(extraReplacements == 1 && !minReplacementSet.contains(left))
                    break;
                
                // Replace the characters at left and right index with 9, if it isn't already
                replaceLeftRight(c, left, right);
            }
            else{
                if(extraReplacements > 1){
                    // Even if n is odd, if the extra replacements is greater than 1,
                    // replace the characters at left and right index with 9, if it isn't already
                    replaceLeftRight(c, left, right);
                }
                else{
                    if(minReplacementSet.contains(left)){
                        // Even if the extra replacements allowed is 1, if that index has been replaced before,
                        // we can consider that we still have two extra replacements allowed.
                        // So, replace the characters at left and right index with 9, if it isn't already
                        replaceLeftRight(c, left, right);
                    }
                    else{
                        // If n is odd, there would only be one character in the center.
                        // If the extra replacements allowed is 1, if that index hasn't been replaced before,
                        // then replace the character in the center as 9, it isn't already
                        c[(n-1)/2] = '9';
                        extraReplacements--;
                    }
                }
            }
            
            // Move to the next index to check for replacements
            left++;
            right--;
        }
        
        System.out.println("After extra replacements: "+Arrays.toString(c));
        
        result = new String(c);
        
        return result;
    }
    
    // Method to replace the characters at index (i) and the character at its corresponding mirror index (n-i) with 9
    // if it isn't 9 already, so that we get the highest possible number 
    private static void replaceLeftRight(char[] c, int left, int right){
        if(c[left] != '9'){
            c[left] = '9';
            c[right] = '9';
            // If the same index was replaced during the minimum replacements,
            // then we don't need to consider the current replacement into account
            if(minReplacementSet.contains(left))
                extraReplacements--;
            else
                extraReplacements -= 2;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String s = scanner.nextLine();

        String result = highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
