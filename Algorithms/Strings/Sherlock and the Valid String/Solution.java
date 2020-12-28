// Problem Link: https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {
        // Store the frequency of each character in a Map
        Map<Character, Integer> frequencyMap = new HashMap<Character,Integer>();
        for(char c : s.toCharArray())
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        
        // Based on the frequency Map, 
        // create a TreeMap that stores the number of characters with the same frequency
        TreeMap<Integer, Integer> countMap = new TreeMap<Integer, Integer>();
        for(Integer value : frequencyMap.values()){
            countMap.put(value, countMap.getOrDefault(value, 0) + 1);
        }
        
        // If all the characters are repeated same number of times,
        // then it is a valid string
        if(countMap.size() == 1)
            return "YES";
        
        // If there are more than 2 frequencies found,
        // we cannot make the string valid by removing just one character.
        // So, it is an invalid string
        if(countMap.size() > 2)
            return "NO";
            
        if(countMap.size() == 2){
            // Read the 2 frequencies and the number of characters with those frequencies
            int firstKey = (Integer)countMap.firstKey();
            int secondKey = (Integer)countMap.lastKey();
            
            int firstCount = countMap.firstEntry().getValue();
            int secondCount = countMap.lastEntry().getValue();
            
            // If the first frequency is 1 and if there is just one character with frequency 1,
            // then the string becomes valid, as there would only be one frequency if we remove it
            if( firstKey == 1 )
                return firstCount == 1 ? "YES" : "NO";
            
            // If the difference between second frequency and first frequency is 1,
            // and if there is just one character with second frequency,
            // then the string becomes valid, as there would only be one frequency 
            // if we remove one occurence of the character with the higher frequency
            if( secondKey - firstKey == 1 )
                return secondCount == 1 ? "YES" : "NO";
        }
            
        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
