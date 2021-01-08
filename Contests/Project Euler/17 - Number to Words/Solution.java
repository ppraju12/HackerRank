// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler017/problem

import java.io.*;
import java.util.*;

public class Solution {
    // Store the words for all the numbers less than 20 in an array
    static String[] uniqueWords = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                                   "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    // Store all the multiples of 10 in an array
    static String[] tens = {"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            long n = in.nextLong();
            // Get the value before each unit
            int trillion = new Double(n / Math.pow(10, 12)).intValue();
            int billion = new Double( (n / Math.pow(10, 9)) % 1000 ).intValue();
            int million = new Double( (n / Math.pow(10, 6)) % 1000 ).intValue();
            int thousand = new Double( (n / Math.pow(10, 3)) % 1000 ).intValue();
            int hundred = Math.round(n % 1000);
            
            String word = "";
            // Edge case: When n is zero
            if(n == 0){
                System.out.println("Zero");
                continue;
            }
            
            // Get the value before each unit in words
            if(trillion != 0)
                word += getWord(trillion) + " Trillion ";
            
            if(billion != 0)
                word += getWord(billion) + " Billion ";
            
            if(million != 0)
                word += getWord(million) + " Million ";
            
            if(thousand != 0)
                word += getWord(thousand) + " Thousand ";
            
            if(hundred != 0)
                word += getWord(hundred) + " ";
            
            // Remove any leading or trailing spaces in the word before printing
            System.out.println(word.trim());
        }
    }
    
    private static String getWord(int n){
        String word = "";
        // If n is greater than 100, then display the number of hundreds in words
        if(n/100 != 0)
            word += uniqueWords[n/100]+ " Hundred";
        // If the value formed with tens and units place combined is greater than zero and less than 20,
        // then get the word from the unique words array
        if(n%100 > 0 && n%100 < 20)
            word += " " + uniqueWords[n%100];
        else if( (n/10) % 10 != 0){
            // If the value formed with tens and units place combined is greater than 20,
            // then get the word from tens array
            word += " " + tens[(n/10) % 10] + " ";
            // If there is any value in the units place,
            // then get the word from the unique words array
            if(n % 10 != 0)
                word += uniqueWords[n%10];
        }
        // Remove any leading or trailing spaces in the word before returning
        return word.trim();
    }
}