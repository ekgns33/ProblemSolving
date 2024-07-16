import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class P18232 {
	public static void main(String[] args) throws IOException {
		Map<Integer, List<Integer>> adj = new HashMap<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];
		int M = input[1];
		int[] dest = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for(int i = 0; i < M; i++) {
			int[] relation = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adj.putIfAbsent(relation[0], new ArrayList<>());
			adj.putIfAbsent(relation[1], new ArrayList<>());
			adj.get(relation[0]).add(relation[1]);
			adj.get(relation[1]).add(relation[0]);
		}

		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		visited[dest[0]] = true;
		q.add(dest[0]);
		int qSize = q.size();
		int qlevel = 0;
		while (!q.isEmpty()) {
			for(int i = 0; i < qSize; i++) {
				int curr = q.poll();
				if(curr == dest[1]) {
					System.out.println(qlevel);
					return;
				}
				// left
				if(curr - 1 > 0 && !visited[curr-1]) {
					visited[curr-1] = true;
					q.add(curr-1);
				}
				// right
				if(curr + 1 <= N && !visited[curr+1]) {
					visited[curr+1] = true;
					q.add(curr+1);
				}
				// teleport
				if(!adj.containsKey(curr)) continue;
				for(int next : adj.get(curr)) {
					if(!visited[next]) {
						visited[next] = true;
						q.add(next);
					}
				}
			}
			qlevel++;
			qSize = q.size();
		}
		// System.out.println(qlevel);
	}
}
