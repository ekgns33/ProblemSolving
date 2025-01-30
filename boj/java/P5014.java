import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P5014 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    boolean[] visited = new boolean[input[0] + 1];
    Queue<Integer> q = new LinkedList<>();

    q.add(input[1]);
    visited[input[1]] = true;
    int attempt = 0;
    int nextLevels = 1;
    while(!q.isEmpty()) {
      for(int i = 0; i < nextLevels; i++) {
        int curLevel = q.poll();
        if(curLevel == input[2]) {
          System.out.println(attempt);
          return;
        }
        if(curLevel + input[3] < input[0] && !visited[curLevel + input[3]]) {
          q.add(curLevel + input[3]);
          visited[curLevel + input[3]] = true;
        }
        if(curLevel - input[4] > 0 && !visited[curLevel - input[4]]) {
          q.add(curLevel - input[4]);
          visited[curLevel - input[4]] = true;
        }
      }
      attempt += 1;
      nextLevels = q.size();
    }
    System.out.println("use the stairs");
  }
}
