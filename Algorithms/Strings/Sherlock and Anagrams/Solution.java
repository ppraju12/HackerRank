// Problem Link: https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int result=0;
        Map<String, Integer> stringMap = new HashMap<String, Integer>();
        
        for(int i=0; i<s.length(); i++){
            for(int j=i+1; j<=s.length(); j++){
                // Read all possible substrings and sort them based on the characters of the string
                char[] token = s.substring(i,j).toCharArray();
                Arrays.sort(token);
                //Form the sorted string
                String sortedToken = new String(token);
                //If the sorted string is already found in the Map, then increase the count for it
                //Else, store the string in the Map with the value 1
                stringMap.put(sortedToken, stringMap.getOrDefault(sortedToken, 0) + 1);
            }
        }
        
        System.out.print(stringMap); 
        
        for(int count : stringMap.values()){
            result += count * (count - 1) / 2;
        }
        
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
