import java.util.Scanner;

public class P13398 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int N = scan.nextInt();
    int[] nums = new int[N];
    int[][] dp = new int[2][N];
    nums[0] = scan.nextInt();
    dp[0][0] = nums[0];
    dp[1][0] = 0;
    int max = Math.max(dp[0][0], Integer.MIN_VALUE);
    for(int i = 1; i < N; i++) {
      nums[i] = scan.nextInt();
      dp[0][i] = Math.max(dp[0][i-1] + nums[i], nums[i]);
      dp[1][i] = Math.max(Math.max(dp[1][i-1] + nums[i], dp[0][i-1]), nums[i]);
      max = Math.max(Math.max(dp[0][i], dp[1][i]), max);
    }
    System.out.println(max);
  }
}
