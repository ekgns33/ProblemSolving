//
// Created by 문다훈 on 2024/03/28.
//
#include <bits/stdc++.h>
using namespace std;
int board[64][64] = {};
bool rangeCheck(int startX, int startY, int endX, int endY) {
    int prev = board[startX][startY];
    for(int i = startX; i <= endX; i++) {
        for(int j = startY; j<= endY; j++) {
            if(board[i][j] != prev) {
                return false;
            }
        }
    }
    return true;
}
string parseImgMap(int lux, int luy, int rdx, int rdy) {
    //end clause
    if(rangeCheck(lux, luy, rdx, rdy)) {
        return to_string(board[lux][luy]);
    }

    int midX = (rdx + lux) / 2;
    int midY = (rdy + luy) / 2;
    string upperLeft = parseImgMap(lux, luy, midX, midY);
    string bottomLeft = parseImgMap(midX+1, luy, rdx, midY);
    string upperRight = parseImgMap(lux, midY+1, midX, rdy);
    string bottomRight = parseImgMap(midX+1, midY+1, rdx, rdy);
    return "(" + upperLeft + upperRight + bottomLeft + bottomRight + ")";
}


int main (void)
{
    int N;
    cin >> N;
    for(int i = 0; i < N; i++) {
        string nextLine;
        cin >> nextLine;
        for(int j = 0; j < N; j++) {
            board[i][j] = nextLine[j] - '0';

        }
    }
    cout << parseImgMap(0, 0, N-1, N-1) << endl;
}
