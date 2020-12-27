// Problem Link: https://www.hackerrank.com/challenges/almost-sorted/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the almostSorted function below.
    static void almostSorted(int[] arr) {
        int i,j;
        
        // Check if the array is already sorted and print yes, if it is sorted
        if(isSorted(arr)){
            System.out.println("yes");
            return;
        }
        
        // Compare each element with its next element and 
        // Lookup the index at which the elements deviated the ascending order
        for(i=0; (i<arr.length-1 && arr[i]<arr[i+1]); i++);
        // Now, we need to repeat the same by looking up in the reverse direction.
        // Compare each element with its next element and 
        // Lookup the index at which the elements deviated the descending order (since we're going backwards)
        for(j=arr.length-1; (j>0 && arr[j]>arr[j-1]); j--);
        
        // Swap the elements at those identified indices
        swap(arr, i, j);
        // If the array is now sorted, then print yes along with the swap statement
        if(isSorted(arr)){
            System.out.println("yes");
            System.out.println("swap " + (i+1) + " " + (j+1));
            return;
        }
        
        // If the array isn't sorted yet, continue swapping the rest of the elements between those two indices
        int low = i+1;
        int high = j-1;
        while(low<high)
            swap(arr, low++, high--);
        
        // Once all the elements between those indices are swapped,
        // Check if the array is sorted and print yes along with reverse statement
        // upon the indices from which we started swapping
        if(isSorted(arr)){
            System.out.println("yes");
            System.out.println("reverse " + (i+1) + " " + (j+1));
            return;
        }
        
        // If the array is still not sorted, it means that the array isn't almost sorted
        System.out.println("no");
    }
    
    private static boolean isSorted(int[] a){
        for(int i=0; i<a.length-1; i++){
            if(a[i] > a[i+1])
                return false;
        }
        return true;
    }
    
    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        almostSorted(arr);

        scanner.close();
    }
}