import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2007 {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(in.readLine());
    int totalsum = 0;
    int[] add = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    Arrays.sort(add);

  }
}
/*
* a b c d
*
* a b
* a c
* a d
* b c
* b d
* a b c
* a b
* a   c
*   b c
*
* 383 777 886
*
*
* 4092 / 2 = 2046
* 2429 - 2046 = 383
*
*
* 1160 1269 1663
* a+B  a + c b +c
* 2 a + b + c
* 1160 - 383 =
*
* N개의 합 == 다 더하고 N-1로 나눠
* */
