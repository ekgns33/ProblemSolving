//
// Created by 문다훈 on 2023/11/23.
//
/*
 * N을 1로 만들기.
 *
 * K를 만드는 방법 from 1
 * -> k가 전3의배수라면 k/3에서 곱하기로 오기
 * -> k가 2의배수라면 k/2에서 곱하기로 오기
 * -> k-1에서 오기
 * */
#include <bits/stdc++.h>
using namespace std;
int main (void) {
    int N;
    cin >> N;

    vector<int> dp (N + 1, INT_MAX);

    dp[0] = 0;
    dp[1] = 0;
    for(int i= 2; i <= N; i++) {
        if(i % 3 == 0) {
            dp[i] = min(dp[i], dp[i / 3]+1);
        }
        if(i % 2 == 0) dp[i] = min(dp[i], dp[i/2]+1);
        dp[i] = min(dp[i], dp[i-1] + 1);

    }

    cout << dp[N] << '\n';

    return 0;
}
