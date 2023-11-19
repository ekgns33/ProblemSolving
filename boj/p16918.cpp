//
// Created by 문다훈 on 2023/11/17.
//
#include <bits/stdc++.h>
using namespace std;

int grid[200][200];

vector<vector<int>> directions ({{-1,0}, {1, 0}, {0, 1}, {0, -1}});

void checkBombs(int curT, int R, int C) {
    for(int i = 0; i < R; i++) {
        for(int j = 0; j < C; j++) {
            if(grid[i][j] == curT) {
                for(auto & direction : directions) {
                    int closeX = i + direction[0];
                    int closeY = j + direction[1];
                    if(closeX < 0 || closeY < 0 || closeX >= R || closeY >= C || grid[closeX][closeY] == curT) continue;
                    grid[closeX][closeY] = -1;
                }
                grid[i][j] = -1;
            }
        }
    }
}
void fillBombs(int curT, int R, int C) {
    for(int i = 0; i < R; i++) {
        for(int j = 0; j < C; j++) {
            if(grid[i][j] == -1) grid[i][j] = curT + 3;
        }
    }
}

void printGrid(int R, int C) {
    for(int i = 0; i < R; i++){
        for(int j= 0; j < C; j++) {
            cout << (grid[i][j] == -1 ? '.' : 'O');
        }
        cout << '\n';
    }
}

int main (void) {

    int R, C, N;
    cin >> R >> C >> N;
    for(int i = 0; i < R; i++)
    {
        string line;
        cin >> line;
        for(int j = 0; j < C; j++) {
            grid[i][j] = (line[j] == '.') ? -1 : 3;
        }
    }

    for(int i = 2; i <= N; i++) {
        checkBombs(i, R, C);
        if(i % 2 ==0) {
            fillBombs(i, R, C);
        }
    }

    printGrid(R, C);

    return 0;
}
