import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P13023 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Map<Integer, List<Integer>> adj = new HashMap<>();
    //construct adjacent list
    for(int i = 0; i < M; i++) {
      int[] edge = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt)
          .toArray();
      adj.putIfAbsent(edge[0], new ArrayList<>());
      adj.putIfAbsent(edge[1], new ArrayList<>());
      adj.get(edge[0]).add(edge[1]);
      adj.get(edge[1]).add(edge[0]);
    }
    Set<Integer> v = new HashSet<>();
    for(int i = 0; i < N; i++) {
      v.add(i);
      if(adj.containsKey(i) && find(adj, v, i, 0)) {
        System.out.println(1);
        return;
      }
      v.remove(i);
    }
    System.out.println(0);
  }
  static boolean find(Map<Integer, List<Integer>> adj, Set<Integer>visited, int cur, int l) {
    if(l >= 4) return true;
    boolean ret = false;

    for(int next : adj.get(cur)) {
      if(visited.contains(next)) continue;
      visited.add(next);
      ret |= find(adj, visited, next, l + 1);
      visited.remove(next);
    }
    return ret;
  }
}
