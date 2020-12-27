// Problem Link: https://www.hackerrank.com/challenges/lilys-homework/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the lilysHomework function below.
    static int lilysHomework(int[] arr) {
        int result=Integer.MAX_VALUE;
        
        // Store the indices of each element into a HashMap
        Map<Integer, Integer> indices = new HashMap<Integer, Integer>();
        // Store the elements in their sorted order in an ArrayList
        ArrayList<Integer> sortedList = new ArrayList<Integer>();
        
        for(int k=1; k<=2; k++){
            int swapCount = 0;
            
            int[] a = new int[arr.length];
            for(int i=0; i<arr.length; i++){
                a[i] = arr[i];
                indices.put(arr[i], i);
                sortedList.add(arr[i]);
            }
            
            // The given array may need minimum swaps to the sorted list 
            // in either ascending or descending order of elements
            // So, we need to get the no: of swaps for both ascending and descending order list and
            // print the minimum value as result
            if(k%2==0)
                Collections.sort(sortedList, Collections.reverseOrder());
            else
                Collections.sort(sortedList);
            
            for(int i=0; i<a.length; i++){
                // Get the element from the sorted list to compare it with the current element of the array
                int sortedElement = sortedList.get(i);
                if(a[i] != sortedElement){
                    // Get the position of (ith element of Sorted List) in the initial array from the Map
                    int j = indices.get(sortedElement);
                    // Update the map with the new index for the current element of the array
                    indices.put(a[i], j);
                    // Swap the current number with the new number based on their indices
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    
                    swapCount++;
                }
            }
            
            result = Math.min(result, swapCount);
            
            sortedList.clear();
        }
        
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = lilysHomework(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
