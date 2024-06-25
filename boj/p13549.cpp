//
// Created by 문다훈 on 2023/11/22.
//
/*
 * N, K
 * x -> walk -> x-1 or x + 1
 * x -> flash -> 2*x
 *
 * we can use bfs
 * */

#include <bits/stdc++.h>
using namespace std;

int main (void) {

    int N, K;
    int max = 100000;

    vector<int> dp (max+1, -1);

    cin >> N >> K;
    queue<pair<int,int>> q;
    q.push({N,0});
    vector<bool> v (max +1, false);


    while(!q.empty()) {

        pair<int,int> cur = q.front();
        q.pop();
        if(cur.first == K) {
            cout << cur.second << '\n';
            return 0;
        }

        if(cur.first + 1 <= max) {
            if(v[cur.first + 1]) continue;
            v[cur.first +1] = true;
            q.push({cur.first +1 , cur.second + 1});
        }
        if(cur.first -1 >= 0) {
            if(v[cur.first - 1]) continue;
            v[cur.first - 1] = true;
            q.push({cur.first -1, cur.second + 1});
        }
        if(cur.first * 2 > 0 && cur.first * 2 <= max) {
            if(v[cur.first *2]) continue;
            v[cur.first *2] = true;
            q.push({cur.first * 2, cur.second});
        }
    }


    return 0;
}
