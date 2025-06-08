import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P7569 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] consts = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[][][] tomatos = new int[101][101][101];
    int total = 0;
    int curTotal = 0;
    int R = consts[0]; int C = consts[1]; int H = consts[2];
    Queue<int[]> cells = new LinkedList<>();
    for(int i = 0; i < H; i++) {
      for(int col = 0; col < C; col++) {
        int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int r = 0; r< row.length; r++) {
          tomatos[i][col][r] = row[r];
          if(row[r] != -1) total++;
          if(row[r] == 1) {
            cells.add(new int[] {i, col, r});
            tomatos[i][col][r] = 2;
            curTotal++;
          }
        }
      }
    }
    int levelSize = cells.size();
    int days = 0;

    int[][] directions = new int[][] {
        {0, 0, 1},
        {0, 0, -1},
        {0, 1, 0},
        {1, 0, 0},
        {0, -1, 0},
        {-1, 0, 0}
    };

    while(!cells.isEmpty()) {
      for(int i = 0; i < levelSize; i++) {
        int[] nextCell = cells.poll();
        for(int[] direction : directions) {
          int nH = direction[0] + nextCell[0];
          int nC = direction[1] + nextCell[1];
          int nR = direction[2] + nextCell[2];
          if(nH < 0 || nH >=H || nC < 0 || nC >= C || nR <0 || nR >= R || tomatos[nH][nC][nR] != 0) continue;
          tomatos[nH][nC][nR] = 2;
          curTotal++;
          cells.add(new int[] {nH, nC, nR});
        }
      }
      levelSize = cells.size();
      days++;
    }
    System.out.println(curTotal == total ? days-1 : -1);
  }
}
