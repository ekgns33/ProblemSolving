import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P1007 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T  = Integer.parseInt(in.readLine());
        for(int i = 0; i < T; i++) {

            MIN = Integer.MAX_VALUE;
            int n = Integer.parseInt(in.readLine());
            List<int[]> vectors = new ArrayList<>();

            for(int j = 0; j < n; j++) {
                int[] vector = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                vectors.add(vector);
            }
            //do something
            solution(vectors, new boolean[n], 0, -1);
            System.out.println(MIN);
        }


    }

    static double MIN = Integer.MAX_VALUE;
    private static void solution(List<int[]> vectors, boolean[] visited, int cnt, int prev) {
        if(cnt == vectors.size()/2) {
            int x = 0;
            int y = 0;
            for (int i = 0; i < vectors.size(); i++) {
                if(visited[i]) {
                    x -=vectors.get(i)[0];
                    y -= vectors.get(i)[1];
                } else {
                    x += vectors.get(i)[0];
                    y += vectors.get(i)[1];
                }
            }
            MIN = Math.min(MIN, calculateDistance(x, y));
            return;
        }
        for(int i = prev+1; i < visited.length; i++) { // 여기서 TLE발생! 어차피 n/2고르면 되는 문제라서 + 1
            if(!visited[i]) {
                visited[i] = true;
                solution(vectors, visited, cnt + 1, i);
                visited[i] = false;
            }
        }

    }
    private static double calculateDistance(int x, int y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

}
