import java.util.*;

                public class P15683 {

                    static int N, M, empty = 0, maxErased = 0;
                    static int[][] map = new int[10][10];
                    static List<int[]> cctvs = new ArrayList<>();
                    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // south, north, east, west
                    static int[][][] cctvDirections = {
                        {{0}, {1}, {2}, {3}}, // CCTV 1
                        {{0, 1}, {2, 3}},     // CCTV 2
                        {{0, 2}, {2, 1}, {1, 3}, {3, 0}}, // CCTV 3
                        {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // CCTV 4
                        {{0, 1, 2, 3}}        // CCTV 5
                    };

                    public static void main(String[] args) {
                        init();
                        solve();
                    }

                    public static void solve() {
                        dfs(0, 0);
                        System.out.println(empty - maxErased);
                    }

                    private static void dfs(int current, int totalErased) {
                        if (current == cctvs.size()) {
                            maxErased = Math.max(maxErased, totalErased);
                            return;
                        }

                        int[] cctv = cctvs.get(current);
                        int x = cctv[0], y = cctv[1], type = map[x][y] - 1;

                        for (int[] dirs : cctvDirections[type]) {
                            List<int[]> visited = new ArrayList<>();
                            for (int dir : dirs) {
                                draw(x, y, directions[dir], visited);
                            }
                            dfs(current + 1, totalErased + visited.size());
                            for (int[] cell : visited) {
                                map[cell[0]][cell[1]] = 0; // Revert changes
                            }
                        }
                    }

                    private static void draw(int x, int y, int[] direction, List<int[]> visited) {
                        int nx = x + direction[0];
                        int ny = y + direction[1];
                        while (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 6) {
                            if (map[nx][ny] == 0) {
                                map[nx][ny] = -1; // Mark as covered
                                visited.add(new int[]{nx, ny});
                            }
                            nx += direction[0];
                            ny += direction[1];
                        }
                    }

                    public static void init() {
                        Scanner in = new Scanner(System.in);
                        N = in.nextInt();
                        M = in.nextInt();
                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < M; j++) {
                                map[i][j] = in.nextInt();
                                if (map[i][j] == 0) empty++;
                                if (map[i][j] > 0 && map[i][j] < 6) {
                                    cctvs.add(new int[]{i, j});
                                }
                            }
                        }
                    }
                }