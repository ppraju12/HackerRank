// Problem Link: https://www.hackerrank.com/challenges/bomber-man/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the bomberMan function below.
    static String[] bomberMan(int n, String[] grid) {
        if(n==1)
            return grid;
        
        int rows = grid.length;
        int columns = grid[0].length();
        
        // Create a Matrix based on the Input Grid
        char[][] matrix = new char[rows][columns];
        for(int i=0; i<rows; i++)
            matrix[i] = grid[i].toCharArray();
        
        if(n%2 == 0){
            // If n is even, then the whole grid is gonna be filled with bombs
            for(int i=0; i<rows; i++)
                Arrays.fill(matrix[i], 'O');
        }
        else{
            // The grid forms the same pattern for every 4 secs.
            // So we don't need to loop through all the stages/seconds to simulate it
            for(int seconds=3; seconds<=(n%4 + 4); seconds+=2){
                ArrayList<ArrayList<Integer>> bombLocations = new ArrayList<ArrayList<Integer>>();
                for(int i=0; i<rows; i++){
                    for(int j=0; j<columns; j++){
                        // If there is a bomb in a cell, then add it to the list of Bomb Locations
                        if(matrix[i][j] == 'O'){
                            ArrayList<Integer> position = new ArrayList<Integer>(Arrays.asList(i,j));
                            bombLocations.add(position);
                        }
                        else{
                            // Even if there is no bomb, just fill it up with 'O', 
                            // as it gets placed on the next even second
                            matrix[i][j] = 'O';
                        }
                    }
                }
                
                // Loop through all the locations of the bomb and detonate its adjacent cells
                Iterator<ArrayList<Integer>> bombIterator = bombLocations.iterator();
                while(bombIterator.hasNext()){
                    ArrayList<Integer> position = bombIterator.next();
                    int i = position.get(0);
                    int j = position.get(1);
                    // Detonate the bomb in cells adjacent to the current cell horizontally
                    // i.e., (i,j-1), (i,j) & (i,j+1)
                    for(int k=j-1; k<=j+1; k++){
                        if(k>=0 && k<columns){
                            matrix[i][k] = '.';
                        }
                    }
                    // Detonate the bomb in cells adjacent to the current cell vertically
                    // i.e., (i-1,j), (i,j) & (i+1,j)
                    for(int k=i-1; k<=i+1; k++){
                        if(k>=0 && k<rows){
                            matrix[k][j] = '.';
                        }
                    }
                }
            }
        }
        
        // Convert the Matrix to the grid of Strings
        for(int i=0; i<rows; i++){
            String data = "";
            for(int j=0; j<columns; j++){
                data += matrix[i][j];
            }
            grid[i] = data;
        }
        return grid;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] rcn = scanner.nextLine().split(" ");

        int r = Integer.parseInt(rcn[0]);

        int c = Integer.parseInt(rcn[1]);

        int n = Integer.parseInt(rcn[2]);

        String[] grid = new String[r];

        for (int i = 0; i < r; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] result = bomberMan(n, grid);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
