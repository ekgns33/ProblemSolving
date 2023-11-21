//
// Created by 문다훈 on 2023/11/15.
//
#include <bits/stdc++.h>
using namespace std;

int directions[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

int dfs(vector<vector<int>>& board, vector<vector<int>>& dp, int curX, int curY) {

    if(dp[curX][curY] != -1) return dp[curX][curY];
    if(curX == board.size()-1 && curY == board[0].size()-1) {
        return 1;
    }
    int sum = 0;
    for(auto& direction : directions) {
        int nextX = curX + direction[0];
        int nextY = curY + direction[1];
        if (nextX < 0 || nextY < 0 || nextX >= board.size() || nextY >= board[0].size() ||
            board[nextX][nextY] >= board[curX][curY]) continue;

        sum += dfs(board, dp, nextX, nextY);
    }
    dp[curX][curY] = sum;
    return dp[curX][curY];
}

int main (void) {

    int M, N;

    cin >> M >> N;
    vector<vector<int>> board (M, vector<int>(N, 0));
    vector<vector<int>> dp (M, vector<int> (N, -1));

    for(int i = 0; i < M; i++) {
        for(int j = 0; j < N; j++) {
            cin >> board[i][j];
        }
    }
    cout << dfs(board, dp, 0, 0);
}
