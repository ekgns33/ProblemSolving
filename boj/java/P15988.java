import java.util.Scanner;

public class P15988 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int t = scanner.nextInt();
    int[] dp = new int[1000001];
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    int lastIndex = 0;
    for(int i = 0; i < t; i++) {
      int n = scanner.nextInt();
      lastIndex = Math.max(lastIndex, n);
      if(lastIndex > n) {
        System.out.println(dp[n]);
        continue;
      }
      for(int k = 4; k<= n; k++) {
        if(dp[k] != 0) continue;
        for(int d = 1; d <=3; d++){
          dp[k] += (dp[k-d]%1000000009);
          dp[k] %= 1000000009;
        }
      }
      System.out.println(dp[n]);
    }

  }
}
