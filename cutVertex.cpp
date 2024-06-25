//
// Created by 문다훈 on 2023/11/28.
//
#include <bits/stdc++.h>
using namespace std;
int pre[10001];
int c = 0;
vector<vector<int>> adj;
set<int> cut_vertexes;

int dfs(vector<vector<int>> adj, set<int>& cv, int curV, int parent) {
    pre[curV] = ++c;
    int ret = pre[curV];
    int child_n = 0;
    for(auto& next : adj[curV]) {
        if(!pre[next]) {
            child_n++;
            int low = dfs(adj, cv, next ,curV);
            if(parent != 0 && low >= pre[curV]) {
                cv.insert(curV);
            }
            ret = min(ret, low);
        } else {
            ret = min(ret, pre[next]);
        }
    }
    if(parent == 0 && child_n >1) {
        cv.insert(curV);
    }
    return ret;
}
int main(void) {

    int N, E;
    cin >> N >> E;
    adj.resize(N+1);


    // Tree -> undirected connected acyclic graph
    for(int i = 0; i < E; i++) {
        int src, dst;
        cin >> src >> dst;
        adj[src].push_back(dst);
        adj[dst].push_back(src);
    }

    // find cut vertex.

    // 1. find root
    for(int i = 1; i <= N; i++) {
        if(!pre[i]) {
            dfs(adj, cut_vertexes, i, 0);
        }
    }
    cout << cut_vertexes.size() << '\n';
    for(auto n : cut_vertexes) {
        cout << n << " ";
    }

    return 0;
}