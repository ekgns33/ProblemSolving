//
// Created by 문다훈 on 2023/11/17.
//
#include <bits/stdc++.h>
using namespace std;
int grid[25][25];
int cnt = 2;

vector<vector<int>> directions({{-1,0}, {1,0}, {0,1}, {0,-1}});

int dfs(int curX, int curY, int N) {
    int ret = 0;
    grid[curX][curY] = cnt;
    for(auto& direction : directions) {
        int nextX = curX + direction[0];
        int nextY = curY + direction[1];

        if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || grid[nextX][nextY] != 1) continue;
        ret += dfs(nextX, nextY, N) + 1;
    }
    return ret;
}

int main (void) {

    int N;
    cin >> N;
    for(int i = 0; i < N; i++) {
        string line;
        cin >> line;
        for(int j = 0; j < N; j++) {
            grid[i][j] = line[j] - '0';
        }
    }
    vector<int> answer;

    for(int i = 0; i < N; i ++) {
        for(int j = 0; j < N; j++) {
            if(grid[i][j] == 1) {
                //dfs
                answer.push_back(dfs(i, j, N) + 1);
                cnt++;
            }
        }
    }
    sort(answer.begin(), answer.end());
    cout << answer.size() << '\n';
    for(auto s : answer) {
        cout << s << '\n';
    }
    return 0;
}
