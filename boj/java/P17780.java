import static java.util.Collections.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P17780 {

  static class Horse {
    int x;
    int y;
    int direction;
    int val;

    public Horse(int x, int y, int direction, int val) {
      this.x = x;
      this.y = y;
      this.direction = direction;
      this.val = val;
    }

    public void move(int nx, int ny) {
      x = nx;
      y = ny;
    }

    public void changeDirection() {
      if (direction == 0) direction = 1;
      else if (direction == 1) direction = 0;
      else if (direction == 2) direction = 3;
      else if (direction == 3) direction = 2;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Horse horse = (Horse) o;
      return val == horse.val;
    }
  }

  public static int N;
  public static int K;
  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static int[][] board;
  private static List<Horse>[][] piles;
  private static List<Horse> horses = new ArrayList<>();
  private static int round = 1;

  private static final int[][] directions = {
    {0, 1},
    {0, -1},
    {-1, 0},
    {1, 0}
  };

  public static void main(String[] args) throws IOException {
    int[] input = readAsInt();
    N = input[0];
    K = input[1];
    init();
    for (int i = 0; i < 1000; i++) {
      execute();
    }
    System.out.println("-1");
  }

  private static void execute() {
    for (int i = 1; i <= K; i++) {
      if (move(horses.get(i - 1))) {
        System.out.println(round);
        System.exit(0);
      }
    }
    round++;
  }

  private static boolean move(Horse horse) {
    if (!isBottom(horse)) return false;
    int x = horse.x;
    int y = horse.y;
    int nx = x + directions[horse.direction][0];
    int ny = y + directions[horse.direction][1];

    if (isOutOfBounds(nx, ny) || board[nx][ny] == 2) {
      horse.changeDirection();
      nx = x + directions[horse.direction][0];
      ny = y + directions[horse.direction][1];
      if (isOutOfBounds(nx, ny) || board[nx][ny] == 2) {
        return false;
      }
      return move(horse);
    } else if (board[nx][ny] == 1) {
      moveStack(x, y, nx, ny, true);
    } else if (board[nx][ny] == 0) {
      moveStack(x, y, nx, ny, false);
    }
    return piles[nx][ny].size() >= 4;
  }

  private static boolean isBottom(Horse horse) {
    return piles[horse.x][horse.y].get(0).equals(horse);
  }

  private static void moveStack(int x, int y, int nx, int ny, boolean reverse) {
    List<Horse> stack = piles[x][y];
    if (reverse) {
      for (int k = stack.size() - 1; k >= 0; k--) {
        Horse h = stack.get(k);
        h.move(nx, ny);
        piles[nx][ny].add(h);
      }
    } else {
      for (Horse h : stack) {
        h.move(nx, ny);
        piles[nx][ny].add(h);
      }
    }
    stack.clear();
  }

  private static boolean isOutOfBounds(int x, int y) {
    return x < 0 || y < 0 || x >= N || y >= N;
  }

  private static void init() throws IOException {
    board = new int[N][];
    piles = new ArrayList[N][N];
    readBoard();
    readPlayers();
  }

  private static void readPlayers() throws IOException {
    for (int i = 1; i <= K; i++) {
      int[] points = readAsInt();
      Horse horse = new Horse(points[0] - 1, points[1] - 1, points[2] - 1, i);
      horses.add(horse);
      piles[points[0] - 1][points[1] - 1].add(horse);
    }
  }

  private static void readBoard() throws IOException {
    for (int i = 0; i < N; i++) {
      board[i] = readAsInt();
      for (int j = 0; j < N; j++) {
        piles[i][j] = new ArrayList<>();
      }
    }
  }

  private static int[] readAsInt() throws IOException {
    return Arrays.stream(br.readLine().split(" "))
      .mapToInt(Integer::parseInt)
      .toArray();
  }
}