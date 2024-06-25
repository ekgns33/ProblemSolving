//
// Created by 문다훈 on 2024/05/20.
//
#include <bits/stdc++.h>
using namespace std;
int main (void) {
    long N;
    cin >> N;
    long cnt = 1;
    while (1) {
        N -= cnt;
        if(N < 0) {
            cnt--;
            break;
        }
        cnt++;
    }
    cout << cnt;
    return 0;
}

/*
 * max
 * 1 2 3 4 5 6
 *
 * 1 > 1
 * 1 + 2 > 3
 * 1 + 2 + 3 > 6
 * 1 + 2 + 3 + 4 > 10
 * 1 + 2 + 3 + 4 + 5 > 15
 *
 * */