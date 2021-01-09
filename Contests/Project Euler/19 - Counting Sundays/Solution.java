// Problem Link: https://www.hackerrank.com/contests/projecteuler/challenges/euler019/problem

import java.io.*;
import java.util.*;

public class Solution {

    static int SUNDAY = 0;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            // Read the Start Date and End Date and store them in a single Array
            long[][] dates = new long[2][3];
            for(int i=0; i<2; i++)
                for(int j=0; j<3; j++)
                    dates[i][j] = in.nextLong();
            
            // Begin from the Start Date and look up the day of week for 1st of each month 
            // till we get past End Date and increment the counter if it is a Sunday
            int numberOfSundays = 0;
            while(true){
                // If it is the 1st of the month and if the day of the week is Sunday,
                // increment the counter
                if(dates[0][2] == 1)
                    if( isSunday(dates[0]) )
                        numberOfSundays++;
                
                // Set the day of the month as 1 moving forward
                dates[0][2] = 1;
                
                // Change the month of the Start Date to the next month
                dates[0][1]++;
                // If the month is December, then we would have to consider the next month as the January of next year
                if(dates[0][1] > 12){
                    dates[0][1] = 1;
                    dates[0][0]++;
                }
                
                // Repeat the above steps till the Start Date gets past End Date
                if( isPast(dates) )
                    break;
            }
            
            System.out.println(numberOfSundays);
        }
        in.close();
    }
    
    private static boolean isPast(long[][] dates){
        // If the Start Date is past the End Date, return true
        
        // If the year of Start Date is greater than the year of End Date, return true
        if(dates[0][0] > dates[1][0])
            return true;
        else if(dates[0][0] == dates[1][0]){
            // If the year matches, then check if the month of Start Date is greater than the month of End Date
            // If the month matches, then check if the day of Start Date is greater than the day of End Date
            if(dates[0][1] > dates[1][1])
                return true;
            else if(dates[0][1] == dates[1][1] && dates[0][2] > dates[1][2])
                return true;
        }
        
        // If the Start Date is older than End Date, return false
        return false;
    }
    
    private static boolean isSunday(long[] date){
        // Get the Day, Month and Year from the date
        long day = date[2];
        long month = date[1];
        long year = date[0];
        
        // Use Zeller's Congruence formula to derive the day of the week of a date
        // h = ( q + 13*(m+1)/5 + k + k/4 + j/4 - 2*j ) mod 7
        
        // Day of the month
        long q = day - 1;
        // Month
        long m = month;
        // January or February need to be considered as month 13 & 14 of the previous year
        if(m < 3){
            m += 12;
            year--;
        }
        // Year of the century
        long k = year % 100;
        // Zero-based century
        long j = (long) Math.floor(year / 100);
        
        // Day of the week
        int h = (int) ((q + Math.floor(13 * (m + 1) / 5) + k + Math.floor(k/4) + Math.floor(j/4) - 2*j) % 7);
        
        // Return if the day of the week is Sunday or not
        boolean isSunday = h == SUNDAY;
        return isSunday;
    }
}