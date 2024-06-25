//
// Created by 문다훈 on 2023/12/21.
//
#include <bits/stdc++.h>
using namespace std;
int dist[300001];
vector<int> adj[300001];
int main (void) {
    int N, M, K, X;


    cin >> N >> M >> K >> X;
    for(int i = 1; i <=N; i++) {
        dist[i] = INT_MAX;
    }

    for(int i = 0; i < M; i++) {
        int src, dst;
        cin >> src >> dst;
        adj[src].push_back(dst);
    }
    queue<int> q;

    q.push(X);
    dist[X] = 0;
    while(!q.empty()) {

        int curN = q.front();
        q.pop();
        for(int j = 0; j < adj[curN].size(); j++) {
            int next = adj[curN][j];
            if(dist[next] > dist[curN] + 1) {
                dist[next] = dist[curN]+1;
                q.push(next);
            }
        }


    }
    bool kExist = false;

    for(int i = 1; i <= N; i++) {
        if(dist[i] == K) {
            cout << i << '\n';
            kExist = true;
        }
    }
    if(!kExist) {
        cout << -1 <<'\n';
    }

}
