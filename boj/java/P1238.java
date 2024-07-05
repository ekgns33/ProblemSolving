import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1238 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Map<Integer, List<int[]>> srcToDst = new HashMap<>();
	static Map<Integer, List<int[]>> dstToSrc = new HashMap<>();

	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int[] sdDist = new int[N+1];
		int[] dsDist = new int[N+1];

		for(int i = 0; i < M; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			srcToDst.putIfAbsent(edge[0], new ArrayList<>());
			dstToSrc.putIfAbsent(edge[1], new ArrayList<>());
			srcToDst.get(edge[0]).add(new int[]{edge[1], edge[2]});
			dstToSrc.get(edge[1]).add(new int[]{edge[0], edge[2]});
		}
		dijkstra(srcToDst, sdDist, X);
		dijkstra(dstToSrc, dsDist, X);

		int maxCost = 0;
		for(int i = 1; i <= N; i++) {
			maxCost = Math.max(maxCost, sdDist[i] + dsDist[i]);
		}
		System.out.println(maxCost);

	}
	public static void dijkstra(Map<Integer, List<int[]>> adj, int[] dist, int src) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
		pq.add(new int[]{src, 0});

		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			if(dist[cur[0]] < cur[1]) continue;

			for(int[] edge : adj.get(cur[0])) {
				if(edge[1] + cur[1] < dist[edge[0]]) {
					dist[edge[0]] = edge[1] + cur[1];
					pq.add(new int[] {edge[0], edge[1] + cur[1]});
				}
			}
		}
	}
}


/*
* input: N towns, M directed roads
* output : student who spend largest time to get back to home
* constraints:
* 1) road is directed
* 2) each road is weighted
* */