import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P16946 {
	static boolean[][] visited;
	static int N;
	static int M;
	static int[][] directions = {
		{1, 0},
		{-1, 0},
		{0, 1},
		{0, -1}
	};
	static final int WALL = 1;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


	static class Cell {

		int connections;
		int groupId;

		public Cell (int count, int group) {
			this.connections = count;
			this.groupId = group;
		}

		public boolean isWall() {
			return groupId == -1;
		}
	}

	public static void readBoard(Cell[][] board) throws IOException {
		for(int i = 0; i < N; i++) {
			String[] tokens = br.readLine().split("");
			for(int j = 0; j < M; j++) {
				int cell = Integer.parseInt(tokens[j]);
				if(cell == WALL) {
					board[i][j] = new Cell(0, -1);
				} else {
					board[i][j] = new Cell(0, 0);
				}
			}
		}
	}

	public static int getAllSpaces(Cell[][] board, int x, int y) {

		int aggr = 1;
		visited[x][y] = true;
		for(int[] direction : directions) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if(nx < 0 || nx >= N || ny <0 || ny >= M ||visited[nx][ny] || board[nx][ny].groupId != 0) continue;
			aggr += getAllSpaces(board, nx, ny);
		}
		return aggr;
	}
	public static void paintSpacesWithCountAndGroup(Cell[][] board, int x, int y, int count, int groupId) {
		board[x][y].connections = count;
		board[x][y].groupId = groupId;
		for(int[] direction : directions) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if(nx < 0 || nx >= N || ny < 0 || ny >= M || !visited[nx][ny]|| board[nx][ny].groupId != 0) continue;
			paintSpacesWithCountAndGroup(board, nx, ny, count, groupId);
		}
	}

	public static void main(String[] args) throws IOException {

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = input[0];
		M = input[1];
		Cell[][] board = new Cell[N][M];
		visited = new boolean[N][M];

		// READ O(MN)
		readBoard(board);

		int lastGroupId = 1;
		// O(2 * MN)
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j].groupId == 0) {
					//dfs and modify values
					int connectedSpaces = getAllSpaces(board, i, j);
					paintSpacesWithCountAndGroup(board, i, j, connectedSpaces, lastGroupId);
					lastGroupId++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!board[i][j].isWall()) {
					sb.append("0");
					continue;
				}
				int possibleSpaces = 0;
				Set<Integer> set = new HashSet<>();
				for(int[] direction : directions) {
					int nx = i + direction[0];
					int ny = j + direction[1];
					if(nx < 0 || nx >= N || ny <0 || ny >= M || board[nx][ny].groupId == -1 || set.contains(board[nx][ny].groupId)) continue;
					possibleSpaces += (board[nx][ny].connections) % 10;
					set.add(board[nx][ny].groupId);
				}
				sb.append((possibleSpaces + 1) % 10);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}
