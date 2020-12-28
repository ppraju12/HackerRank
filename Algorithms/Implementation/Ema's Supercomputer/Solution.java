// Problem Link: https://www.hackerrank.com/challenges/two-pluses/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static int rows, columns;
    
    // Complete the twoPluses function below.
    static int twoPluses(String[] grid) {
        rows = grid.length;
        columns = grid[0].length();
        
        int result = 0;
        
        // Assertions:
        // 1. The Area of a Plus of length can be derived as (4*length) + 1
        // 2. The cells that formed the first Plus shouldn't be considered 
        //    while finding the second Plus with Maximum Area
        
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                // Convert the grid from an Array of Strings to 2D char Array
                char[][] gridArray = new char[rows][columns];
                for(int k=0; k<rows; k++)
                    gridArray[k] = grid[k].toCharArray();
                // Get the length of the Plus that can be formed with the current cell as center
                int length = lengthOfPlus(gridArray, i, j);
                // As we need to find out the length of the next Plus
                // which doesn't overlap with any of the cells that formed the current Plus,
                // we need to check all the combinations 
                // which yield the maximum Product of the areas of both of the Plus
                for(int k=0; k<=length; k++){
                    // Make the cells that formed the Plus as Bad cells, 
                    // so that they won't be considered again while finding the second Plus
                    clearPlus(gridArray, i, j, k);
                    // Get the Product of the Areas of both the Pluses
                    int product = (4*k + 1) * getMaxArea(gridArray);
                    // Store the maximum product in the result
                    result = Math.max(result, product);
                }
            }
        }
        
        return result;
    }
    
    private static int lengthOfPlus(char[][] grid, int i, int j){
        int length = 1;
        // Increment the length while looking up the adjacent cells on all 4 directions,
        // only if all of those are good cells
        while(i-length >= 0 && i+length < rows && j-length >= 0 && j+length < columns && 
        grid[i-length][j] == 'G' && grid[i+length][j] == 'G' && grid[i][j-length] == 'G' && grid[i][j+length] == 'G')
            length++;
        
        return length-1;
    }
    
    private static void clearPlus(char[][] grid, int i, int j, int length){
        // Make the cells that formed the Plus as Bad cells, 
        // so that they won't be considered again while finding the second Plus
        grid[i][j] = 'B';
        for(int k=1; k<=length; k++){
            grid[i][j-k] = 'B';
            grid[i][j+k] = 'B';
            grid[i-k][j] = 'B';
            grid[i+k][j] = 'B';
        }
    }
    
    private static int getMaxArea(char[][] grid){
        int maxArea = Integer.MIN_VALUE;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                if(grid[i][j] == 'G'){
                    int area = 4*lengthOfPlus(grid, i, j) +1;
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        int result = twoPluses(grid);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
