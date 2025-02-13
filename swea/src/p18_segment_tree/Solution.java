package p18_segment_tree;

/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// double b = 1.0;
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{

  static int[] arr = new int [1000000];
  static int[][] tree = new int[2][4000000];

  public static void main(String args[]) throws Exception
  {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
    System.setIn(new FileInputStream("res/sample_input_18.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
    Scanner sc = new Scanner(System.in);
    int T;
    T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/


    int N;
    int Q;
    for(int test_case = 1; test_case <= T; test_case++)
    {
      System.out.print("#" + test_case);
      N = sc.nextInt();
      Q = sc.nextInt();
      for(int i = 1; i <= N; i++) {
        arr[i] = sc.nextInt();
      }
      init(1, 1, N);


     // System.out.println();
      for(int i = 0; i < Q; i++) {
        int command = sc.nextInt();
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();
        if(command == 1) {
          int [] ret = query(1, 1, N, v1+1, v2);
         System.out.print(" " + (ret[1] - ret[0]));
        } else {
          update(1, 1, N, v1+1, v2);
        }
      }
      System.out.println();
    }
  }

  static int[] query(int node, int ts, int te, int qs, int qe) {
    if(te < qs || qe < ts) return new int[] {Integer.MAX_VALUE, 0};
    if(qs <= ts && te <= qe) return new int[] {tree[0][node], tree[1][node]};
    int mid = (ts + te) / 2;
    int[] ret = query(node*2, ts, mid, qs, qe);
    int[] ret2 = query(node*2 + 1, mid + 1, te, qs, qe);
    return new int[] {Math.min(ret[0], ret2[0]), Math.max(ret[1], ret2[1])};
  }


  static void update(int node, int s, int e, int v1, int v2) {

    if(e < v1 || v1 < s) return;
    if(s == e) {
      tree[0][node] = v2;
      tree[1][node] = v2;
      return;
    }
    int mid = (s + e) / 2;
    update(node * 2 , s, mid, v1, v2);
    update(node *2 + 1, mid + 1, e, v1, v2);
    tree[0][node] = Math.min(tree[0][node*2], tree[0][node * 2 + 1]);
    tree[1][node] = Math.max(tree[1][node*2], tree[1][node * 2 + 1]);
  }


  static int[] init(int node, int s, int e) {

    if(s == e) {
      tree[0][node] = arr[s];
      tree[1][node] = arr[s];
      return new int[] {tree[0][node], tree[1][node]};
    }
    int mid = (s + e) / 2;
    int[] left = init(node * 2, s, mid);
    int[] right = init(node * 2 + 1, mid+1, e);
    tree[0][node] = Math.min(left[0], right[0]);
    tree[1][node] = Math.max(left[1], right[1]);
    return new int[] {tree[0][node], tree[1][node]};
  }


}
