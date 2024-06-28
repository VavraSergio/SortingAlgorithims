import java.util.List;

/**
 * Driver for testing.
 */
public class ProfileDriver {
  /**
   * Main method for testing.
   *
   * @param args Args from command line
   */
  public static <T extends Comparable<T>> void main(String[] args) {

    // Create a SortProfiler object.
    // See the JavaDoc for an explanation of the parameters.
    
    for (int i = 0; i < 90; i += 5) {
      MergeSortImproved.setSortThreshold(i);
      System.out.println("MERGE_THRESHOLD_VALUE:" + i);
      SortProfiler sp = new SortProfiler(
          List.of(MergeSortImproved::mergeSortAdaptive, MergeSort::mergeSort),
          List.of("MergeSortAdaptive", "MergeSort"), 0, 10, 150, 200,
          Generators::generateRandom);

      // Run the profiler and send the output to stdout.
      sp.run(System.out);
    }
      

  }

}
