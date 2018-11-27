package oving3;

import java.util.Date;

public class Main {

    private static int max = 1000;
    private static int min = -1000;

    /********************* QUICK SORT **********************************************************/
    private static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /* The Main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    public static void sortQuick(int arr[], int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sortQuick(arr, low, pi - 1);
            sortQuick(arr, pi + 1, high);
        }
    }

    /******************************************************* DUAL SORT *********************************************/
    public static void sortDualQuick(int[] arr, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) {
            return;
        }

        if (arr[lowIndex] > arr[highIndex]) {
            swap(arr, lowIndex, highIndex);
        }

        int pivot1 = arr[lowIndex];
        int pivot2 = arr[highIndex];

        int lt = lowIndex + 1;
        int gt = highIndex - 1;
        int i = lowIndex + 1;

        while (i <= gt) {
            if (arr[i] < pivot1) {
                swap(arr, i, lt);
                lt++;
                i++;
            } else if (arr[i] > pivot2) {
                swap(arr, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        swap(arr, lowIndex, lt - 1);
        swap(arr, gt + 1, highIndex);

        lt--;
        gt++;

        sortDualQuick(arr, lowIndex, lt - 1);
        sortDualQuick(arr, lt + 1, gt - 1);
        sortDualQuick(arr, gt + 1, highIndex);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    /******* Felles hjelpe metoder *********************************************************************************************/

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
                sortQuick(arr, 0, arr.length - 1);
                slutt = new Date();
                ++runder;
            } while (slutt.getTime() - start.getTime() < 1000);
        } else {
            start = new Date();
            do {
                sortDualQuick(arr, 0, arr.length - 1);
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
        sortQuick(arr, 0, arr.length - 1);
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
        //int[] arr = getArray(1000);
        //int[] arr = getDupliArray(1000);
        int[] arr = getSortArray(1000);

        System.out.println("QuickSort:");
        finnTid(true, arr);
        System.out.println("\nDualQuickSort:");
        finnTid(false, arr);
    }
}