//
// Created by 문다훈 on 2024/05/27.
//
#include <bits/stdc++.h>
using namespace std;

int main(void) {

    int N;
    cin >> N;
    vector<pair<int,int>> circles;

    for(int i = 0; i  < N; i++) {
        int d, r;
        cin >> d >> r;
        int left = d -r;
        int right = d + r;
        circles.push_back(make_pair(left, right));
    }

    sort(circles.begin(), circles.end(), [](const pair<int,int>& c1, const pair<int,int>& c2)
    { return c1.first == c2.first ? c1.second < c2.second : c1.first < c2.first; });

    stack<pair<int,int>> stk;

    for(auto circle : circles) {
        while(!stk.empty() && stk.top().second < circle.first) {
            stk.pop();
        }
        if(!stk.empty() && stk.top().second >= circle.first && circle.second > stk.top().second) {
            cout << "NO" << '\n';
            return 0;
        }
        stk.push(circle);
    }
    cout << "YES" << '\n';
    return 0;
}