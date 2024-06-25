//
// Created by 문다훈 on 2024/05/20.
//
#include <bits/stdc++.h>
using namespace std;

void solution(int L, int K, int M) {
    int low = 0;
    int high = L;
    int ret = 0;
    while(low <= high) {
        int mid = high - (high - low) / 2;
        int pieces = 0;
        if(L % mid == 0) pieces = L /mid;
        if(pieces < M) {
            high = mid - 1;
        } else {
            ret = max(ret, mid);
            low = mid + 1;
        }
    }
    cout << (ret > 0 ? ret : -1) << '\n';
}

int main (void) {
    int N, K, M;
    cin >> N >> K >> M;
    int gimbapLength = 0;

    for(int i = 0; i < N; i++) {
        int curGimbapLength;
        cin >> curGimbapLength;
        if(curGimbapLength <= K) continue;
        gimbapLength += (gimbapLength - 2 *K);
    }
    solution(gimbapLength, K, M);
}
