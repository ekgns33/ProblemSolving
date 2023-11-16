//
// Created by 문다훈 on 2023/11/17.
//
#include <bits/stdc++.h>
using namespace std;
bool visited[10001];
vector<vector<int>> adj;
int cnt = 0;
void dfs(int cur) {
    visited[cur] =true;
    cnt++;
    for(auto next : adj[cur]) {
        if(!visited[next]) dfs(next);
    }
}
int main (void){
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int N, M;
    cin >> N >> M;
    adj = vector<vector<int>> (N + 1, vector<int>());
    vector<int> res (N+1, 0);
    for(int i = 0; i < M; i++) {
        int n1, n2;
        cin >> n1 >> n2;
        adj[n2].push_back(n1);
    }
    int maxP = 0;
    for(int i = 1; i <= N; i++) {
        memset(visited, false, N+1);
        cnt = 0;
        dfs(i);
        int tmp = cnt;
        res[i] = tmp;
        maxP = max(res[i], maxP);
    }
    for(int i = 1; i <= N; i++) {
        if( res[i]== maxP) cout << i << " ";
    }
    return 0;
}