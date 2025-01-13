import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P14888 {
  static long max = Integer.MIN_VALUE;
  static long min = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[] ops = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    find(nums, 1, ops, nums[0]);
    System.out.println(max);
    System.out.println(min);
  }
  static void find(int[] nums, int pos, int[] ops,  long sum) {
    if(pos == nums.length) {
      max = Math.max(max, sum);
      min = Math.min(min, sum);
      return;
    }
    // add
    if (ops[0] > 0) {
      ops[0]--;
      find(nums, pos + 1, ops, sum + nums[pos]);
      ops[0]++;
    }
    if (ops[1] > 0) {
      ops[1]--;
      find(nums, pos + 1, ops, sum - nums[pos]);
      ops[1]++;
    }
    if (ops[2] > 0) {
      ops[2]--;
      find(nums, pos + 1, ops, sum * nums[pos]);
      ops[2]++;
    }
    if (ops[3] > 0) {
      ops[3]--;
      find(nums, pos + 1, ops, sum / nums[pos]);
      ops[3]++;
    }
  }
}
