import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class P1021 {


    static Deque<Integer> deq = new ArrayDeque<>();
    public static void main(String[] args) {
        int N, M;
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        int[] target = new int[M];
        for(int i = 0; i < M; i++) {
            target[i] = in.nextInt();
        }
        for(int i = 1; i <= N; i++) {
            deq.addLast(i);
        }
        int cnt = 0;
        for(int t : target) {
            if(t == deq.peekFirst()) {
                deq.removeFirst();
                continue;
            }
            int distance = 0;
            for(int next : deq) {
                if(next == t) {
                    break;
                }
                distance++;
            }
            int rightRoll = distance;
            int leftRoll = deq.size() - distance;
            if(rightRoll > leftRoll) {
                for(int k = 0; k < leftRoll; k++) {
                    rollLeft();
                    cnt++;
                }
            } else {
                for(int k = 0; k < rightRoll; k++) {
                    rollRight();
                    cnt++;
                }
            }
            deq.removeFirst();
        }
        System.out.println(cnt);
    }

    private static void rollLeft() {
        if(deq.isEmpty()) return;
        int head = deq.removeLast();
        deq.addFirst(head);
    }

    private static void rollRight() {
        if(deq.isEmpty()) return;
        int last = deq.removeFirst();
        deq.addLast(last);
    }

}
