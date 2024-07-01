import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P14606 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N+1];
		dp[0] = 0;
		dp[1] = 0;
		for(int i = 2; i <= N; i++) {
			int left = i /2;
			int right = i - left;

			dp[i] = left * right + dp[left] + dp[right];
		}
		System.out.println(dp[N]);
	}
}

/*
*
* 1 : 0
* 2 : 1 + 1 : 1*1 = 1
* 3 : 2 + 1 : 2 * 1 + dp[2] + dp[1] = 2 + 1 = 3
* 4 : 2 * 2 + 1 + 1 - > 6
* 5 : 2 * 3 + 4 = 10
* 6 : 3 * 3 + 6 = 15
* 7: 3 *4  + 9 = 21
* 8 : 4 *4 + 12 = 28
* */