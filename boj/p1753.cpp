//
// Created by 문다훈 on 2023/11/30.
//
#include <bits/stdc++.h>
using namespace std;

int main (void) {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int V, E, S;
    cin >> V >> E >> S;
    vector<int> dist (V+1, INT_MAX);
    vector<vector<pair<int,int>>> adj (V+1, vector<pair<int,int>>());
    for(int i = 0; i < E; i++) {
        int src, dst, w;
        cin >> src >>dst >>w;
        adj[src].push_back({dst,w});
    }

    dist[S] = 0;
    priority_queue<pair<int,int>> pq;
    pq.push({0, S});
    while(!pq.empty()) {

        int curCost = -pq.top().first;
        int curN = pq.top().second;
        pq.pop();
        if(curCost > dist[curN]) continue;
        for(auto& next : adj[curN]) {
            int dist_next = next.second + curCost;
            if(dist_next < dist[next.first]) {
                dist[next.first] = dist_next;
                pq.push({-dist_next, next.first});
            }
        }
    }
    for(int i = 1; i <= V; i++) {
        if(dist[i] == INT_MAX)
        {
            cout << "INF"<<endl;
        } else {
            cout << dist[i] << '\n';
        }
    }
    return 0;
}
