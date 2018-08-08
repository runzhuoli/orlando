package ca.rlm.orlando.core.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {
  @Override
  public void run(String... args) throws Exception {
    int[] input = {4, -1, 7, 1, -3};
    System.out.println(this.maximumSum(input));
  }


  /**
   *
   * @param arr
   * @return
   */
  static long maximumSum(int[] arr) {
    if (arr == null) {
      return 0;
    }
    if (arr.length == 1) {
      return arr[0] > 0 ? arr[0] : 0;
    }
    long result = arr[0];
    long max = 0;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i - 1] < arr[i]) {
        result += arr[i];
      } else {
        max = max < result ? result : max;
        result = arr[i];
      }
    }
    return max;
  }
}
