import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P2644 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int T = Integer.parseInt(br.readLine());

    int[][] adj = new int[101][101];

    for(int i = 0; i < T; i++) {
      int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      adj[edge[0]][edge[1]] = 1;
      adj[edge[1]][edge[0]] = 1;
    }

    boolean[] visited = new boolean[101];
    Queue<Integer> q = new LinkedList<>();
    q.add(target[0]);
    visited[target[0]] = true;
    int level = 0;
    int levelSize= 1;
    while(!q.isEmpty()) {
      for(int i = 0; i < levelSize; i++) {
        int next = q.poll();
        if(next == target[1]) {
          System.out.println(level);
          return;
        }
        for(int j = 0; j <101; j++) {
            if(adj[next][j] ==1 && !visited[j]) {
              q.add(j);
              visited[j] = true;
            }
        }
      }
      level++;
      levelSize = q.size();
    }
    System.out.println("-1");
  }
}
