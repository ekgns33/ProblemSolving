//
// Created by 문다훈 on 2023/11/20.
//

#include <bits/stdc++.h>
using namespace std;
int grid[1001][1001];
queue<pair<int,int>> q;
vector<vector<int>> directions ({{1,0}, {-1,0},{0,1},{0,-1}});
int main (void) {
    int M, N;
    int tomatoN = 0;
    cin >> N>> M;

    for(int i = 0; i < M; i++) {
        for(int j = 0; j < N; j++) {
            cin >> grid[i][j];
            if(grid[i][j] == 0) {
                tomatoN++;
            } else if (grid[i][j] == 1) {
                q.push({i,j});
            }
        }
    }
    int level = 0;
    int levelSize = q.size();

    while(!q.empty()) {
        for(int i = 0; i < levelSize; i++) {
            pair<int, int> curP = q.front();
            q.pop();
            for(auto& direction : directions) {
                int nx = curP.first + direction[0];
                int ny = curP.second + direction[1];

                if(nx < 0 || ny < 0 || nx >= M || ny >= N || grid[nx][ny] != 0) continue;
                grid[nx][ny] = 1;
                tomatoN--;
                q.push({nx,ny});
            }
        }
        levelSize = q.size();
        if(levelSize > 0) {
            level++;
        }
    }
    cout << (tomatoN == 0 ? level : -1);
    return 0;

}