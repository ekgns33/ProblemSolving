import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class P1854 {
	static	Map<Integer, List<int[]>> adj = new HashMap<>();
	static 	PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
	static  PriorityQueue<Integer>[] distHistory;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];
		int M = input[1];
		int K = input[2];

		for(int i = 0; i < M; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adj.putIfAbsent(edge[0], new ArrayList<>());
			adj.putIfAbsent(edge[1], new ArrayList<>());
			adj.get(edge[0]).add(new int[] {edge[1], edge[2]});
		}

		distHistory = new PriorityQueue[N+1];
		for(int i = 0; i < distHistory.length; i++) {
			distHistory[i] = new PriorityQueue<>(K, (a,b)-> b-a);
		}

		pq.add(new int[]{1, 0});
		distHistory[1].add(0);

		while(!pq.isEmpty()) {

			int[] curr = pq.poll();

			for(int[] next : adj.get(curr[0])) {
				int nextCost = next[1] + curr[1];
				if(distHistory[next[0]].size() < K) {
					distHistory[next[0]].add(nextCost);
					pq.add(new int[] {next[0], nextCost});

				}else if (nextCost < distHistory[next[0]].peek()) {
					distHistory[next[0]].poll();
					distHistory[next[0]].add(nextCost);
					pq.add(new int[] {next[0], nextCost});
				}

			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			if(distHistory[i].size() < K) {
				sb.append("-1\n");
			} else {
				sb.append(distHistory[i].peek());
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());

	}
}
