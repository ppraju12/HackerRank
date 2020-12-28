// Problem Link: https://www.hackerrank.com/challenges/matrix-rotation-algo/problem

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

public class Solution {

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int r) {
        int rows = matrix.size();
        int columns = matrix.get(0).size();
        
        // Convert the List into 2D Array
        Integer[][] grid = new Integer[rows][columns];
        for(int i=0; i<rows; i++){
            grid[i] = matrix.get(i).toArray(grid[i]);
        }
        // Derive the No: of Layers available in the grid
        int layers = Math.min(rows, columns)/2;
        // Loop through each layer and rotate all the elements within it
        for(int l=0; l<layers; l++){
            // Derive the Last Row and Column of the current Layer
            int lastRow = l + rows - (2 * l) - 1;
            int lastColumn = l + columns - (2 * l) - 1;
            // Derive the no: of elements in the current Layer
            int numberOfElements = (lastRow - l) * 2 + (lastColumn - l) * 2;
            // The pattern repeats after all the elements are rotated once.
            // So, we can just find the remainder and rotate it those many times
            int rotationsNeeded = r % numberOfElements;
            
            // If the rotations needed is zero, then continue with the next layer(if exists)
            if(rotationsNeeded == 0)
                continue;
                
            // System.out.println("Layer " + l + ": (" + lastRow + ", " + lastColumn + ")");
            // System.out.println("No. of elements: " + numberOfElements);
            
            // Store all the elements of the layer in a Queue    
            Queue<Integer> layerElements = new LinkedList<Integer>();
            // Add elements of first row to the queue
            for(int i=l; i<lastColumn; i++)
                layerElements.add(grid[l][i]);
            // Add elements of last column to the queue
            for(int i=l; i<lastRow; i++)
                layerElements.add(grid[i][lastColumn]);
            // Add elements of last row to the queue
            for(int i=lastColumn; i>l; i--)
                layerElements.add(grid[lastRow][i]);
            // Add elements of first column to the queue
            for(int i=lastRow; i>l; i--)
                layerElements.add(grid[i][l]);
            
            // System.out.println("Elements in Layer before rotating:\n" + layerElements);
            
            // Shift all the elements based on the no: of rotations needed
            // i.e., Dequeue the first element from the Queue and Enqueue it at the end
            for(int i=0; i<rotationsNeeded; i++)
                layerElements.add(layerElements.poll());
            
            // System.out.println("Elements in Layer after rotating:\n" + layerElements);
            
            // Update the current Layer of the Matrix with the elements from the rotated Queue
            
            // Add elements from the queue to the first row
            for(int i=l; i<lastColumn; i++)
                grid[l][i] = layerElements.poll();
            // Add elements from the queue to the last column
            for(int i=l; i<lastRow; i++)
                grid[i][lastColumn] = layerElements.poll();
            // Add elements from the queue to the last row
            for(int i=lastColumn; i>l; i--)
                grid[lastRow][i] = layerElements.poll();
            // Add elements from the queue to the first column
            for(int i=lastRow; i>l; i--)
                grid[i][l] = layerElements.poll();
        }
        
        // Print the final grid after rotations
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++)
                System.out.print(grid[i][j]+" ");
            System.out.println("");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
