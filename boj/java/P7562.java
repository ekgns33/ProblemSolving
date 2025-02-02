import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P7562 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    for(int t = 0; t < N; t++) {

      int M = Integer.parseInt(br.readLine());
      int[] start = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      int[] end = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      int[] visited = new int[M];
      Arrays.fill(visited, (1<<M));
      visited[start[0]] = (1<<M) | (1<<start[1]);
      System.out.println(dfs(start[0], start[0], visited, 0, end[0], end[1]));
    }

  }
  static int[][] directions = new int[][] {
      {1, -2}, {1, 2}, {-1, 2}, {-1, -2},
      {2, -1}, {2, 1}, {-2, 1}, {-2, -1}
  };
  static int dfs(int x, int y, int[] visit, int cnt, int ex, int ey) {
    if(x == ex && y == ey) {
      return 1;
    }
    for(int[] direction : directions) {
      int nx = x + direction[0];
      int ny = y + direction[1];
      if(nx < 0 || nx >= visit.length || ny < 0 || ny >= visit.length || ((visit[nx] & (1 << ny)) != 0)) continue;
      visit[nx] = visit[nx] | (1<<ny);
      int ret = dfs(nx, ny, visit, 0, ex, ey);
      if(ret != -1) return ret + 1;
    }
    return -1;
  }

}
