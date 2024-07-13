import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15724 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] sums = new int[inputs[0]+1][inputs[1]+1];

		for(int i = 1; i < sums.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sum = 0;
			for(int j = 1; j < sums[0].length; j++) {
				sum += Integer.parseInt(st.nextToken());
				sums[i][j] = sum;
			}
		}

		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			int[] range = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int sum = 0;
			for(int row = range[0]; row <= range[2]; row++) {
				sum += (sums[row][range[3]] - sums[row][range[1]-1]);
			}
			System.out.println(sum);
		}

	}
}

/*
*
*
*
*
* */
