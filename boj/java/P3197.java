import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P3197 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    char[][] map = new char[input[0]][input[1]];
    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int swan = 0;
    int[][] swans = new int[2][2];
    boolean[][] visited = new boolean[map.length][map[0].length];
    Queue<int[]> melting = new LinkedList<>();
    Queue<int[]> moving = new LinkedList<>();

    for (int i = 0; i < map.length; i++) {
      map[i] = br.readLine().toCharArray();
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == '.') {
          melting.add(new int[]{i, j});
        } else if (map[i][j] == 'L') {
          swans[swan][0] = i;
          swans[swan][1] = j;
          swan++;
          map[i][j] = '.';
          melting.add(new int[]{i, j});
        }
      }
    }

    moving.add(swans[0]);
    visited[swans[0][0]][swans[0][1]] = true;
    int days = 0;

    while (true) {
      Queue<int[]> nextMelting = new LinkedList<>();
      Queue<int[]> nextMoving = new LinkedList<>();

      while (!moving.isEmpty()) {
        int[] next = moving.poll();
        for (int[] direction : directions) {
          int nx = next[0] + direction[0];
          int ny = next[1] + direction[1];
          if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length || visited[nx][ny]) continue;
          if (map[nx][ny] == '.') {
            moving.add(new int[]{nx, ny});
          } else {
            nextMoving.add(new int[]{nx, ny});
          }
          visited[nx][ny] = true;
        }
      }

      // Melt ice
      while (!melting.isEmpty()) {
        int[] next = melting.poll();
        for (int[] direction : directions) {
          int nx = next[0] + direction[0];
          int ny = next[1] + direction[1];
          if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
          if (map[nx][ny] == 'X') {
            nextMelting.add(new int[]{nx, ny});
            map[nx][ny] = '.';
          }
        }
      }

      if (visited[swans[1][0]][swans[1][1]]) {
        System.out.println(days);
        return;
      }

      melting = nextMelting;
      moving = nextMoving;
      days++;
    }
  }
}
