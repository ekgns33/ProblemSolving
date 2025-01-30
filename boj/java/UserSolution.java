  import java.util.ArrayList;
  import java.util.HashSet;
  import java.util.List;
  import java.util.Set;

  class UserSolution {

    private final static int MAX_N	= 1000;
    private final static int MAX_M	= 20;
    private final static int MAX_ROTATE = 4;
    private final static int TREASURE = 1;
    private final static int PIVOT = 9;
    // 10^6 * 4 byte : 4mb
    private final static int[][] map = new int[MAX_N][MAX_N];
    private final static Set<int[]> treasurePoints = new HashSet<>();
    private static int count = 0;
    private static int curPiece = 0;
    private static int[][] pivots = new int[4][2];
    private static List<int[]>[] points = new ArrayList[4];


    public void init(int N, int M, int Map[][]) {
      for(int i = 0; i < N; i ++) {
        for(int j = 0; j < N; j++) {
          map[i][j] = Map[i][j];
          if(Map[i][j] != 0) treasurePoints.add(new int[] {i, j});
        }
      }
    }

    private void rotateAndSetPivot() {
      ++curPiece;
      List<int[]> l = points[curPiece-1];
      for(int[] p : l) {
        int newX = p[1];
        int newY = MAX_M - p[0] - 1;
        points[curPiece].add(new int[] {newX, newY});
      }
      pivots[curPiece][0] = pivots[curPiece-1][1];
      pivots[curPiece][1] = MAX_M - pivots[curPiece-1][0] - 1;
    }


    private boolean compareTreasureMap(int x, int y) {
      int pivotX = pivots[curPiece][0];
      int pivotY = pivots[curPiece][1];
      int treasureCount = 0;
      List<int[]> l = points[curPiece];
      for(int[] point : l) {
        int mapX = x - pivotX + point[0];
        int mapY = y - pivotY + point[1];
        if(mapX < 0 || mapY < 0 || mapX >= MAX_N || mapY >= MAX_N) {
          continue;
        }
        if(map[mapX][mapY] != 0) {
          treasureCount++;
        } else {
          return false;
        }
      }
      return treasureCount == count;
    }

    public Solution.Result findTreasureChest(int Pieces[][]) {
      Solution.Result res = new Solution.Result();
      res.y = res.x = 0;
      count = 0;
      curPiece = 0;
      for(int i = 0; i < MAX_ROTATE; i++) {
        points[i] = new ArrayList<>();
      }
      for(int i = 0; i < MAX_M; i++) {
        for(int j = 0; j < MAX_M; j++) {
          if(Pieces[i][j] == TREASURE) {
            points[curPiece].add(new int[] {i, j});
            count++;
          } else if(Pieces[i][j] == PIVOT) {
            points[curPiece].add(new int[] {i, j});
            count++;
            pivots[curPiece][0] = i;
            pivots[curPiece][1] = j;
          }
        }
      }
      rotateAndSetPivot();
      rotateAndSetPivot();
      rotateAndSetPivot();
      curPiece = 0;
      for(int[] treasure : treasurePoints) {
        int x = treasure[0];
        int y = treasure[1];
        for(int rotate = 0; rotate < MAX_ROTATE; rotate++) {
          if(compareTreasureMap(x, y)) {
            res.y = x;
            res.x = y;
            return res;
          }
          curPiece++;
        }
        curPiece = 0;
      }
      return res;
    }


  }
