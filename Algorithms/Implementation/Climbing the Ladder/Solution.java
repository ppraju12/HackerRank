// Problem Link: https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem

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
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer> result = new ArrayList<Integer>();
        TreeMap<Integer, Integer> rankMap = new TreeMap<Integer, Integer>();
        
        // Assertion: The Ranked List is already sorted in descending order
        // Derive the Rank for each Score and store it in a Sorted Map i.e., TreeMap
        int count=0;
        for(int value : ranked){
            if(!rankMap.containsKey(value)){
                count++;
                rankMap.put(value, count);
            }
        }
        
        System.out.println(rankMap);
        
        for(int score : player){
            List<Integer> listToRemove = new ArrayList<Integer>();
            
            // Edge Case: When the score is greater than the Leaderboard
            if(score > rankMap.lastKey()){
                result.add(1);
                continue;
            }
                
            for(int value : rankMap.keySet()){
                if(score > value){
                    // If the score is greater than the current value on Rank Map,
                    // then we don't need to compare it anymore for any of the scores that come in future
                    listToRemove.add(value);
                }
                else if(score < value){
                    // If the score is lesser than the curren value on Rank Map,
                    // then increment the rank and add it to the list
                    result.add(rankMap.get(value) + 1);
                    break;
                }
                else{
                    // If the score is equal to the curren value on Rank Map,
                    // then add the current rank to the list
                    result.add(rankMap.get(value));
                    break;
                }
            }
            
            // Remove all the values which are past the current score
            for(int value : listToRemove)
                rankMap.remove(value);
        }
        
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
