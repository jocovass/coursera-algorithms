/*
 * Name: Quick sort
 * Time: N log N
 * Space: does not use any extra space
 * Not stable.
 */
public class QuickSort {
  public void quickSort(int[] array) {
      // Use a random shuffle on the array, this ensures that
      // Quick sort runtime is always O(N log N)
      // StdRandom.shuffle
      threeWayPartitioning(array, 0, array.length-1);
      
      // sort(array, 0, array.length-1);
  }

  private void shuffle(int[] a) {
    int N = a.length;
    for (int i = 0; i < N; i++) {
      // generate a random number between 0 and i + 1
      // shout use the random java method
      int r = i;
      swap(a, i, r);
    }
  }

  // Recursivle sort each subarray
  private void sort(int[] array, int lo, int hi) {
      if (hi <= lo) return;
      // One possible optimisation could be to take the median of 3 items
      // slightly fewer compares.
      //
      // @example 
      // int m = medianOf3(lo, lo+(hi-lo)/2, hi)
      // swap(a, lo, m);

      // partition
      int j = partition(array, lo, hi);
      // Sort left side of the array from the pivot not including the pivot
      // itself
      sort(array, lo, j - 1);
      // Sort right side of the array form the pivot
      sort(array, j + 1, hi);
  }

  // Three way partitioning accounts for dublicate keys
  public void threeWayPartitioning(int[] array, int lo, int hi) {
    if (hi <= lo) return;
    int pivot = array[lo];
    int i = lo;
    int lt = lo;
    int gt = hi;
    while (i <= gt) {
      if (array[i] < pivot) {
        swap(array, lt, i);
        lt++;
        i++;
      } else if (array[i] > pivot) {
        swap(array, i, gt);
        gt--;
      } else {
        i++;
      }
    }

    threeWayPartitioning(array, lo, lt - 1);
    threeWayPartitioning(array, gt + 1, hi);
  }
  
  // We select a pivot point(we use the "lo" for this)
  // And sort the array so that at the end all the items
  // To the right of the pivot are higher than the pivot
  // And all the items to the left are less than the pivot
  private int partition(int[] array, int lo, int hi) {
      int i = lo;
      int j = hi + 1;
      // Do this while the right pointer is not less or equalt to the left pointer
      while (true) {
        // Loop until we find an item that is bigger than the pivot
        // starting from left, or until i is equal to hi
        while(less(array[++i], array[lo])) {
            if (i == hi) break;
        }

        // Loop until we find an item that is smaller than the pivot
        // starting from right, or until j is equal to lo
        while(less(array[lo], array[--j])) {
            if (j == lo) break;
        }

        // If j is less than or equal to i we don't want to change the
        // Items in the array because that means they are in the correct order
        if (j <= i) break;
        // Othervise swap 
        swap(array, i, j);
      }
      // Before returning the index of the new pivot make sure we place it
      // In the correct place in the array
      swap(array, lo, j);
      return j;
  }

  // Swap two items in the array
  private void swap(int[] array, int a, int b) {
      int temp = array[a];
      array[a] = array[b];
      array[b] = temp;
  }

  // Check if a is less than b
  private boolean less(int a, int b) {
      return a < b;
  }

  // Loop throguh the array and print each value
  public void printArray(int[] array) {
      for (int item : array) {
          System.out.println("num " + item);
      }
  }

  public static void main(String[] args) {
      QuickSort ms = new QuickSort();
      int[] myArray = { 12, 11, 13, 5, 6, 7 };
      System.out.println("Unsorted array:");
      ms.printArray(myArray);

      System.out.println("Sorted array:");
      ms.quickSort(myArray);
      ms.printArray(myArray);
  }
}
