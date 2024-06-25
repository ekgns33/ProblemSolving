//
// Created by 문다훈 on 2024/03/25.
//

/*
 *
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 *
 * */


#include <bits/stdc++.h>
using namespace std;

int main (void) {

    int N, K;
    cin >> N >> K;
    vector<int> dp(N, 0);
    dp[0] = 1;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j <= i; j++) {
            int prev = j-1 >= 0 ? dp[j-1] : 0;
            dp[j] = prev + dp[j];
            cout << dp[j];
        }
    }
    cout << dp[K-1];
     return 0;
}