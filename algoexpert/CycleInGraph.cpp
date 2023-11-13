//
// Created by 문다훈 on 2023/11/13.
//

// naive time O(n^2)  n is the number of nodes
//       space O(n)
#include <vector>
using namespace std;
typedef vector<vector<int>> vvi;

bool dfsHelper(vvi& edges, int curNode, int visited) {

    if((visited & (1<<curNode))) return false;

    bool ret = true;
    int next = visited | (1 << curNode);

    for(int edge : edges[curNode]) {
        ret &= dfsHelper(edges, edge, next);
    }

    return ret;
}

bool cycleInGraph1(vector<vector<int>> edges) {
    // Write your code here.
    int n = edges.size();
    vector<bool> visited (n, false);

    for(int i = 0; i < edges.size(); i++) {
        if(!dfsHelper(edges, i, 0)) return true;
    }

    return false;
}

// better solution coloring nodes
// time : O(V + E)
// space : O(V)
// 색칠하는 아이디어. 그래프 문제에서 자주 사용됨.
bool containsCycle(vector<vector<int>>& edges, vector<int>& colors, int curNode) {

    colors[curNode] = 2;

    for(auto next : edges[curNode]) {

        if(colors[next] == 1) continue;

        if(colors[next] == 2) return true;

        bool ret = containsCycle(edges, colors, next);
        if(ret) return true;
    }

    colors[curNode] = 1;
    return false;
}

bool cycleInGraph(vector<vector<int>> edges) {
    // Write your code here.
    int n = edges.size();
    vector<int> nodeColor (n, 0);

    for(int i = 0; i < edges.size(); i++) {
        if(containsCycle(edges, nodeColor, i)) return true;
    }



    return false;
}