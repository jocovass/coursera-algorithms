/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class MergeSort {
    public void mergeSort(int[] array) {
        // Create an auxiliary array the same length as the
        // array to be sorted. This is used to do the merge.
        int[] aux = new int[array.length];
        // Set low = 0, and high = N - 1;
        sort(array, aux, 0, array.length - 1);
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
        // We copy over the items from the main array to the auxiliary array
        // before we start to merge the two subarray(left side and right side).
        // This is used as a temporary placeholder for items current position.
        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }

        int l = lo;
        // We add 1 to mid because that is the index of the first element
        // in the right hand side sub array.
        int m = mid + 1;
        for (int i = lo; i <= hi; i++) {
            // If l is bigger than mid we know that there is noting left in the 
            // left hand side sub array, so we grab the next item from the right
            // hand side sub array and increment the index by one.
            if (l > mid) array[i] = aux[m++];
            // Else if m is bigger than hi we know that there is nothing left in
            // the right hand side sub array, grab the next item from the left.
            else if (m > hi) array[i] = aux[l++];
            // Else if grab the smaller item.
            else if (less(aux[l], aux[m])) array[i] = aux[l++];
            // Else grab the next item from the right hand side array.
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
