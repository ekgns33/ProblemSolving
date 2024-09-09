import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P14719 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  public static void main(String[] args) throws IOException {
    int[] input = readLineAndParseInt();

    int[] walls = readLineAndParseInt();


    int l = 0;
    int r = walls.length-1;
    int waterFall = 0;
    while(l < r) {

      //choose sweeping direction
      if(walls[l] < walls[r]) {

        int p = l + 1;
        while(p < r && walls[p] < walls[l]) {
          waterFall += (walls[l] - walls[p]);
          p++;
        }
        l = p;
      } else {
        int p = r - 1;
        while(r >l && walls[p] < walls[r]) {
          waterFall += (walls[r] - walls[p]);
          p--;
        }
        r = p;
      }
    }
    System.out.println(waterFall);

  }

  public static int[] readLineAndParseInt() throws IOException {
    return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
  }
}
