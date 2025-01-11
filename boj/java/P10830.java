import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P10830 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    int[][] matrix = new int[(int)input[0]][(int)input[0]];
    for(int i = 0; i < input[0]; i++) {
      matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    int[][] ret;

    ret = find(matrix, input[1]);
    for (int[] ints : ret) {
      for (int j = 0; j < matrix.length; j++) {
        System.out.print(ints[j] % 1000 + " ");
      }
      System.out.println();
    }
  }
  static int[][] find(int[][] matrix, long n) {
    if(n == 1) return matrix;
    if(n == 2) {
     return multiply(matrix, matrix);
    }
    long div = n / 2;
    int[][] next1;
    int[][] next2;
    if(n %2 == 0) {
      next1 = find(matrix, div);
      return multiply(next1, next1);
    } else {
      next1 = find(matrix, div);
      next2 = multiply(next1, matrix);
      return multiply(next1, next2);
    }
  }
  static int[][] multiply(int[][] mat1, int[][] mat2) {
    int[][] ret = new int[mat1.length][mat1.length];
    for(int i = 0; i < mat1.length; i++) {
      for(int j = 0; j < mat1[0].length; j++) {
        for(int k = 0; k < mat1.length; k++) {
          ret[i][j]  = (ret[i][j] +  (mat1[i][k] * mat2[k][j])) % 1000;
        }
      }
    }
    return ret;
  }
}
