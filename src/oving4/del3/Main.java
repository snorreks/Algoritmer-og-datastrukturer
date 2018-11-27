package oving4.del3;

import java.util.Date;

public class Main {


    private static int max = 10000;
    private static int min = -10000;

    /* A utility function to print array of size n */
    private static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    private static boolean sortCheck(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    private static void finnTid(boolean quicksort, int[] arr) {
        int runder = 1;
        double tid;
        Date slutt;
        Date start;
        if (quicksort) {
            start = new Date();
            do {
                QuickSort.sort(arr, 0, arr.length - 1);
                slutt = new Date();
                ++runder;
            } while (slutt.getTime() - start.getTime() < 1000);
        } else {
            start = new Date();
            do {
                HeapSort.sort(arr);
                slutt = new Date();
                ++runder;
            } while (slutt.getTime() - start.getTime() < 1000);
        }
        tid = (double) (slutt.getTime() - start.getTime()) / runder;
        printArray(arr);
        System.out.println("Tiden tok: " + tid);
        System.out.println(sortCheck(arr));
    }

    private static int[] getArray(int length) {
        int[] stocks = new int[length];
        for (int i = 0; i < stocks.length; i++) {
            stocks[i] = (int) (Math.random() * ((max - min) + 1)) + min;
        }
        return stocks;
    }

    private static int[] getSortArray(int length) {
        int[] arr = getArray(length);
        QuickSort.sort(arr, 0, arr.length - 1);
        printArray(arr);
        return arr;
    }

    private static int[] getDupliArray(int length) {
        int[] stocks = new int[length];
        for (int i = 0; i < stocks.length; i++) {
            if (i % 2 != 0) {
                stocks[i] = stocks[i - 1];
            } else {
                stocks[i] = (int) (Math.random() * ((max - min) + 1)) + min;
            }
        }
        return stocks;
    }

    public static void main(String args[]) {
        int[] arr = getArray(1000);
        //int[] arr = getDupliArray(1000);
        //int[] arr = getSortArray(1000);

        System.out.println("QuickSort:");
        finnTid(true, arr);
        System.out.println("\nHeapSort:");
        finnTid(false, arr);
    }
}