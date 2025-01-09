import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class P1655 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> right = new PriorityQueue<>();

    boolean even = false;
    for(int i = 0; i < N; i++) {
      int next = Integer.parseInt(br.readLine());

      if(even) {
        right.add(next);
        left.add(right.poll());
        System.out.println(left.peek());
      } else {
        left.add(next);
        right.add(left.poll());
        System.out.println(right.peek());
      }
      even = !even;
    }

  }
}
