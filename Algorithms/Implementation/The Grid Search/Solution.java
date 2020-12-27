// Problem Link: https://www.hackerrank.com/challenges/the-grid-search/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the gridSearch function below.
    static String gridSearch(String[] G, String[] P) {
        // Store all the indices at which the First Pattern is found in the Grid
        ArrayList<ArrayList<Integer>> firstPatternIndices = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<G.length; i++){
            Matcher m = Pattern.compile("(?=("+ P[0] + "))").matcher(G[i]);
            while(m.find())
                firstPatternIndices.add(new ArrayList<Integer>(Arrays.asList(i,m.start())));
        }
        
        System.out.println(firstPatternIndices);
        // Loop through each index and 
        // check if the subsequent lines of pattern are available in the same column starting from the current row
        for(ArrayList<Integer> index : firstPatternIndices){
            int row = index.get(0);
            int col = index.get(1);
            int count = 1;
            for(int i=1; i<P.length; i++){
                if(P[i].equals(G[row+i].substring(col, col+P[i].length())))
                    count++;
                else
                    break;
                
            }
            
            if(count == P.length)
                return "YES";
        }

        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] RC = scanner.nextLine().split(" ");

            int R = Integer.parseInt(RC[0]);

            int C = Integer.parseInt(RC[1]);

            String[] G = new String[R];

            for (int i = 0; i < R; i++) {
                String GItem = scanner.nextLine();
                G[i] = GItem;
            }

            String[] rc = scanner.nextLine().split(" ");

            int r = Integer.parseInt(rc[0]);

            int c = Integer.parseInt(rc[1]);

            String[] P = new String[r];

            for (int i = 0; i < r; i++) {
                String PItem = scanner.nextLine();
                P[i] = PItem;
            }

            String result = gridSearch(G, P);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
