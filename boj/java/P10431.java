import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P10431 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			int[] heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int cnt = 0;
			for(int j =1; j < heights.length; j++) {
				int p = j;
				while(p > 1 && heights[p-1] > heights[p]) {
					int tmp = heights[p];
					heights[p] = heights[p-1];
					heights[p-1] = tmp;
					p--;
					cnt++;
				}
			}
			System.out.println(i+1 + " " + cnt);
		}

	}
}
