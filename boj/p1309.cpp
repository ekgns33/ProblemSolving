//
// Created by 문다훈 on 2023/11/23.
//
#include<bits/stdc++.h>
using namespace std;

int main(void) {

    int N;
    cin >> N;
    vector<int> dp (N+1, 0);
    vector<vector<int>> dp2 (N+1, vector<int> (3, 0));
    dp[0] = 1;
    dp[1] = 3;
    dp2[1][0] = 1;
    dp2[1][1] = 1;
    dp2[1][2] = 1;
    for(int i = 2; i <= N; i++) {
        dp[i] = (dp[i-1] * 2 + dp[i-2]) % 9901;
        // 이게 나오는 이
        dp2[i][0] = (dp2[i-1][0] + dp2[i-1][1] + dp2[i-1][2]) % 9901;
        dp2[i][1] = (dp2[i-1][0] + dp2[i-1][2]) % 9901;
        dp2[i][2] = (dp2[i-1][0] + dp2[i-1][1]) % 9901;
    }


    cout << dp[N] << " : "  <<(dp2[N][0]+dp2[N][1]+dp2[N][2]);
    return 0;
}
/*
 * left에 사자를 두는경우, right에 사자를 두는경우, 안두는 경우
 * left에 사자를 두는경우
 * -> i-1까지 사자를 뒀을때 left에 사자가 없는 경우
 *
 * right에 사자를 두는경우
* -> i-1까지 사자를 뒀을때 right에 사자가 없는경우
 * 안두는 경우
 * -> all
 * */