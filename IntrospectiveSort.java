


/**
 * IntrospectiveSort class.
 */

public class IntrospectiveSort {

  /**
   * Sort the provided items using introspective sort.
   */
  public static <T extends Comparable<T>> void introspectiveSort(T[] items) {
    int depthLimit = 2 * (int) (Math.log(items.length) / Math.log(2));
    introspectiveSort(items, 0, items.length - 1, depthLimit);
  }


  private static <T extends Comparable<T>> void introspectiveSort(T[] items, int left, int right,
      int depthLimit) {
    if (depthLimit == 0) {
      MergeSortImproved.mergeSubsortAdaptive(items, left, right);
    } else {
      if (items.length > 0) {

        int curr = partition(items, left, right, (left + right) / 2);

        if ((curr - left) > 1) {
          introspectiveSort(items, left, curr - 1, depthLimit - 1);
        }
        if ((right - curr) > 1) {
          introspectiveSort(items, curr + 1, right, depthLimit - 1);
        }
      }
    }
  }

  /**
   * Partition the indicated region of the array. The pivot item will be placed in
   * its final sorted position, with all smaller elements moved to the left and
   * all larger elements moved to the right.
   *
   * @return The final index of the pivot item.
   */
  protected static <T extends Comparable<T>> int partition(T[] items, int left, int right,
      int pivotindex) {

    T pivot = items[pivotindex];
    BasicSorts.swap(items, pivotindex, right); // Stick the pivot at end
    pivotindex = right; // remember where it is.
    right--;


    while (left <= right) { // Move bounds inward until they meet
      while (items[left].compareTo(pivot) < 0) {
        left++;
      }
      while ((right >= left) && (items[right].compareTo(pivot) >= 0)) {
        right--;
      }
      if (right > left) {
        BasicSorts.swap(items, left, right); // Swap out-of-place values
      }
    }
    BasicSorts.swap(items, left, pivotindex); // Put pivot in place

    return left; // Return first position in right partition
  }
}

