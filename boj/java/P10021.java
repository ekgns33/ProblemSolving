import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P10021 {
  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static int[] parent = new int[2001];

  public static void main(String[] args) throws IOException {

    int[] input = readIntArray();

    int[][] points = new int[input[0]][2];

    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

    for(int i = 0; i < input[0]; i++) {
      parent[i] = i;
      points[i] = readIntArray();
      for(int j = 0; j < i; j++) {
        double cost = calculateCost(points, i, j);
        if(cost < input[1]) continue;
        pq.add(new int[] {i, j, (int)cost});
      }
    }

    int cnt = input[0]-1;
    int pay = 0;
    while(cnt > 0 && !pq.isEmpty()) {
      int[] edge = pq.poll();
      int x = edge[0];
      int y = edge[1];

      x = find(x);
      y = find(y);
      if(x != y) {
        union(x, y);
        cnt--;
        pay += edge[2];
      }
    }
    System.out.println(cnt == 0 ? pay : -1);
  }

  public static int find(int x) {
    if(x == parent[x]) return x;
    return parent[x] = find(parent[x]);
  }

  public static void union(int x, int y) {
    x = find(x);
    y = find(y);
    if(x < y) {
      parent[y] = x;
    } else if (x > y) {
      parent[x] = y;
    }
  }

  public static double calculateCost(int[][] points, int i, int j) {
    int[] point1 = points[i];
    int[] point2 = points[j];
    return Math.pow(point1[0] - (double)point2[0], 2) + Math.pow(point1[1] - (double)point2[1], 2);
  }


  public static int[] readIntArray() throws IOException {
    return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
  }
}
