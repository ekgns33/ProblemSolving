import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P15723 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] parent = new int[26];

    Map<Character, List<Character>> graph = new HashMap<>();
    for(int i = 0; i < n; i++) {
      String[] tokens = br.readLine().split(" ");
      char from = tokens[0].charAt(0);
      char to = tokens[2].charAt(0);
      graph.putIfAbsent(from, new ArrayList<>());
      graph.get(from).add(to);
    }

    int T = Integer.parseInt(br.readLine());
    for(int i = 0; i < T; i++) {
      int v = 1 << 26;
      String[] tokens = br.readLine().split(" ");
      char from = tokens[0].charAt(0);
      char to = tokens[2].charAt(0);

      Queue<Character> q = new LinkedList<>();
      q.add(from);
      v = v | (1<<(from-'a'));
      boolean suc = false;
      while(!q.isEmpty()) {
        char cur = q.poll();
        if(cur == to) {
          suc = true;
          break;
        }
        List<Character> l = graph.get(cur);
        if(l == null) break;
        for(char next : l) {
          if((v & (1<< (next-'a'))) == 0) {
            q.add(next);
            v = (v | (1<< (next - 'a')));
          }
        }
      }
      if(suc) {
        System.out.println("T");
      } else {
        System.out.println("F");
      }
    }
  }
}
