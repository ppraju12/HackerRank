// Problem Link: https://www.hackerrank.com/challenges/magic-square-forming/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {
	
	// Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int result = Integer.MAX_VALUE;
        // Prefill all possible combinations of squares that form a magic square
        int[][][] pre = {
                            {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}},
                            {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},
                            {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},
                            {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}}, 
                            {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},
                            {{4, 3, 8}, {9, 5, 1}, {2, 7, 6}}, 
                            {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}}, 
                            {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}},
                        };
        
        // Compare each value in the given square vs each of the possible squares that we prefilled
        // and add the difference to the cost
        for(int i=0; i<pre.length;i++){
            int[][] pre_square = pre[i];
            int cost=0;
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    if(s[j][k] != pre_square[j][k]){
                        cost += Math.abs(s[j][k] - pre_square[j][k]);
                    }
                }
            }
            
            System.out.println("Cost for Pre-square " + i + ": " + cost);
            
            // If the cost is lesser than the value for previous combination,
            // then update the result with the cost to get current combination
            result = Math.min(result, cost);
        }
        
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
