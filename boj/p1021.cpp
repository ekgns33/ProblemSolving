//
// Created by 문다훈 on 2024/05/22.
//
/*
 *
 * 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10
 * ^         ^              ^
 *
 * */
#include <bits/stdc++.h>
using namespace std;
int main(void) {
    int N, M;
    cin >> N >> M;
    deque<int> dq;

    for(int i = 1; i <= N; i++) {
        dq.push_back(i);
    }
    int target;
    int popCount = 0;
    for(int i = 0; i < M; i++) {
        cin >> target;
        int cnt;
        for(cnt = 0; cnt < dq.size(); cnt++) {
            if(dq[cnt] == target) break;
        }

        if(cnt <= dq.size() / 2) {
            while(!dq.empty() && dq.front() != target) {
                int el = dq.front();
                dq.pop_front();
                dq.push_back(el);
                popCount++;
            }
        } else {
            while(!dq.empty() && dq.front() != target) {
                int el = dq.back();
                dq.pop_back();
                dq.push_front(el);
                popCount++;
            }
        }
        dq.pop_front();
    }
    cout << popCount << '\n';
    return 0;
}