//
// Created by 문다훈 on 2023/11/23.
//
/*
 *
 * main goal : C명 이상의 고객을 위해 투자하는 돈의 최솟값.
 *
 * c명일때 돈의 최솟값만 구하면됨.
 *
 * i명일때 돈의 최솟값은?
 *  0 ~ i-1에서 i에 도달할 수 있는 방법들 중 가장 작은 투자값.
 *
 *
 * */
#include <bits/stdc++.h>
using namespace std;
int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int C, N;
    cin >> C >> N;
    vector<vector<int>> v(N, vector<int>(2, 0));
    for(int i = 0; i < N; i++) {
        cin >> v[i][0] >> v[i][1];
    }
    vector<int> dp (C+1, INT_MAX);

    for(int i = 1; i <= C; i++) {
        for(auto pair : v) {
            if(i - pair[1] <= 0) {
                dp[i] = min(dp[i], pair[0]);
            } else {
                dp[i] = min(dp[i], dp[i-pair[1]] + pair[0]);
            }
        }
    }
    cout << dp[C];
    return 0;


}