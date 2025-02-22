package v3_p4_town;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class UserSolution {

  final int MAX_N = 25000;
  final int MAX_R = 101;

  class Node{
    boolean alive; // 존재 여부
    int idx, id;
    int x, y;
    Node next;
  }
  Node[] House = new Node[MAX_N];

  HashMap< Integer, Integer > Hash = new HashMap<>();          // id 저장 hash
  int L, Idx, TownCnt, newR;
  int[] Parent = new int[MAX_N], Cnt = new int[MAX_N];       // 부모 배열과 마을내 집 개수 배열
  Node [] Head = new Node[MAX_N];                            // 집이 속한 마을의 첫 집
  Node [] Tail= new Node[MAX_N];                             // 집이 속한 마을의 마지막 집
  ArrayList< Integer >[][] Map = new ArrayList[MAX_R][MAX_R];; // 압축에 사용할 Map



  // union find
  int find(int x) {
    if (Parent[x] == x) return x;
    return Parent[x] = find(Parent[x]); // 압축
  }

  void Union(int x, int y) {
    x = find(x);
    y = find(y);
    if (x == y) return; // 이미 합쳐져 있다면 종료

    TownCnt--; // 전체 마을 개수 -1
    if (House[x].id < House[y].id) { // id가 작은쪽으로 합쳐지도록
      Parent[y] = x;               // 부모 변경
      Cnt[x] += Cnt[y];            // 개수 합치기
      Tail[x].next = Head[y];      // 마을 이어주기
      Tail[x] = Tail[y];
    }
    else { // 반대의 경우
      Parent[x] = y;
      Cnt[y] += Cnt[x];
      Tail[y].next = Head[x];
      Tail[y] = Tail[x];
    }
  }

  public void init(int L, int R) {
    this.L = L;
    Idx = TownCnt = 0;
    newR = R / L + 1; // 좌표 압축
    for (int i = 0; i < newR; i++)
      for (int j = 0; j < newR; j++)
        Map[i][j] = new ArrayList<>();

    for(int i = 0 ; i < MAX_N; i++) {
      House[i] = new Node();
    }
  }

  public int add(int mId, int mX, int mY) {
    ++TownCnt;
    int cur_idx = Idx++;
    Hash.put(mId, cur_idx); // id로 index 저장

    // 집에 대한 정보 등록
    House[cur_idx].alive = true;
    House[cur_idx].idx = cur_idx;
    House[cur_idx].id = mId;
    House[cur_idx].x = mX;
    House[cur_idx].y = mY;
    House[cur_idx].next = null;

    Parent[cur_idx] = cur_idx; // 최초에는 자기 자신이 부모
    Cnt[cur_idx] = 1;
    Head[cur_idx] = Tail[cur_idx] = House[cur_idx];

    int cx = mX / L, cy = mY / L; // 좌표 압축
    Map[cx][cy].add(cur_idx);

    // 현재 좌표와 근처 8방향 좌표 확인
    for (int x = cx - 1; x <= cx + 1; ++x) {
      if (x < 0 || x > newR - 1) continue;
      for (int y = cy - 1; y <= cy + 1; ++y) {
        if (y < 0 || y > newR - 1) continue;

        for (int i : Map[x][y]) {
          if (!House[i].alive) continue; // 삭제한 집이라면 pass
          if (Math.abs(House[i].x - mX) + Math.abs(House[i].y - mY) <= L) // 거리 내에 있다면 마을 합치기
            Union(cur_idx, i);
        }
      }
    }
    return Cnt[find(cur_idx)]; // 마을의 집 개수 반환
  }

  public int remove(int mId) {
    int idx = Hash.get(mId);
    if (!House[idx].alive) return -1; // 이미 삭제됐다면 -1

    int pIdx = find(idx);     // 부모 index
    int cnt = Cnt[pIdx] - 1;  // 삭제하고난 집 개수
    House[idx].alive = false; // 삭제 처리
    if (cnt == 0) {           // cnt가 0이면 마을에 집이 하나밖에 없었으므로
      --TownCnt;            // 마을 삭제
      return 0;
    }

    // 유효한 집 중, id가 가장 작은 집을 minNode에 저장
    Node minNode = null;
    Node curr = Head[pIdx];
    while(curr != null) {
      if(curr.alive) {
        if(minNode == null || minNode.id > curr.id) {
          minNode = curr;
        }
      }
      curr = curr.next;
    }

    curr = Head[pIdx];
    while (curr != null) {
      if (curr.alive) {
        Parent[curr.idx] = minNode.idx; // 마을에 속한 집의 Parent 갱신
      }
      curr = curr.next;
    }

    // minNode의 idx를 활용해 Cnt, Head, Tail 배열 갱신
    Cnt[minNode.idx] = cnt;
    Head[minNode.idx] = Head[pIdx];
    Tail[minNode.idx] = Tail[pIdx];
    return cnt; // 삭제 후 집 개수 반환
  }

  public int check(int mId) {
    int idx = Hash.get(mId);
    if (!House[idx].alive) return -1; // 이미 없었다면 -1 반환
    return House[find(idx)].id;       // 마을의 가장 작은 집의 id 반환
  }

  public int count() {
    return TownCnt;
  }
}