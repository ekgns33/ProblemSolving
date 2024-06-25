//
// Created by 문다훈 on 2024/05/23.
//

#include <bits/stdc++.h>
using namespace std;

void solution() {
    string input;
    cin >> input;

    stack<char> stk;
    for(char curC : input) {
        if(curC == '(') {
            stk.push(curC);
            continue;
        }
        if(stk.empty() && curC == ')') {
            cout << "NO" << '\n';
            return;
        }
        if(curC == ')') {
            stk.pop();
        }
    }
    if(stk.empty()) {
        cout << "YES" << '\n';
        return;
    }
    cout << "NO" << '\n';
    return;
}

int main(void) {
    int N;
    cin >> N;

    for(int i = 0; i < N; i++) {
        solution();
    }
}