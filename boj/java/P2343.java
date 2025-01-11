import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2343 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int N = input[0];
    int M = input[1];

    int[] records = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int ans = Integer.MAX_VALUE;

    int l = Arrays.stream(records).max().getAsInt();
    int r = 1000000000;
    while(l <= r) {
      int mid = (l + r) / 2;
      // check
      int cnt = 0;
      int curSum = 0;
      for (int record : records) {
        if (curSum + record > mid) {
          curSum = 0;
          cnt++;
        }
        curSum += record;
      }
      if (curSum != 0) cnt++;
      if(cnt > input[1]) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    System.out.println(l);
  }
}
