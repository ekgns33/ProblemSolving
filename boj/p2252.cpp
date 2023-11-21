//
// Created by 문다훈 on 2023/11/21.
//
#include <bits/stdc++.h>
using namespace std;
int main (void) {

    int N, M;
    cin >> N >> M;

    vector<int> indeg (N+1);
    vector<vector<int>> adj (N+1, vector<int>());
    vector<bool> visited (N+1, false);
    for(int i = 0; i < M; i++) {
        int src, dest;
        cin >> src >> dest;
        adj[src].push_back(dest);
        indeg[dest]++;
    }
    queue<int> q;

    for(int i = 1; i <= N; i++) {
        if(indeg[i] == 0) {
            q.push(i);
            visited[i] = true;
        }
    }

    while(!q.empty()) {
        int curN = q.front();
        q.pop();
        cout << curN << " ";
        for(auto next : adj[curN]) {
            if(visited[next]) continue;
            indeg[next]--;
            if(indeg[next] == 0) {
                visited[next] = true;
                q.push(next);
            }
        }
    }
    return 0;

}
