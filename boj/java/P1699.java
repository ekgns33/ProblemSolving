import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1699 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] dp = new int[N+1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    dp[1] = 1;
    for(int i = 2; i <= N; i++) {
      for(int j = 1; j <= Math.sqrt(i); j++) {
        dp[i] = Math.min(dp[i], dp[(i - (int)Math.pow(j, 2))] + 1);
      }
    }
    System.out.println(dp[N]);
  }
}
