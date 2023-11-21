//
// Created by 문다훈 on 2023/11/11.
//
#include <bits/stdc++.h>
using namespace std;

typedef vector<vector<int>> vii;

int directions[8][2] = {{-1,0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};

bool dfs(vii& board, int curX, int curY, int d1, int d2, int color, int l) {
    if(curX <= 0 || curY <= 0 || curX > 19 || curY > 19) {
        if(l == 6) return true;
        return false;
    }
    if(l == 7) {
        if(board[curX][curY] == 0) return true;
        return false;
    }
    if(board[curX][curY] != color) return false;

    int nextX = curX + d1, nextY = curY + d2;
    return dfs(board, nextX, nextY, d1, d2, color, l + 1);
}

int main(void) {
    int N = 21;
    vector<int> cnt (3, 0);
    vii board (21, vector<int> (N, 0));

    for(int i = 1; i < 20; i++) {
        for(int j = 1; j < 20; j++) {
            cin >> board[i][j];
            cnt[board[i][j]]++;
        }
    }

    bool p1_win = false;
    bool p2_win = false;
    pair<int,int> p1;
    pair<int,int> p2;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            if(p1_win) break;
            if(board[j][i] == 0) {
                for(auto dir: directions) {
                    if(p1_win) break;
                    int nextX = j + dir[0];
                    int nextY = i + dir[1];
                    int l = (i == 0 || j == 0 || i == 20 || j == 20) ? 1 : 2;
                    p1_win |= dfs(board, nextX, nextY, dir[0], dir[1], 1, l);
                    p1 = {j+dir[0], i+dir[1]};
                }
            }

        }
    }

    if(p1_win && p2_win) {
        if(cnt[1] > cnt[2]) {
            cout << 1 << '\n' << p1.first << " " << p1.second << endl;
        }
        if(cnt[1] < cnt[2]) {
            cout << 2 << '\n' << p2.first << " " << p2.second << endl;
        }
        return 0;
    }
    if(p1_win) {
        cout << 1 << '\n' << p1.first << " " << p1.second << endl;
        return 0;
    }
    if(p2_win) {
        cout << 2 << '\n' << p2.first << " " << p2.second << endl;
        return 0;
    }
    return 0;
}
