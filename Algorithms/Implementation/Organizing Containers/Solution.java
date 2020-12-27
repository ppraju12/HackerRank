// Problem Link:  https://www.hackerrank.com/challenges/organizing-containers-of-balls/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the organizingContainers function below.
    static String organizingContainers(int[][] container) {
        int[] containerList=new int[container.length];
        int[] typeList=new int[container.length];
        
        
        for(int i=0; i<container.length; i++){
            for(int j=0; j<container.length; j++){
                // Add the Total No: of balls (all types) in each container to an array
                containerList[i] += container[i][j];
                // Add the Total No: of balls of Type J (across all containers) to an array
                typeList[j] += container[i][j];
            }
        }
        
        // Sort the list based on the count
        Arrays.sort(containerList);
        Arrays.sort(typeList);
        
        System.out.println("containerList[] : " + Arrays.toString(containerList));
        System.out.println("typeList[] : " + Arrays.toString(typeList));
        
        // If the total no: of balls match in both the lists, it means they can be organized
        if(Arrays.equals(containerList, typeList))
            return "Possible";
        
        return "Impossible";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] container = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] containerRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < n; j++) {
                    int containerItem = Integer.parseInt(containerRowItems[j]);
                    container[i][j] = containerItem;
                }
            }

            String result = organizingContainers(container);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
