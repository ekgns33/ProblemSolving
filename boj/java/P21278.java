import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P21278 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] input = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    int N = input[0];
    int M = input[1];

    long[][] dp = new long[N][N];
    for(int i = 0; i < N; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
      dp[i][i] = 0;
    }

    for(int i =0; i < M; i++) {
      int[] nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      dp[nodes[0]-1][nodes[1]-1] = 1;
      dp[nodes[1]-1][nodes[0]-1] = 1;
    }

    for(int k = 0; k < N; k++) {
      for(int from = 0; from < N; from++) {
        for(int to = 0; to < N; to++) {
          dp[from][to] = Math.min(dp[from][to], dp[from][k] + dp[k][to]);
        }
      }
    }

    int minL = Integer.MAX_VALUE;
    int s = -1;
    int e = -1;

    for(int i = 0; i < N; i++) {
      for(int j = i+1; j < N; j++) {
        int sum = 0;
        for(int r = 0; r < N; r++) {
          sum += (int) (2 * Math.min(dp[i][r], dp[j][r]));
        }
        if(minL > sum) {
          s = i;
          e = j;
          minL = sum;
        }
      }
    }
    System.out.println(String.format("%d %d %d", s+1, e+1, minL));
  }
}
