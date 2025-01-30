import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class UserSolution2
{
  private static final int PLAYER1 = 1;
  private static final int PLAYER2 = 2;
  private static final int MAX_H = 200;
  private static final int MAX_W = 200;
  private static final int DROP_NUMS = 3;
  private static final int[][] directions = {
      {1, 0}, {0, 1}, {-1, 0}, {0, -1}
  };
  private static final int[][] diagonals = {
      {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
  };
  // state
  private static int p1Score = 0;
  private static int p2Score = 0;
  private static int burstP1 = 0;
  private static int burstP2 = 0;
  private static Column[] columns = new Column[200];
  private static int WIDTH = 0;
  private static int HEIGHT = 0;

  class Column {
    int[] blocks;
    int top;
    Column(int size) {
      blocks = new int[size];
      top = 0;
    }

    void init() {
      this.top = 0;
      Arrays.fill(blocks, 0);
    }
    void push(int block) {
      blocks[top++] = block;
    }

    // O(H) : H = 200
    void removeInvalidBlocksAndUpdateBurst() {
      int newTop = 0;
      for(int i = 0; i <= top; i++) {
        if (blocks[i] == -1) {
          burstP1++;
        } else if (blocks[i] == -2) {
          burstP2++;
        } else if(blocks[i] != 0) {
          blocks[newTop++] = blocks[i];
        } else {
          blocks[newTop] = 0;
          break;
        }
      }
      for (int i = newTop; i < HEIGHT; i++) {
        blocks[i] = 0;
      }
      top = newTop;
    }

    int bottom() {
      return blocks[0];
    }
  }
  /*
  *
  * 1. 플레이어는 한턴씩 번갈아가면서 게임 진행
  * 2. 두가지 선택
  *   2.1 : 특정 위치에서 3줄짜리 블럭 떨구기
  *   2.2 : 최하단 위치의 상대방 블럭 선택해서 연결된거 다 바꾸기 >> bfs
  * 3. 블럭 5개 연결성 확인 후 제거 (점수 합산) >> dfs and mark
  * 4. 블럭 떨구기
  * 5. 5개 연결성 확인 후 없으면 다음 턴 진행
  *
  *
  * */


  void init(int W, int H)
  {
    p1Score = 0;
    p2Score = 0;
    WIDTH = W;
    HEIGHT = H;
    for(int i = 0; i < MAX_W; i++) {
      if (columns[i] == null) {
        columns[i] = new Column(MAX_H);
      }
      columns[i].init();
    }
  }

  int dropBlocks(int mPlayer, int mCol)
  {
    for(int i = 0; i < DROP_NUMS; i++) {
      columns[mCol + i].push(mPlayer);
    }
    int earnedScore = removeMatchedBlocksAndFall(mPlayer);
    if (mPlayer == PLAYER1) {
      p1Score += earnedScore;
    } else {
      p2Score += earnedScore;
    }
    return earnedScore;
  }

  private int removeMatchedBlocksAndFall(int mPlayer) {
    int ret= 0;
    while(true) {
      int burst = checkMatchedBlocksAndRemove(mPlayer);
      ret += mPlayer == PLAYER1 ? burstP1 : burstP2;
      if(burst == 0) {
        break;
      }
    }
    return ret;
  }

  private void changeBlocksWithBFS(int mPlayer, int mCol) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[] {mCol, 0});
    columns[mCol].blocks[0] = mPlayer;
    while(!queue.isEmpty()) {
      int[] curPoint = queue.poll();
      for(int[] direction : directions) {
        int nx = curPoint[0] + direction[0];
        int ny = curPoint[1] + direction[1];
        if(nx < 0 || nx >= WIDTH || ny < 0 || ny >= HEIGHT || columns[nx].blocks[ny] == mPlayer || columns[nx].blocks[ny] == 0) {
          continue;
        }
        queue.add(new int[] {nx, ny});
        columns[nx].blocks[ny] = mPlayer;
      }
    }
  }

  int changeBlocks(int mPlayer, int mCol)
  {
    if(columns[mCol].bottom() != mPlayer && columns[mCol].bottom() != 0) {
      changeBlocksWithBFS(mPlayer, mCol);
    }
    //do some logic
    int earnedScore = removeMatchedBlocksAndFall(mPlayer);
    if (mPlayer == PLAYER1) {
      p1Score += earnedScore;
    } else {
      p2Score += earnedScore;
    }
    return earnedScore;
  }


  private int checkMatchedBlocksAndRemove(int mPlayer) {
    // current player
    markRemoveTarget(mPlayer);
    // opponent player
    markRemoveTarget(mPlayer == PLAYER1 ? PLAYER2 : PLAYER1);
    burstP1 = 0;
    burstP2 = 0;
    for(int col = 0; col < WIDTH; col++) {
      columns[col].removeInvalidBlocksAndUpdateBurst();
    }
    return burstP1 + burstP2;
  }

  private void markRemoveTarget(int mPlayer) {
    for(int col = 0; col < WIDTH; col++) {
      int startRow = columns[col].top - 1;
      for(int row = startRow; row >= 0; row--) {
        if(Math.abs(columns[col].blocks[row]) == mPlayer) {
          markStraightIfMoreThanFive(col, row, mPlayer);
          markDiagonalIfMoreThanFive(col, row, mPlayer);
        }
      }
    }
  }

  private void markStraightIfMoreThanFive(int col, int row, int mPlayer) {
    for(int[] direction : directions) {
      markIfMoreThanFive(direction, col, row, mPlayer, 0);
    }
  }

  private void markDiagonalIfMoreThanFive(int col, int row, int mPlayer) {
    for(int[] diag : diagonals) {
      markIfMoreThanFive(diag, col, row, mPlayer, 0);
    }
  }

  private boolean markIfMoreThanFive(int[] direction, int x, int y, int mPlayer, int cnt) {
    if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT || Math.abs(columns[x].blocks[y]) != mPlayer) {
      return cnt >= 5;
    }
    if(markIfMoreThanFive(direction, x + direction[0], y + direction[1], mPlayer, cnt + 1)) {
      columns[x].blocks[y] = -mPlayer;
      return true;
    }
    return false;
  }

  private void countLastingPlayerBlocks(int[] mBlockCnt) {
    mBlockCnt[0] = 0;
    mBlockCnt[1] = 0;
    for (int i = 0; i < WIDTH; i++) {
      for(int block : columns[i].blocks) {
        if(block == PLAYER1) {
          mBlockCnt[0]++;
        } else if(block == PLAYER2) {
          mBlockCnt[1]++;
        }
      }
    }
  }

  int getResult(int[] mBlockCnt)
  {
    countLastingPlayerBlocks(mBlockCnt);
    if(p1Score > p2Score) {
      return 1;
    } else if(p1Score < p2Score) {
      return 2;
    } else {
      return 0;
    }
  }

}