import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P17836 {

	static int[][] directions = {
		{1, 0},
		{0, 1},
		{-1, 0},
		{0, -1}
	};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] map = new int[input[0]][input[1]];

		// edge
		if(input[0] + input[1] -2 > input[2]) {
			System.out.println("Fail");
			return;
		}

		for(int i = 0; i < input[0]; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0, 0});
		boolean[][] visited = new boolean[input[0]][input[1]];
		visited[0][0] = false;

		int minT = Integer.MAX_VALUE;
		while(!q.isEmpty()) {

			int[] current = q.poll();


			// get sword
			if(map[current[0]][current[1]] == 2) {
				minT = Math.min(minT, (current[2] + (input[0] - 1 - current[0]) + (input[1] - 1 - current[1])));
			}
			if(current[0] == map.length-1 && current[1] == map[0].length-1) {
				minT = Math.min(minT, current[2]);
			}
			for(int[] direction : directions) {

				int nx = current[0] + direction[0];
				int ny = current[1] + direction[1];
				if(nx < 0 || nx >= input[0] || ny < 0 || ny >= input[1] || visited[nx][ny] || map[nx][ny] ==1) continue;
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny, current[2] + 1});
			}
		}

		if (minT <= input[2]) {
			System.out.println(minT);
			return;
		}
		System.out.println("Fail");


		// bfs without sword
		//bfs to sword, if possible
	}
}
