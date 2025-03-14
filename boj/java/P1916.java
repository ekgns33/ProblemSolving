import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1916 {

  static List<int[]>[] adj = new ArrayList[100001];

  static {
    for(int i = 0; i < 100001; i++) {
      adj[i] = new ArrayList<>();
    }
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    for(int i = 0; i < N; i++) {
      adj[i].clear();
    }
    for(int i = 0; i < M; i++) {
      int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      if(input[0] == input[1]) continue;
      adj[input[0]].add(new int[]{input[1], input[2]});
    }

    int[] startEnd = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
      return a[1] - b[1];
    });
    int[] visited = new int[N+1];
    long[] dist = new long[N+1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    pq.add(new int[]{startEnd[0], 0});

    while(!pq.isEmpty()) {
      int[] current = pq.poll();
      if(visited[current[0]] == 1 || dist[current[0]] < current[1]) continue;
      visited[startEnd[0]] = 1;
      for(int[] e : adj[current[0]]) {
        int next = e[0];
        int price = e[1];
        if(visited[next] == 0 && dist[next] > (price + current[1])) {
          dist[next] = price + current[1];
          pq.add(new int[]{next, price + current[1]});
        }
      }
    }
    System.out.println(dist[startEnd[1]]);
  }
}
