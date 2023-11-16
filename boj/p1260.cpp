//
// Created by 문다훈 on 2023/11/17.
//
#include<bits/stdc++.h>
using namespace std;
void dfs(vector<vector<int>>& adj, vector<bool>& v, int cur) {

    if(v[cur]) return;
    v[cur] = true;
    cout << cur << " ";
    for(auto n : adj[cur]) {
        dfs(adj, v, n);
    }
}
int main (void) {

    int N, M, V;
    cin >> N >> M >> V;
    vector<vector<int>> adj(N+1, vector<int>());
    for(int i = 0; i < M; i++) {
        int n1, n2;
        cin >> n1 >> n2;
        adj[n1].push_back(n2);
        adj[n2].push_back(n1);
    }
    for(auto& l : adj) {
        sort(l.begin(), l.end());
    }
    // dfs
    vector<bool> visited (N+1, false);
    deque<int> next;
    dfs(adj, visited, V);

    cout << endl;
    visited = vector<bool> (N+1, false);
    next.push_back(V);
    visited[V] = true;
    while(!next.empty()) {
        int curNode = next[0];
        next.pop_front();
        cout << curNode << " ";
        for(auto nextNode : adj[curNode]) {
            if(!visited[nextNode]) {
                visited[nextNode] = true;
                next.push_back(nextNode);
            }
        }
    }


    return 0;
}
