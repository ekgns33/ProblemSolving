//
// Created by 문다훈 on 2024/03/25.
//

#include <bits/stdc++.h>

using namespace std;
typedef vector<vector<int>> vv;

int directions[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

int dfsHelper(vv& board, vv& dp, int x, int y) {
    int ret = 0;
    //end clause
    if(x == dp.size()-1 && y == dp[0].size()-1) {
        return 1;
    }
    if(dp[x][y] != -1) return dp[x][y];

    for(auto direction : directions) {
        int nextX = x + direction[0];
        int nextY = y + direction[1];
        if(nextX >=0 && nextX < board.size() && nextY >=0 && nextY <board[0].size()
        && board[nextX][nextY] < board[x][y]) {
            ret += dfsHelper(board, dp, nextX, nextY);
        }
    }
    dp[x][y] = max(ret, dp[x][y]);
    return dp[x][y];
}


int main (void) {
    int M, N;
    cin >> M >> N;
    vector<vector<int>> dp (M, vector<int> (N, -1));
    vector<vector<int>> board (M, vector<int>(N, 0));
    for(int i = 0; i < M; i++) {
        for(int j = 0; j < N; j++) {
            int c;
            cin >> c;
            board[i][j] = c;
        }
    }
    cout << dfsHelper(board, dp, 0, 0) << '\n';

    return 0;
}
