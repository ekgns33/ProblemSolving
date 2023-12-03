//
// Created by 문다훈 on 2023/12/03.
//
#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> ans;
vector<vector<int>> adj;
vector<vector<int>> reversed;
deque<int> post_num;
bool visited[100001];
int max_post = 1;

void buildDFSTree(int curN) {
    for(auto next : adj[curN]) {
        if(!visited[next]) {
            visited[next] = true;
            buildDFSTree(next);
        }
    }
    post_num.push_back(curN);
}

void buildSCC(int curN) {
     vector<int> elements;
     queue<int> q;
     q.push(curN);
     visited[curN] = true;

    while(!q.empty()) {
        int cur = q.front();
        q.pop();
        elements.push_back(cur);
         for(auto next : reversed[cur]) {
             if(!visited[next]) {
                 visited[next] = true;
                 q.push(next);
             }
         }
     }
    sort(elements.begin(), elements.end());
    elements.push_back(-1);
    ans.push_back(elements);

}

int main (void){

    adj.resize(100001);
    reversed.resize(100001);
    int V, E;
    cin >> V >> E;
    for(int i = 0; i < E; i++) {
        int src, dst;
        cin >> src >> dst;
        adj[src].push_back(dst);
        reversed[dst].push_back(src);
    }

    for(int i = 1; i <= V; i++) {
        if(!visited[i]) {
            buildDFSTree(i);
        }
    }

    for(int i = 0; i <= V; i++) {
        visited[i] = false;
    }

    while(!post_num.empty()) {
        int curPost = post_num.back();
        post_num.pop_back();
        if(visited[curPost]) continue;
        buildSCC(curPost);
    }
    sort(ans.begin(), ans.end());
    cout << ans.size() << '\n';
    for(auto a : ans) {
        for(auto n : a) {
            cout << n  << " ";
        }
        cout << '\n';
    }

    return 0;
}
