import java.util.Scanner;

public class P2501 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        int q = sc.nextInt();
        for(int i = 1; i <= p; i++) {
            if(p % i == 0) {
                q--;
                if(q == 0) {
                    System.out.println(i);
                    return;
                }
            }
        }
        if(p != 0) {
            System.out.println(0);
        }
    }

}
