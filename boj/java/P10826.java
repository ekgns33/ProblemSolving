import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class P10826 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N == 0) {
			System.out.println("0");
			return;
		}
		if(N == 1) {
			System.out.println("1");
			return;
		}
		BigInteger f2 = BigInteger.ZERO;
		BigInteger f1 = BigInteger.ONE;
		BigInteger f = BigInteger.ZERO;
		for(int i = 2; i <= N; i++) {
			f = f2.add(f1);
			f2 = f1;
			f1 = f;
		}
		System.out.println(f);
	}
}
