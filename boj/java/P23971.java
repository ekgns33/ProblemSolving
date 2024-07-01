import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P23971 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int row = inputs[0] / (inputs[2] + 1);
		row += ((inputs[0] % (inputs[2] + 1)) > 0 ? 1 : 0);

		int col = inputs[1] / (inputs[3] + 1);
		col += ((inputs[1] % (inputs[3] + 1)) > 0 ? 1 : 0);

		System.out.println(row * col);

	}
}
