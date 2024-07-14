import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P10406 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		long cnt = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.filter(e -> e>= inputs[0] && e <= inputs[1])
			.count();

		System.out.println(cnt);

	}
}
