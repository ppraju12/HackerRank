// Problem Link: https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int notificationCount = 0;
        // If the trailing no: of days matches the total no: of expenditures,
        // then we don't need to notify at all
        if(d>=expenditure.length)
            return 0;
            
        // Add the trailing expenses to a list and sort them in ascending order
        List<Integer> trailingExpenses = new ArrayList<Integer>();
        for(int i=0; i<d; i++)
            trailingExpenses.add(expenditure[i]);
        Collections.sort(trailingExpenses);
        // Get the first index of the trailing expenses
        int firstIndex = Collections.binarySearch(trailingExpenses, expenditure[0]);
        
        for(int i=d; i<expenditure.length; i++){
            if(i!=d){
                // Delete the first expense of the trailing expenses, as we've processed it already
                trailingExpenses.remove(firstIndex);
                // Get the amount of the last expense of the trailing expenses
                int lastExpenseAmount = expenditure[i-1];
                // Get the index at which the the last expense exists or 
                // the 9's complement of the index at which it can be placed (if it doesn't exist in the list)
                int lastIndex = Collections.binarySearch(trailingExpenses, lastExpenseAmount);
                // System.out.println(trailingExpenses);
                // System.out.println(lastExpenseAmount + " found at " + lastIndex + " or "+ ~lastIndex);
                // Get the 9's complement of the last index, if it is less than zero
                trailingExpenses.add(lastIndex >= 0 ? lastIndex : ~lastIndex, lastExpenseAmount);
                // Update the index of first expense of the trailing expenses 
                firstIndex = Collections.binarySearch(trailingExpenses, expenditure[i-d]);
            }
            if ( (median(trailingExpenses) * 2) <= expenditure[i])
                notificationCount++;
        }
        
        return notificationCount;
    }
    
    private static double median(List<Integer> list) {
        int mid = list.size() / 2;
        if (list.size() % 2 != 0)
            return list.get(mid);
        return (list.get(mid - 1) + list.get(mid)) / 2.0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
