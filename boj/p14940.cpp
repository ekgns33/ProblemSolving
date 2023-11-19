//
// Created by 문다훈 on 2023/11/20.
//
#include <bits/stdc++.h>
#include <stdlib.h>
using namespace std;
int grid[1001][1001];
vector<vector<int> > directions ({{-1,0}, {1,0}, {0,1}, {0,-1}});

int main (void) {

    int n , m;
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    pair<int, int> src;
    vector<vector<int>> dist (n, vector<int> (m, -1));
    queue<pair<int,int>> q;

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            cin >> grid[i][j];
            if(grid[i][j] == 2) {
                q.push({i, j});
                dist[i][j] = 0;
            }
        }
    }

    while(!q.empty()) {
        pair<int,int> curP = q.front();
        q.pop();
        for(auto& direction : directions) {
            int nx = curP.first + direction[0];
            int ny = curP.second + direction[1];
            if(nx < 0 || ny < 0 || nx >= n || ny >= m || grid[nx][ny] == 0 || dist[nx][ny] != -1) continue;
            dist[nx][ny] = dist[curP.first][curP.second] + 1;
            q.push(make_pair(nx,ny));
        }
    }

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            cout << ((grid[i][j] == 0) ? 0 : dist[i][j]) << " ";
        }
        cout << '\n';
    }

    return 0;
}