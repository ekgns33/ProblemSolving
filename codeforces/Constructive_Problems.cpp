//
// Created by 문다훈 on 2023/12/17.
//
#include <bits/stdc++.h>
using namespace std;
int main(void) {
    int t;
    cin >> t;
    for(int i = 0; i < t; i++) {
        int n, m;
        cin >> n >> m;
        cout << max(n,m)<<'\n';
    }
    return 0;
}
