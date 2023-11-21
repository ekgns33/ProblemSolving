//
// Created by 문다훈 on 2023/11/21.
//
#include<bits/stdc++.h>
using namespace std;
typedef pair<int,int> pp;

int farFromRoot;
int pathCost = 0;

int main (void) {

    int N;
    cin >> N;
    vector<vector<pp>> adj (N+1, vector<pp>());
    for(int i = 0; i < N - 1; i++) {
        int parent, child, weight;
        cin >> parent >> child >> weight;
        adj[parent].push_back({child, weight});
        adj[child].push_back({parent, weight});
    }

    queue<pp> q;
    vector<bool>visited (N+1, false);
    q.push({1,0});
    visited[1] = true;
    while(!q.empty()) {

        pp curN = q.front();
        q.pop();
        if(curN.second > pathCost) {
            farFromRoot = curN.first;
            pathCost = curN.second;
        }
        for(auto& nextN : adj[curN.first]) {
            if(visited[nextN.first]) continue;
            q.push({nextN.first, nextN.second + curN.second});
            visited[nextN.first] = true;
        }
    }

    visited = vector<bool> (N+1, false);
    pathCost = 0;
    q.push({farFromRoot,0});
    visited[farFromRoot] = true;
    while(!q.empty()) {
        pp curN = q.front();
        q.pop();
        if(curN.second > pathCost) {
            farFromRoot = curN.first;
            pathCost = curN.second;
        }
        for(auto& nextN : adj[curN.first]) {
            if(visited[nextN.first]) continue;
            q.push({nextN.first, nextN.second + curN.second});
            visited[nextN.first] = true;
        }
    }
    cout << pathCost<<'\n';



    return 0;
}