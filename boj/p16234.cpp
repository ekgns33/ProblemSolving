//
// Created by 문다훈 on 2023/11/20.
//

#include <bits/stdc++.h>
using namespace std;
typedef pair<int,int> pp;

vector<vector<int>> population (51, vector<int> (51, 0));
vector<vector<int>> directions ({{-1,0}, {1,0}, {0,1}, {0, -1}});


bool bfs(int startX, int startY, vector<vector<bool>>& visited, vector<vector<int>>& grid, int N, int L, int R) {

    queue<pp> result;
    queue<pp> q;
    bool ret = false;
    int totalSum = 0;
    visited[startX][startY] = true;
    q.push({startX, startY});

    while(!q.empty()) {
        pp curP = q.front();
        q.pop();
        result.push({curP.first, curP.second});
        totalSum += population[curP.first][curP.second];

        for (auto &direction : directions) {
            int nextX = curP.first + direction[0];
            int nextY = curP.second + direction[1];

            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY]) continue;
            int populationDiff = abs(population[curP.first][curP.second] - population[nextX][nextY]);
            if (populationDiff >= L && populationDiff <= R) {
                visited[nextX][nextY] = true;
                q.push({nextX, nextY});
            }
        }
    }

    int nextPop = totalSum / result.size();
    ret = result.size() > 1;
    while(!result.empty()) {
        pp curP = result.front();
        result.pop();
        grid[curP.first][curP.second] = nextPop;
    }
    return ret;
}


int main (void) {

    int N, L, R;

    cin >> N >> L >> R;

    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            cin >> population[i][j];
        }
    }

    bool isModified = true;

    int days = 0;
    while(isModified) {
        vector<vector<int>> nextPop(N, vector<int> (N, 0));
        vector<vector<bool>> visited (N, vector<bool> (N, false));
        bool tick = false;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                //do bfs
                if(!visited[i][j]) {
                    if(bfs(i, j, visited, nextPop, N, L, R)) tick = true;
                }
            }
        }
        population.swap(nextPop);
        isModified = tick;
        days++;
    }

    cout << days-1 << '\n';
    return 0;
}