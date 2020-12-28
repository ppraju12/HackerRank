// Problem Link: https://www.hackerrank.com/challenges/non-divisible-subset/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'nonDivisibleSubset' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY s
     */

    public static int nonDivisibleSubset(int k, List<Integer> s) {
        int result;
        // Store the frequency of the remainders of all the elements in the list
        int[] remainderFrequency = new int[k];
        for(int i=0; i<s.size(); i++)
            remainderFrequency[s.get(i)%k]++;
        
        // Now that we have the frequencies of all the remainders,
        // we shouldn't include any remainder i when (k-i) is added to the resultant set.
        
        // However, remainder zero is an exceptional case,
        // as there wouldn't be a number with remainder (k-0).
        // Because the remainder would still be zero if it is divisible by k
        
        // So, if more than one number has the remainder as zero,
        // then consider just one of those, as the sum would be divisible by k if we include two of them
        result = Math.min(remainderFrequency[0], 1);
        // Similarly, when i = (k-i) which occurs only if k is even (Ex: k=4, then 2 = (4-2))
        // Even in this case, we need to consider just one of those,
        // as the sum would be divisible by k if we include two of them
        if(k%2==0)
            result += Math.min(remainderFrequency[k/2], 1);
            
        // And then, we can check all the numbers from 1..k/2, as the combination of i & (k-i) repeats after that
        for(int i=1; i<=k/2; i++){
            if(i != k - i)
                result += Math.max(remainderFrequency[i], remainderFrequency[k-i]);
        }
        
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.nonDivisibleSubset(k, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
