import java.io.*;
import java.util.Arrays;

public class P10868 {
  static class Node{
    int min;
    int max;
    int cnt;
    Node(int min, int max, int cnt){
      this.min = min;
      this.max = max;
      this.cnt = cnt;
    }
  }

  static Node[] tree = new Node[100100 * 4];
  static int N;
  static void update(int n, int s, int e, int l, int r, int x) {
    if(r < s || e < l) return;
    if(l <= s && e <= r) {
      tree[n].min = Math.min(tree[n].min, x);
      return;
    }
    int mid = (s + e) >> 1;
    update(n << 1, 1, mid, l, r, x);
    update((n << 1) | 1, mid + 1, e, l, r, x);
    tree[n] = merge(tree[n << 1], tree[(n<<1) |1]);
  }
  static Node merge(Node a, Node b) {
    return new Node(Math.min(a.min, b.min), 0, 0);
  }
  static Node init(int[] arr, int n, int s, int e) {
    if(s == e) return tree[n] = new Node(arr[s], arr[s],0);
    int mid = (s + e) >> 1;
    Node a = init(arr, n << 1 , s, mid);
    Node b = init(arr, n <<1 | 1, mid + 1, e);
    return tree[n] = merge(a, b);
  }

  static Node query(int n, int s, int e, int l, int r) {
    if(r < s || e < l) return new Node(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    if(l <= s && e <= r) {
      return tree[n];
    }
    int mid = (s + e) >> 1;
    return merge(query(n << 1, s, mid, l, r), query((n << 1) | 1, mid + 1, e, l, r));
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    N = input[0];
    int M = input[1];
    int[] arr = new int[N];
    for(int i = 0; i < N; i++) {
      int next = Integer.parseInt(br.readLine());
      arr[i] = next;
    }
    init(arr, 1, 0, N -1);

    for(int i = 1; i <= M; i++) {
     int[] range = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      System.out.println(query(1, 1, N, range[0], range[1]).min);
    }
  }
}
