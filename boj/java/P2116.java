import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2116 {

  static int[][] dice = new int[10001][6];

  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(in.readLine());
    for(int i = 0; i < N; i++) {
      dice[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    int max = 0;
    int cur = 0;
    // set bottom
    int curTop = -1;
    for(int i = 0; i < 6; i++) {
      cur = 0;
      int behind = getBehind(i);
      curTop= dice[0][behind];
      cur += getMaxInLevel(0, i);
      for(int j = 1; j < N; j++) {
        int curBottomIdx = getPlaneForDice(j, curTop);
        cur += getMaxInLevel(j, curBottomIdx);
        curTop = dice[j][getBehind(curBottomIdx)];
      }
      max = Math.max(max, cur);
    }
    System.out.println(max);




  }
  private static int getPlaneForDice(int level, int val) {
    for(int i = 0; i < 6; i++) {
      if(dice[level][i] == val) {
        return i;
      }
    }
    return  -1;
  }


  private static int getMaxInLevel(int level, int bottom) {
    int max = 0;
    for(int i = 0; i < 6; i++) {
      if(i == bottom || i == getBehind(bottom)) continue;
      max = Math.max(max, dice[level][i]);
    }
    return max;
  }

  private static int getBehind(int i) {
    if(i == 0) return 5;
    if(i == 1) return 3;
    if(i == 2) return 4;
    if(i == 3) return 1;
    if(i == 4) return 2;
    if(i == 5) return 0;
    return -1;
  }
}
