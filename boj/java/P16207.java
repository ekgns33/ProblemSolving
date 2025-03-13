import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class P16207 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] nums = new int[N];
    nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    Arrays.sort(nums);
    long[] line = new long[N];
    int p = 0;
    for(int i = N - 1; i > 0; i--) {
      if(nums[i] == nums[i-1]) {
        line[p++] = nums[i];
        i--;
      } else if(nums[i] - nums[i-1] == 1) {
        line[p++] = nums[i-1];
        i--;
      }
    }
    long ret = 0;
    for(int i = 0; i < N-2; i+=2) {
      //System.out.println(line[i] + " " + line[i+1]);
      ret += line[i] * line[i+1];
    }
    System.out.println(ret);

  }
}
