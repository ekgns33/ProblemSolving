//
// Created by 문다훈 on 2024/05/29.
//
#include <bits/stdc++.h>
using namespace std;

int main(void) {
    unordered_map<int, int> map;
    vector<int> v;
    int n, target, next;
    int ans = 0;
    cin >> n;

    if(n == 1) {
        cout << "0";
        return 0;
    }

    for(int i = 0; i < n; i++) {
        cin >> next;
        v.push_back(next);
    }
    cin >> target;
    for(const auto el : v) {
        int key = target - el;
        if(map.find(key) != map.end()) {
            ans += map[key];
        }
        map[el]++;
    }
    cout << ans << '\n';
    return 0;
}
