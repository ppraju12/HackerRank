// Problem Link: https://www.hackerrank.com/challenges/3d-surface-area/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the surfaceArea function below.
    static int surfaceArea(int[][] A) {
        
        for(int i=0; i<A.length; i++){
            System.out.println(Arrays.toString(A[i]));
        }
        
        // Since we added two dummy rows and columns to pad the 2D Array with zeroes,
        // subtract the length by 2 to get the actual no: of rows and columns
        int H = A.length - 2;
        int W = A[0].length - 2;
        
        // The top and bottom surface area will always be the size of the grid i.e., H * W
        // Hence, we can multiply the size of the grid by 2
        int price = 2 * H * W;
        
        // Surface area of sides would be the difference between adjacent cells
        for(int i=0; i<=H; i++){
            for(int j=0; j<=W; j++){
                // Add the difference between current cell and adjacent cell in the same row
                price += Math.abs(A[i][j] - A[i][j+1]);
                // Add the difference between current cell and adjacent cell in the same column
                price += Math.abs(A[i][j] - A[i+1][j]);
            }
        }
        return price;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] HW = scanner.nextLine().split(" ");

        int H = Integer.parseInt(HW[0]);

        int W = Integer.parseInt(HW[1]);
        
        // Add two dummy rows and columns to pad the 2D Array with zeroes
        int[][] A = new int[H+2][W+2];
        
        // Fill the input in the Index 1..n based 2D Array
        for (int i = 1; i <= H; i++) {
            String[] ARowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < W; j++) {
                int AItem = Integer.parseInt(ARowItems[j]);
                A[i][j+1] = AItem;
            }
        }

        int result = surfaceArea(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
