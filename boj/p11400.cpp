//
// Created by 문다훈 on 2023/12/03.
//

#include <bits/stdc++.h>

using namespace std;

vector<int> adj[100001];
int pre_num = 1;
int pre[100001];
vector<pair<int, int>> edge;

int dfs(int curN, int prevN) {

    pre[curN] = ++pre_num;

    int ret = pre[curN];

    for (auto next : adj[curN]) {
        if (next == prevN) {
            continue;
        }
        if(pre[next]!=0) {
            ret = min(ret, pre[next]);
            continue;
        }
        int l = dfs(next, curN);
        if (l > pre[curN]) {
            edge.push_back(make_pair(min(curN,next), max(curN,next)));
        }
        ret = min(ret, l);
    }
    return ret;
}


int main(void) {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int V, E;
    cin >> V >> E;
    for (int i = 0; i < E; i++) {
        int src, dst;
        cin >> src >> dst;
        adj[src].push_back(dst);
        adj[dst].push_back(src);
    }

    for (int i = 1; i <= V; i++) {
        if (pre[i] == 0) {
            dfs(i, 0);
        }
    }
    sort(edge.begin(), edge.end());

    cout << edge.size() << '\n';
    for (auto e : edge) {
        cout << e.first << " " << e.second << '\n';
    }
    return 0;

}