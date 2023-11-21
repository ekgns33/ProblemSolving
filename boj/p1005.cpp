//
// Created by 문다훈 on 2023/11/21.
//
#include <bits/stdc++.h>
using namespace std;


void solve() {

    int N, K;
    cin >> N >> K;
    vector<int> constructTime (N + 1, 0);
    vector<int> indegree(N+1, 0);
    vector<bool> visited (N+1, false);
    vector<vector<int>> adj (N+1, vector<int>());
    vector<int> dp (N+1, 0);
    int maxT;
    for(int i = 1 ; i <= N; i++) {
        cin >> constructTime[i];
    }
    for(int i = 0; i < K; i++) {
        int src, dest;
        cin >> src >> dest;
        indegree[dest]++;
        adj[src].push_back(dest);
    }
    int target;
    cin >> target;
    queue<int> q;

    for(int i = 1; i <= N; i++) {
        if(indegree[i] == 0)  {
            q.push(i);
            visited[i] = true;
            dp[i] = constructTime[i];
        }
    }
    int level = 0;
    int levelSize = q.size();
    while(!q.empty()) {

        for(int i = 0; i < levelSize; i++) {
            int curN = q.front();
            q.pop();
            if(curN == target) {
                cout << dp[curN] << '\n';
                return;
            }
            for(auto next : adj[curN]) {
                if(visited[next]) continue;
                indegree[next]--;
                dp[next] = max(dp[next], dp[curN] + constructTime[next]);

                if(indegree[next] == 0) {
                    q.push(next);
                    visited[next] = true;
                }
            }
        }
        level++;
        levelSize = q.size();
    }

    cout << dp[target] << '\n';

}

int main (void) {

    int T;
    cin >> T;
    for(int i = 0; i < T; i++) {
        solve();
    }
    return 0;
}
