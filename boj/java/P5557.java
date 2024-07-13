import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P5557 {
	public static void main(String[] args) throws IOException {

		long[][] dp = new long[100][4000];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		dp[0][numbers[0]] = 1;
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < 21; j++) {
				if(dp[i-1][j] != 0) {
					if(j + numbers[i] <= 20) {
						dp[i][j + numbers[i]] += dp[i-1][j];
					}
					if(j - numbers[i] >= 0) {
						dp[i][j - numbers[i]] += dp[i-1][j];
					}
				}
			}
		}

		System.out.println(dp[N-2][numbers[N-1]]);
	}
}
/*
*	solution1 )
*
* brute force :
*
*  2^ N-1 = 2^99 > TLE
*
* solution 2)
*
* 99개 0 ~ 20 >
*
* dp[k][j] = dp[k-1][j-cur] + dp[k-1][j + cur]
*
*
* 99번째에 1980
*
* 20 * 200 > 4000
* */
