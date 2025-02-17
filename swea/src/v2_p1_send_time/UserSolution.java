package v2_p1_send_time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class UserSolution {

  static int MAX_D = Integer.MAX_VALUE;
  static int MAX_N = 30100;
  static List<long[]>[] superNode = new ArrayList[MAX_N];
  static List<long[]>[] subNode = new ArrayList[MAX_N];
  static long[][][] superDist = new long[302][4][4];
  static long[] dist = new long[MAX_N];
  static int n;


  static {
    for(int i=0; i<MAX_N; i++){
      superNode[i] = new ArrayList<>();
      subNode[i] = new ArrayList<>();
    }
  }

  int[] visited = new int[MAX_N];
  int curQuery = 0;

  void calcDistSubGroup(int from) {
    //System.out.println("from : " + from);
    PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));
    pq.add(new long[]{from, 0});
    ++curQuery;
    dist[from] = 0;
    visited[from] = curQuery;
    while(!pq.isEmpty()){
      long[] cur = pq.poll();
      int curNode = (int)cur[0];
      long curDist = cur[1];
      //System.out.println("curNode : " + curNode + " curDist : " + curDist);
      if(dist[curNode] != curDist) continue;
      for(long[] edge : subNode[curNode]){
        //supernode
        int nextNode = (int)edge[0];
        long nextDist = curDist + edge[1];
        if(visited[nextNode] != curQuery || dist[nextNode] > nextDist){
          if((edge[0] % 100) <= 3) {
            //System.out.println("NEXT : " + edge[0]);
            superDist[from/ 100][from % 100][nextNode%100] = nextDist;
            superDist[nextNode / 100][nextNode % 100][from % 100] = nextDist;
            pq.add(new long[]{nextNode, nextDist});
            visited[nextNode] = curQuery;
            dist[nextNode] = nextDist;
          } else { // sub node
            pq.add(new long[]{nextNode, nextDist});
            visited[nextNode] = curQuery;
            dist[nextNode] = nextDist;
          }
        }
      }
    }
  }


  void shortestPath(int srcGroup) {

    for(int i = 1; i <= 3; i++) {
      for(int j = 1; j <= 3; j++) {
        superDist[srcGroup][i][j] = MAX_D;
      }
    }
    for(int i = 1; i <= 3; i++) {
      calcDistSubGroup(srcGroup* 100 + i);
    }
  }

  public void init(int N, int K, int mNodeA[], int mNodeB[], int mTime[])
  {
    this.n = N;
    for(int i=0; i<N * 100 + 30; i++){
      superNode[i].clear();
      subNode[i].clear();
    }
    for(int i=0; i<K; i++){
      int src = mNodeA[i];
      int dst = mNodeB[i];
      int srcGroup = src / 100;
      int dstGroup = dst / 100;
      if(srcGroup != dstGroup){
        superNode[src].add(new long[] {dst, mTime[i]});
        superNode[dst].add(new long[] {src, mTime[i]});
      } else {
        subNode[src].add(new long[] {dst, mTime[i]});
        subNode[dst].add(new long[] {src, mTime[i]});
      }
    }

    for(int i = 1; i <= n; i++) {
      shortestPath(i);
    }

  }

  public void addLine(int mNodeA, int mNodeB, int mTime)
  {
    if(mNodeA / 100 == mNodeB / 100){
      subNode[mNodeA].add(new long[] {mNodeB, mTime});
      subNode[mNodeB].add(new long[] {mNodeA, mTime});
      shortestPath(mNodeA / 100);
    } else {
      superNode[mNodeA].add(new long[] {mNodeB, mTime});
      superNode[mNodeB].add(new long[] {mNodeA, mTime});
    }
  }

  public void removeLine(int mNodeA, int mNodeB)
  {
    if(mNodeA / 100 == mNodeB / 100){
      subNode[mNodeA].removeIf(node -> node[0] == mNodeB);
      subNode[mNodeB].removeIf(node -> node[0] == mNodeA);
      shortestPath(mNodeA / 100);
    } else {
      superNode[mNodeA].removeIf(node -> node[0] == mNodeB);
      superNode[mNodeB].removeIf(node -> node[0] == mNodeA);
    }
  }

  public int checkTime(int mNodeA, int mNodeB)
  {
    PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));
    pq.add(new long[]{mNodeA, 0});
    visited[mNodeA] = ++curQuery;
    dist[mNodeA] = 0;

    while(!pq.isEmpty()){
      long[] cur = pq.poll();
      int curNode = (int)cur[0];
      long curDist = cur[1];
      //System.out.println(curNode + " " + curDist);
      for(long[] edge : superNode[curNode]){
        int nextNode = (int)edge[0];
        long nextDist = curDist + edge[1];
        if(visited[nextNode] != curQuery || dist[nextNode] > nextDist){
          pq.add(new long[]{nextNode, nextDist});
          visited[nextNode] = curQuery;
          dist[nextNode] = nextDist;
        }
      }
      if(curNode <= 3) continue;
      int group = curNode / 100;
      int node = curNode % 100;
      for(int i = 1; i<=3; i++) {
        int nextNode = group * 100 + i;
        long nextDist = superDist[group][node][i] + curDist;
        if(visited[nextNode] != curQuery || dist[nextNode] > nextDist){
          pq.add(new long[]{nextNode, nextDist});
          visited[nextNode] = curQuery;
          dist[nextNode] = nextDist;
        }
      }
    }


    return (int)dist[mNodeB];
  }
}