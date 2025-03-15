import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class P1504 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    for(int i = 0; i < input[1]; i++) {
      int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      adj[edge[0]].add(new int[]{edge[1], edge[2]});
      adj[edge[1]].add(new int[]{edge[0], edge[2]});
    }

    int[] must = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    long[] distFromSrc = new long[input[0] + 1];
    Arrays.fill(distFromSrc, Integer.MAX_VALUE);
    dijkstra(distFromSrc, 1);
    long[] distFromMust1 = new long[input[0] + 1];
    Arrays.fill(distFromMust1, Integer.MAX_VALUE);
    dijkstra(distFromMust1, must[0]);
    long[] distFromMust2 = new long[input[0] + 1];
    Arrays.fill(distFromMust2, Integer.MAX_VALUE);
    dijkstra(distFromMust2, must[1]);
    long ret1 = distFromSrc[must[0]] + distFromMust1[must[1]] + distFromMust2[input[0]];
    long ret2 = distFromSrc[must[1]] + distFromMust2[must[0]] + distFromMust1[input[0]];
    if(must[0] == 1 && must[1] == input[0]) {
      System.out.println(distFromSrc[input[0]] >= Integer.MAX_VALUE ? -1 : distFromSrc[input[0]]);
      return;
    }
    if(Math.min(ret1, ret2) > Integer.MAX_VALUE) {
      System.out.println("-1");
      return;
    }
    System.out.println(Math.min(ret1, ret2));
  }
  static int[] visited = new int[1024];
  static int pv = 0;
  static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->{
    return a[1] -  b[1];
  });
  static List<int[]>[] adj =new List[1024];

  static {
    for(int i = 0; i < 801; i++) {
      adj[i] = new ArrayList<>();
    }
  }
  static void dijkstra(long[] dist, int src) {
    pq.clear();
    ++pv;
    pq.add(new int[]{src, 0});
    dist[src] = 0;
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      if(visited[cur[0]] == pv || dist[cur[0]] < cur[1]) continue;
      visited[cur[0]] = pv;
      for(int[] e : adj[cur[0]]) {
        int next = e[0];
        int price = e[1];
        if(visited[next] != pv && cur[1] + price < dist[next]) {
          dist[next] = cur[1] + price;
          pq.add(new int[]{next, price + cur[1]});
        }
      }
    }
  }
}
