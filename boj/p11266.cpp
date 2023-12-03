//
// Created by 문다훈 on 2023/12/03.
//
#include <bits/stdc++.h>
using namespace std;
vector<vector<int>> adj;
int preNum[10001];
set<int> cv;

int prev_pre = 1;

int dfs(int curN, int prevN) {

    int cur_pre = preNum[curN] = ++prev_pre;

    int ret = cur_pre;
    int child_n = 0;
    for(auto child : adj[curN]) {
        if(preNum[child] == 0) {
            child_n++;
            int low  = dfs(child, curN);
            if(prevN != -1 && low >= preNum[curN]) {
                cv.insert(curN);
            }
            ret = min(ret, low);
        } else {
            ret = min(ret, preNum[child]);
        }
    }

    if(prevN == -1 && child_n > 1) { // root
        cv.insert(curN);
    }
    return ret;
}


int main (void) {

    int V, E;
    cin >> V >> E;
    adj.resize(10001);
    for(int i = 0; i < E; i++) {
        int src, dst;
        cin >> src >> dst;
        adj[src].push_back(dst);
        adj[dst].push_back(src);
    }

    for(int i = 1; i <= V;  i++) {
        if(preNum[i] == 0) {
            dfs(i, -1);
        }
    }

    cout << cv.size() << '\n';
    for(auto& v : cv) {
        cout << v << " ";
    }
    return 0;
}

