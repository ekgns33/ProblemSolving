//
// Created by 문다훈 on 2023/11/17.
//
#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> directions ({{1,0}, {-1, 0}, {0,1}, {0, -1}});
int main (void) {
    int N, M;
    cin >> N >> M;
    vector<vector<int>> grid (N, vector<int> (M, 0));

    for(int i = 0; i < N; i++) {
        string s;
        cin >> s;
        for(int j = 0; j < M; j++) {
           grid[i][j] = s[j]-'0';
        }
    }
    int level = 1;
    queue<pair<int,int>> q;
    vector<vector<bool>> visited (N, vector<bool> (M, false));
    visited[0][0] = true;
    q.push({0,0});
    while(!q.empty()) {

        int levelNode = q.size();

        for(int i = 0; i < levelNode; i++) {
            pair<int,int> curP = q.front();
            q.pop();
            if(curP.first == N-1 && curP.second == M-1) cout <<level;
            for(auto& direction : directions) {
                int nextX = curP.first + direction[0];
                int nextY = curP.second + direction[1];
                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M || visited[nextX][nextY] || grid[nextX][nextY] == 0) continue;
                q.push({nextX, nextY});
                visited[nextX][nextY] = true;
            }
        }
        level++;
    }
    return 0;
}
