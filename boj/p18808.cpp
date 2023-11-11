//
// Created by 문다훈 on 2023/11/09.
//
#include <bits/stdc++.h>
using namespace std;
typedef vector<vector<int>> vii;


bool stickTrial(vii& board, vii& sticker, int N, int M) {

    int stickerHeight = sticker.size();
    int stickerWidth = sticker[0].size();

    // check enough space
    if(stickerHeight > N || stickerWidth > M) return false;

    //starts from 0 0 to end of range
    for(int i = 0; i <= N - stickerHeight; i++) {
        for(int j = 0; j <= M - stickerWidth; j++) {
            // check if there is space to stick
            bool is_succeed = true;
            for(int si = 0; si < stickerHeight; si++) {
                for(int sj = 0; sj < stickerWidth; sj++) {
                    if(board[i + si][j + sj] == 1 && sticker[si][sj] == 1)  {
                        is_succeed = false;
                        break;
                    }
                }
            }
            if(is_succeed) {
                // stick it to the board
                for(int si = 0; si < stickerHeight; si++) {
                    for(int sj = 0; sj < stickerWidth; sj++) {
                        board[i+si][j+sj] = max(sticker[si][sj],  board[i+si][j+sj]);
                    }
                }
                return true;
            }
        }
    }
    return false;
}

void rotateSticker(vector<vector<int>>& sticker) {
    int m = sticker.size(),  n = sticker[0].size();
    vector<vector<int>> ret(n, vector<int> (m, 0));

    for(int i = 0 ; i < m; i ++) {
        for(int j = 0; j < n; j++) {
            ret[j][m - i - 1] = sticker[i][j];
        }
    }
    sticker.swap(ret);
}



int main (void) {
    int N, M, K;
    cin >> N >> M >>K;
    vector<vector<int>> board (N, vector<int> (M, 0));
    vector<vector<vector<int>>> stickers;

    //read each stickers
    for(int t = 0; t <K; t++) {
        int m, n;
        cin >> m >> n;
        vector<vector<int>> sticker (m , vector<int> (n, 0));
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int cur;
                cin >> cur;
                sticker[i][j] = cur;
            }
        }
        stickers.push_back(sticker);
    }

    for(auto& sticker : stickers) {
        // match to the board.
        for(int t = 0; t < 4; t++) {
            if(stickTrial(board, sticker, N, M)) break;
            rotateSticker(sticker);
        }

    }
    int cnt = 0;
    for(int a = 0; a < N; a++ ){
        for(int b = 0; b < M; b++) {
            cnt += board[a][b];
        }
    }
    cout << cnt << endl;
    return 0;
}