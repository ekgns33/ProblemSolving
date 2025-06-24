import java.util.Scanner;

public class P1072 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int z = (int)((double)y * 100 / x); // 부동소수점 오차 없애기
        int left = 0;
        int right = x;
        if(x == y) {
            System.out.println(-1);
            return;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int nx = x + mid;
            int ny = y + mid;
            int next =  (int)((double) ny *100/ nx); // 부동소수점 오차 없애기
            if (next <= z) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if(left > x) {
            System.out.println(-1);
            return;
        }
        System.out.println(left);
    }

}
