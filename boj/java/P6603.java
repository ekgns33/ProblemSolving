import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P6603 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    Set<Integer> set = new HashSet<>();
    while(tokens[0] != 0) {
      int n = tokens[0];
      for(int i = 1; i < tokens.length; i++) {
        set.add(tokens[i]);
        find(tokens, i+1, set, String.valueOf(tokens[i]));
        set.remove(tokens[i]);
      }
      tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      System.out.println();
    }
  }
  static void find(int[] tokens, int p, Set<Integer> set, String s) {
    if(set.size() == 6) {
      System.out.println(s);
      return;
    }

    for(int i = p; i < tokens.length; i++) {
      set.add(tokens[i]);
      find(tokens, i+1, set, s + " " + tokens[i]);
      set.remove(tokens[i]);
    }
  }
}
