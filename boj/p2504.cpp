//
// Created by 문다훈 on 2024/05/27.
//

#include <bits/stdc++.h>
using namespace std;

int main(void) {
    string input;
    cin >> input;

    int curP;
    vector<pair<char, int>> v;
    v.push_back({'-', 0});
    for(auto curC : input) {

        if(curC == '[' || curC == '(') {
            v.push_back({curC, 0});
        }
        if(curC == ')') {
            if(v.back().first != '(') {
                cout << "0";
                return 0;
            }
            int tmpSum = v.back().second == 0 ? 2 : v.back().second * 2;
            v.pop_back();
            v.back().second += tmpSum;
        }
        if(curC == ']') {
            if(v.back().first != '[') {
                cout << "0";
                return 0;
            }
            int tmpSum = v.back().second == 0 ? 3 : v.back().second * 3;
            v.pop_back();
            v.back().second += tmpSum;
        }
    }
    if(v.size() > 1) {
        cout << "0";
        return 0;
    }
    cout << v.back().second;
    return 0;
}