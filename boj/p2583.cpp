//
// Created by 문다훈 on 2024/03/27.
//
#include <bits/stdc++.h>
using namespace std;
typedef vector<vector<int>> vv;
vector<int> localSpace;
int directions[4][2] = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
queue<pair<int,int>> q;
void bfs(vv& board, int x, int y) {
    q.push(make_pair(x, y));
    int spaceLocal = 0;
    while(!q.empty()) {
        pair<int,int> curP = q.front();
        q.pop();
        spaceLocal++;
        int nextX, nextY;
        for(auto direction : directions) {
            nextX = curP.first + direction[0];
            nextY = curP.second + direction[1];
            if(nextX >=0 && nextX < board.size() && nextY >= 0 && nextY <board[0].size() && board[nextX][nextY] != 1) {
                board[nextX][nextY] = 1;

                q.push(make_pair(nextX, nextY));
            }
        }
    }
    localSpace.push_back(spaceLocal);
}

int main (void) {
    int m, n, k;
    cin >> n >> m >> k;
    vv board (m, vector<int> (n, 0));
    for(int i = 0; i < k; i++) {
        int sx, sy, dx, dy;
        cin >> sx >> sy >> dx >> dy;
        for(int x = sx; x < dx; x++) {
            for(int y = sy; y < dy; y++) {
                board[x][y] = 1;
            }
        }
    }
    int ans = 0;
    for(int i = 0; i < m; i++) {
        for(int j = 0; j < n; j++) {
            if(board[i][j] == 0) {
                board[i][j] = 1;
                ans++;
                bfs(board, i, j);
            }
        }
    }
    cout << ans  << '\n';
    sort(localSpace.begin(), localSpace.end());
    for(auto size : localSpace) {
        cout << size << " ";
    }

    return 0;
}
