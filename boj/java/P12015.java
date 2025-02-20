import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

public class P12015 {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(in.readLine());
    int[] nums = Arrays.stream(in.readLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
    int[] dp = new int[N];
    int len = 0;
    for(int i = 0; i < N; i++) {
      int cur = nums[i];
      int l = 0, r = len-1;
      while(l <= r) {
        int mid = (l + r) >> 1;
        if(dp[mid] == cur) {
          l = mid;
          break;
        } else if (dp[mid] < cur) {
          l = mid + 1;
        } else {
          r = mid - 1;
        }
      }
      if(l >= len) {
        dp[len++] = cur;
      } else {
        dp[l] = cur;
      }
    }
    System.out.println(len);

  }
}
