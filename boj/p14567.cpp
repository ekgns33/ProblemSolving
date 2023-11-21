//
// Created by 문다훈 on 2023/11/21.
//
#include <bits/stdc++.h>
using namespace std;
int main (void) {
    int N, M;
    cin >> N >> M;
    vector<int> indeg (N+1, 0);
    vector<int> ans (N+1, 1);
    vector<vector<int>> adj (N+1, vector<int>());
    for(int i = 0; i < M; i++) {
        int src, dst;
        cin >> src >> dst;
        adj[src].push_back(dst);
        indeg[dst]++;
    }

    queue<int> q;
    for(int i = 1; i <= N; i++) {
        if(indeg[i] == 0) q.push(i);
    }
    int semester = 1;
    int levelSize = q.size();

    while(!q.empty()) {
        for(int i = 0; i < levelSize; i++) {
            int curN = q.front();
            ans[curN] = semester;
            q.pop();
            for(auto& next : adj[curN]) {
                indeg[next]--;
                if(indeg[next] == 0) q.push(next);
            }
        }
        levelSize = q.size();
        semester++;
    }
    for(int i = 1; i <= N; i++) {
        cout << ans[i] << " ";
    }
    return 0;
}
