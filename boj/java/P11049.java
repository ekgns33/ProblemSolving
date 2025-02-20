import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P11049 {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(in.readLine());
    int[][] matrix = new int[N][2];
    for (int i = 0; i < N; i++) {
      matrix[i] = Arrays.stream(in.readLine().split(" "))
          .mapToInt(Integer::parseInt)
          .toArray();
    }
    int[][] dp = new int[N][N];
    for(int len = 1; len <= N; len++) {
      for (int i = 0; i < N - len; i++) {
        int j = i + len;
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
          dp[i][j] = Math.min(dp[i][k] + dp[k + 1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1], dp[i][j]);
        }
      }
    }
    System.out.println(dp[0][N-1]);
  }
}
/*
*        0 1 2
*     0
*     1
*     2
*  0
*  1
*  1

*   dp[0][1] = max, dp[0][0] + 5*3*2
*   dp[0][2] = max, dp[0][0]
*
*
* 5 3
* 3 2
* 2 6
*
* i k j
* i-k j or i k-j
*    0 1 2
* 0
* 1
* 2
* dp[i][j] = dp[i][k] dp[k][j]
* */