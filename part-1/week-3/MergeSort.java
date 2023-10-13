/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class MergeSort {
    public void mergeSort(int[] array) {
        int[] aux = new int[array.length];
        sort(array, aux, 0, array.length - 1);
        Arrays.sort(array);
    }

    private void sort(int[] array, int[] aux, int lo, int hi) {
        // base condition
        if (hi <= lo) return;

        // For small arrays Insertion sort performs better
        // merge sort has too much overhead

        int mid = lo + (hi - lo) / 2;
        sort(array, aux, lo, mid);
        sort(array, aux, mid + 1, hi);

        // Stop if already sorted
        if (less(array[mid], array[mid + 1])) return;

        merge(array, aux, lo, mid, hi);
    }

    private void merge(int[] array, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }

        int l = lo;
        int m = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (l > mid) array[i] = aux[m++];
            else if (m > hi) array[i] = aux[l++];
            else if (less(aux[l], aux[m])) array[i] = aux[l++];
            else array[i] = aux[m++];
        }
    }

    private boolean less(int a, int b) {
        return a < b;
    }

    public void printArray(int[] array) {
        for (int item : array) {
            System.out.println("num " + item);
        }
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] myArray = { 12, 11, 13, 5, 6, 7 };

        System.out.println("Sorted array:");
        ms.printArray(myArray);

        System.out.println("Sorted array:");
        ms.mergeSort(myArray);
        ms.printArray(myArray);
    }
}
