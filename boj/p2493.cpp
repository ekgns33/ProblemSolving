//
// Created by 문다훈 on 2024/05/25.
//

#include <bits/stdc++.h>
using namespace std;
stack<pair<int,int>> stk;

int main (void) {
    cin.tie(NULL);
    int N, tower;
    cin >> N;
    stk.push({INT_MAX, 0});
    for(int i = 0; i < N; i++) {
        cin >> tower;
        while(stk.top().first < tower) {
            stk.pop();
        }

        cout << stk.top().second << " ";
        stk.push(make_pair(tower, i+1));
    }
    return 0;
}
/*
 * [9 7]
 * 6 9 5 7 4
 * 0 0 2 2 4
 *
 *
 * */