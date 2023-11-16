//
// Created by 문다훈 on 2023/11/17.
//
#include<bits/stdc++.h>
using namespace std;

void dfs(vector<vector<int>>& adj, vector<bool>& visited, vector<int>& parent, int cur, int prev) {

    visited[cur] = true;
    parent[cur] = prev;
    for(auto next : adj[cur]) {
        if(!visited[next]) {
            dfs(adj, visited, parent, next, cur);
        }
    }
}

int main (void) {
    int N;
    cin >> N;

    vector<vector<int>> adj(N+1, vector<int>());
    for(int i = 0; i < N-1; i++) {
        int n1, n2;
        cin >> n1 >> n2;
        adj[n1].push_back(n2);
        adj[n2].push_back(n1);
    }

    vector<bool> visited (N+1, false);
    vector<int> parent (N+1, -1);

    dfs(adj, visited, parent, 1, -1);
    for(int i = 2; i <= N; i++){
        cout << parent[i] << '\n';
    }


    return 0;
}
