import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P19947 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] dp = new int[inputs[1] + 1];
		dp[0] = inputs[0];
		for(int i = 1; i< dp.length; i++) {
			dp[i] = Math.max(dp[i], (int)(dp[i-1] * 1.05));
			if(i -3 >= 0) {
				dp[i] = Math.max(dp[i], (int)(dp[i-3] * 1.2));

			}
			if(i -5 >=0) {
				dp[i] = Math.max(dp[i], (int)(dp[i-5] * 1.35));
			}
		}
		System.out.println(dp[inputs[1]]);
	}
}
