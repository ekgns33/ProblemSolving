//
// Created by 문다훈 on 2024/03/25.
//
#include <bits/stdc++.h>
using namespace std;

int points[26] = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

int main (void) {
    string n1, n2;
    cin >> n1 >> n2;
    int N = n1.size();
    vector<int> dp(N * 2, 0);

    int p = 0, p1 = 0, p2 = 0;
    int M = 2 * N;
    bool isTrade = true;
    while(p < M) {
        if(isTrade) {
            dp[p++] = points[n1[p1++] - 'A'];
        }else {
            dp[p++] = points[n2[p2++] -  'A'];
        }
        isTrade = !isTrade;
    }
    for(int i = 0; i < M - 2; i++) {
        for(int j = 1; j < M - i; j++) {
            dp[j-1] = (dp[j] + dp[j-1]) % 10;
        }
    }

    cout << dp[0]<<dp[1] << '\n';
    return 0;
}
