import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1707 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for(int i = 0; i < T; i++) {
      int[] input = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt)
          .toArray();
      Map<Integer, List<Integer>> adj = new HashMap<>();
      for(int k = 0; k < input[1]; k++){
        int[] edge = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        adj.putIfAbsent(edge[0], new ArrayList<>());
        adj.putIfAbsent(edge[1], new ArrayList<>());
        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
      }
      int[] visited = new int[input[0] + 1];
      visited[1] = 1;
      Set<Integer> set = new HashSet<>();
      for(int node = 1; node <= input[0]; node++) {
        if(set.contains(node)) continue;
        set.add(node);
        visited[node] = 1;
        if(!isBipartite(adj, node, visited, set, 1)) {
          System.out.println("NO");
          return;
        }
      }
      System.out.println("YES");
    }
  }
  static boolean isBipartite(Map<Integer, List<Integer>> adj, int cur, int[] visited, Set<Integer> v, int color) {
     List<Integer> next = adj.get(cur);
     boolean ret = true;
     for(int n : next) {
       if(visited[n] == color) return false;
       if(v.contains(n)) continue;
       visited[n] = -color;
       v.add(n);
       ret &= isBipartite(adj, n, visited, v, -color);
     }
     return ret;
  }
}
