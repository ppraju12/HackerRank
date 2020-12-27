// Problem Link: https://www.hackerrank.com/challenges/queens-attack-2/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        int result=0;
        
        // Create a distance array with the max. possible distance the queen can attack on each direction
        // 0 - North West Diagonal
        // 1 - North
        // 2 - North East Diagonal
        // 3 - East
        // 4 - South East Diagonal
        // 5 - South
        // 6 - South West Diagonal
        // 7 - West
        int[] distanceArray = new int[8];
        // Derive the maximum distance for the horizontal and vertical directions
        distanceArray[1] = n - r_q;
        distanceArray[3] = n - c_q;
        distanceArray[5] = r_q - 1;
        distanceArray[7] = c_q - 1;
        // Derive the maximum distance of the diagonal based on the minimum of two directions involved
        distanceArray[0] = Math.min(distanceArray[1], distanceArray[7]);
        distanceArray[2] = Math.min(distanceArray[1], distanceArray[3]);
        distanceArray[4] = Math.min(distanceArray[5], distanceArray[3]);
        distanceArray[6] = Math.min(distanceArray[5], distanceArray[7]);
        
        System.out.println(Arrays.toString(distanceArray));
        
        for(int i=0; i<k; i++){
            int row = obstacles[i][0];
            int col = obstacles[i][1];
            
            int distance;
            if(row == r_q){
                distance = Math.abs(col - c_q) - 1;
                // Update the maximum distance the queen can attack on the West direction
                if(col < c_q && distance < distanceArray[7] )
                    distanceArray[7] = distance;
                // Update the maximum distance the queen can attack on the East direction
                else if(col > c_q && distance < distanceArray[3] )
                    distanceArray[3] = distance;
            }
            else if(col == c_q){
                distance = Math.abs(row - r_q) - 1;
                // Update the maximum distance the queen can attack on the South direction
                if(row < r_q && distance < distanceArray[5] )
                    distanceArray[5] = distance;
                // Update the maximum distance the queen can attack on the North direction
                else if(row > r_q && distance < distanceArray[1] )
                    distanceArray[1] = distance;
            }
            else if(Math.abs(row - r_q) == Math.abs(col - c_q)){
                distance = Math.abs(row - r_q) - 1;
                if(col < c_q){
                    // Update the maximum distance the queen can attack on the South West direction
                    if(row < r_q && distance < distanceArray[6] )
                        distanceArray[6] = distance;
                    // Update the maximum distance the queen can attack on the North West direction
                    else if(row > r_q && distance < distanceArray[0] )
                        distanceArray[0] = distance;
                }
                else{
                    // Update the maximum distance the queen can attack on the South East direction
                    if(row < r_q && distance < distanceArray[4] )
                        distanceArray[4] = distance;
                    // Update the maximum distance the queen can attack on the North East direction
                    else if(row > r_q && distance < distanceArray[2] )
                        distanceArray[2] = distance;
                }
                
            }
        }
        
        System.out.println(Arrays.toString(distanceArray));
        
        // Sum up the distance across all directions
        // to get the number of positions the queen can attack
        for(int i=0; i<8; i++)
            result += distanceArray[i];
            
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
