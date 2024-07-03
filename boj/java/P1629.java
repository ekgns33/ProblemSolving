import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1629 {
	public static long divide(long op1, long op2, long mod) {
		if(op2 == 1) {
			return op1 % mod;
		} else if (op2 % 2 == 0) {
			long ret = divide(op1, op2 / 2, mod);
			return (ret * ret) % mod;
		}
		long ret = divide(op1, (op2-1)/2, mod);
		return (ret * ret % mod) * (op1 % mod) % mod;

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.println(divide(input[0], input[1], input[2]));
	}
}

/*
* 자연수 A 를 B번 곱한 수    % C
* 11 -> 5 /6
*  2 3      3 3
* 1 1 1 2  1
*
*
* 3 > 1 2
* 4 > 2 2
* 5 > 2 3>
* 6 > 3 3
* 7 > 3 4
* */