import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P17129 {

	private static int[][] directions = {
		{1, 0},
		{0, 1},
		{-1, 0},
		{0, -1}
	};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M;
		N = br.read();
		M = br.read();

		List<List<Integer>> map = new ArrayList<>(N);
		for(int i = 0; i < N; i++) {
			Arrays.stream(br.readLine().split(""))
				.mapToInt(Integer::parseInt)
				.forEach(map.get(i)::add);
		}

		//find Family

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map.get(i).get(j) == 2) {
					// do bfs
					break;
				}
			}
		}

		System.out.println("NIE");
	}
}
