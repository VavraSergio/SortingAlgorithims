


/**
 * Improved MergeSort class.
 */

public class MergeSortImproved {
  public static int MERGE_SORT_THRESHOLD = 55;


  protected static void setSortThreshold(int threshold) {
    MergeSortImproved.MERGE_SORT_THRESHOLD = threshold;
  }

  /**
   * Merge sort the provided array using an improved merge operation.
   */
  public static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items) {
    mergeSortHalfSpace(items, 0, items.length - 1);
  }


  private static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items, int start, int end) {
    if (start < end) {
      int mid = start + (end - start) / 2;
      mergeSortHalfSpace(items, start, mid);
      mergeSortHalfSpace(items, mid + 1, end);
      merge(start, end, mid, items);
    }
  }

  private static <T extends Comparable<T>> void merge(int start, int end, int mid, T[] items) {
    int tempSize = mid - start + 1;

    @SuppressWarnings("unchecked")
    T[] tempArray = (T[]) new Comparable[tempSize];

    for (int i = 0; i < tempSize; i++) {
      tempArray[i] = items[start + i];
    }

    int i = 0;
    int j = mid + 1;
    int k = start;

    while (i < tempSize && j <= end) {
      if (tempArray[i].compareTo(items[j]) <= 0) {
        items[k] = tempArray[i];
        k++;
        i++;
      } else {
        items[k] = items[j];
        k++;
        j++;
      }
    }

    while (i < tempSize) {
      items[k] = tempArray[i];
      k++;
      i++;
    }
  }


  /**
   * Merge sort the provided array by using an improved merge operation and switching to insertion
   * sort for small sub-arrays.
   */
  public static <T extends Comparable<T>> void mergeSortAdaptive(T[] items) {
    mergeSortAdaptive(items, 0, items.length - 1);
  }

  private static <T extends Comparable<T>> void mergeSortAdaptive(T[] items, int start, int end) {
    if (start < end) {
      if (end - start > MERGE_SORT_THRESHOLD) {
        int mid = start + (end - start) / 2;
        mergeSortAdaptive(items, start, mid);
        mergeSortAdaptive(items, mid + 1, end);
        merge(start, end, mid, items);
      } else {
        BasicSorts.insertionSubsort(items, start, end);
      }

    }
  }

  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the fallback method
   * used by introspective sort.
   */
  public static <T extends Comparable<T>> void mergeSubsortAdaptive(T[] items, int start, int end) {
    if (start < end) {
      if (end - start > MERGE_SORT_THRESHOLD) {
        int mid = start + (end - start) / 2;
        mergeSortAdaptive(items, start, mid);
        mergeSortAdaptive(items, mid + 1, end);
        merge(start, end, mid, items);
      } else {
        BasicSorts.insertionSubsort(items, start, end);
      }

    }

  }

}
