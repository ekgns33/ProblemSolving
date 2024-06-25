//
// Created by 문다훈 on 2024/04/02.
//

#include<bits/stdc++.h>

using namespace std;
vector<pair<int, int>> sources;
queue<pair<int,int>> q;
int directions[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

int main(void) {
    int M, N;
    cin >> M >> N;
    int maxLen = 0;
    vector<vector<int>> board (M, vector<int>(N, 0));
    for(int i = 0; i < M; i++) {
        for(int j = 0; j < N; j++) {
            char t;
            cin >> t;
            board[i][j] = (t == 'W') ? 0 : 1;
        }
    }

    for(int i = 0; i < M; i++) {
        for(int j = 0; j < N; j++) {
            if(board[i][j] != 0) {
                int flag = board[i][j];
                q.push({i,j});
                board[i][j] = -flag;
                int level = 0;
                int levelSize = q.size();
                while(!q.empty()) {
                    for(int i = 0; i < levelSize; i++) {
                        pair<int,int> nextNode = q.front();
                        q.pop();
                        for(auto direction : directions){
                            int nextX = nextNode.first + direction[0];
                            int nextY = nextNode.second + direction[1];
                            if(nextX >= 0 && nextX < board.size() && nextY >=0 && nextY < board[0].size() && board[nextX][nextY] == flag) {
                                q.push({nextX, nextY});
                                board[nextX][nextY] = -flag;
                            }
                        }
                    }
                    levelSize = q.size();
                    level++;
                }
                maxLen = max(maxLen, level-1);
            }
        }
    }

    cout << maxLen << endl;
    return 0;
}