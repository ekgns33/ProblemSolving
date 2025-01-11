import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P11060 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] jump = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[] dp = new int[N];
    Arrays.fill(dp, 1000000);
    dp[0] = 0;
    for(int i = 0; i < N; i++) {
      for(int j = 0; j <= jump[i] && (i + j) < N; j++) {
        dp[i+j] = Math.min(dp[i+j], dp[i] + 1);
      }
    }
    System.out.println(dp[N-1] == 1000000 ? -1 : dp[N-1]);
  }
}
