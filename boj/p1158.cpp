//
// Created by 문다훈 on 2024/05/23.
//
#include <bits/stdc++.h>
using namespace std;
int main (void) {
    int N, K;
    cin >> N >> K;

    deque<int> dq;
    for(int i = 1; i <= N; i++) {
        dq.push_back(i);
    }
    cout << "<";
    while(dq.size() > 1) {
        for(int i = 0; i < K-1; i++) {
            int front = dq.front();
            dq.pop_front();
            dq.push_back(front);
        }
        cout << dq.front() << ", ";
        dq.pop_front();
    }
    cout << dq.front() << ">";
}
