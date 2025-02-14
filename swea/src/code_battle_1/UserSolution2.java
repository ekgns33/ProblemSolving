package code_battle_1;

import java.util.*;

class UserSolution2
{

  static int MAX_N = 20;
  static int MAX_M = 5;
  static int[][] map = new int[MAX_N][MAX_N];
  static int[][] mmap  = new int[MAX_N][MAX_N];
  static int N;
  static Map<Integer, List<Node>> keys = new HashMap<>();
  class Node {
    int x, y;
    boolean row;
    boolean reversed;
    public Node(int x, int y, boolean row, boolean reversed) {
      this.x = x;
      this.y = y;
      this.row = row;
      this.reversed = reversed;
    }
  }

  public void init(int N, int mMap[][])
  {
    this.N = N;
    keys.clear();
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        map[i][j] = mMap[i][j];
        mmap[i][j] = mMap[i][j];
      }
    }
    for(int i = 0; i < 10000; i++) {
      keys.put(i, new ArrayList<>());
    }

    for(int l = 2; l <= 5; l++) {
      // row Key
      for(int i = 0; i <  N; i++) {
        for(int j = 0; j < N - l + 1; j++) {
          int key = 0;
          for(int k = 0; k < l - 1; k++) {
            key = key * 10 +  map[i][j + k + 1] - map[i][j + k] + 5;
          }
          keys.get(key).add(new Node(i, j, true, true));

          int reverseKey = 0;
          for(int k = l-1; k >= 1; k--) {
            reverseKey = reverseKey * 10 +  map[i][j + k-1] - map[i][j + k] + 5;
          }
          if(key != reverseKey) {
            keys.get(reverseKey).add(new Node(i, j, true, false));
          }
        }
      }
      // col Key
      for(int i = 0; i < N; i++) {
        for(int j = 0; j < N - l + 1; j++) {
          int key = 0;
          for(int k = 0; k < l-1; k++) {
            key = key * 10 + map[j + k + 1][i] - map[j + k][i] + 5;
          }
          keys.get(key).add(new Node(j, i, false, true));
          int reverseKey = 0;
          for(int k = l-1; k >= 1; k--) {
            reverseKey = reverseKey * 10 + map[j + k-1][i] - map[j + k][i] + 5;
          }
          if(key != reverseKey) keys.get(reverseKey).add(new Node(j, i, false, false));
        }
      }
    }


  }

  public int numberOfCandidate(int M, int mStructure[])
  {
    if(M == 1) return N * N;
    int key = 0;
    for(int i = 0; i < M-1; i++) {
      key = key * 10 + (mStructure[i] - mStructure[i + 1] + 5);
    }
    List<Node> l = keys.get(key);
    if(l == null) return 0;
    return l.size();
  }

  static int[][] directions = new int[][] {
      {1,0}, {-1,0}, {0,1}, {0,-1}
  };

  static int[] visited = new int[MAX_N];

  int bfs(int level) {
    for(int i = 0; i < N; i++) {
      visited[i] = (1 << N);
    }
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < N; i++)
    {
      for (int j = 0; j < N; j++)
      {
        if (i == 0 || i == N-1 || j == 0 || j == N-1)
        {
          if(mmap[i][j] < level) {
            q.add(new int[] {i, j});
            visited[i] |= (1 <<j);
          }
        }
      }
    }
    int cnt = 0;
    while(!q.isEmpty()) {
      int[] cur = q.poll();
      cnt++;
      for(int[] direction : directions) {
        int x = cur[0] + direction[0];
        int y = cur[1] + direction[1];
        if(x < 0 || x >= N || y < 0 || y >= N || level <= mmap[x][y] || (visited[x] & (1<<y)) != 0) continue;
        visited[x] |= (1 <<y);
        q.add(new int[] {x, y});
      }
    }
    return cnt;
  }


  public int maxArea(int M, int mStructure[], int mSeaLevel)
  {
    int max = -1;
    if(M == 1) {

      for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
          mmap[i][j] += mStructure[0];
          int ret = N*N - bfs(mSeaLevel);
          max = Math.max(max, ret);
          mmap[i][j] -= mStructure[0];
        }
      }
      return max;
    }

    int key = 0;
    for(int i = 0; i < M-1; i++) {
      key = key * 10 + (mStructure[i] - mStructure[i+1] + 5);
    }
    List<Node> nodes = keys.get(key);
    if (nodes == null) return -1;
    for(Node node : nodes) {
      if(node.row) {
        int h = mStructure[0] + (node.reversed ? map[node.x][node.y] : map[node.x][node.y + M - 1]);
        for(int i = 0; i < M; i++) {
          mmap[node.x][node.y + i] = h;
        }
        int ret = N*N - bfs(mSeaLevel);
        max = Math.max(max, ret);
        for(int i = 0; i < M; i++) {
          mmap[node.x][node.y + i] = map[node.x][node.y + i];
        }
      } else {
        int h = mStructure[0] + (node.reversed ? map[node.x][node.y] : map[node.x + M - 1][node.y]);
        for (int i = 0; i < M; i++) {
          mmap[node.x + i][node.y] = h;
        }
        int ret = N*N - bfs(mSeaLevel);
        max = Math.max(max, ret);
        for (int i = 0; i < M; i++) {
          mmap[node.x + i][node.y] = map[node.x + i][node.y];
        }
      }
    }

    return max;
  }
}