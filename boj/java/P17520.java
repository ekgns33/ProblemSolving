import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P17520 {
	static final int MOD = 16769023;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N+1];
		dp[1] = 2;
		for(int i = 2; i <= N; i++) {
			if(i % 2 ==0) {
				dp[i] = dp[i-1];
			} else {
				dp[i] = (2* dp[i-1]) % MOD;
			}
		}
		System.out.println(dp[N]);
	}
}
/*
*
* N개일때 1이 K개인 경우
*
* 1 K= 0 K = 1
* 2 K= 1
* 3 K =1 K =2
* 4 K =2
* 5 K= 2 K =3
* 6 K =3
* 7 K =3 K =4
*
* n = 1
* 0, 1  > 2
* n = 2
* 01, 10 > 2
* n =3
* 010,001, 100, 010, 011,101, 101, 110
*
*
* */