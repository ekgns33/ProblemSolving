//
// Created by 문다훈 on 2023/11/17.
//
#include<bits/stdc++.h>
using namespace std;
int main (void) {
    int N, M, answer = 0;
    cin >> N >> M;
    vector<vector<int>> adj (N+1, vector<int>());
    vector<int> visited(N+1, false);
    for(int i = 0; i < M; i++) {
        int n1, n2;
        cin >> n1 >> n2;
        adj[n1].push_back(n2);
        adj[n2].push_back(n1);
    }

    queue<int> q;
    visited[1] = true;
    q.push(1);
    while(!q.empty()) {
        int curNode = q.front();
        q.pop();
        for(auto connectedNode : adj[curNode]) {
            if(visited[connectedNode]) continue;
            visited[connectedNode] = true;
            answer++;
            q.push(connectedNode);
        }
    }


    cout << answer;
    return 0;
}
