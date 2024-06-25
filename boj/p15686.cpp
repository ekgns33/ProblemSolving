//
// Created by 문다훈 on 2024/04/01.
//

//brute force -> 2^13 * k * N^2
#include <bits/stdc++.h>
using namespace std;
typedef vector<pair<int,int>> vp;
vector<pair<int, int>> chickens;
vector<pair<int,int>> houses;
int minDist = INT_MAX;
vp selected;
void backTrackingHelper(vp& houses, vp& chickens, int cnt, int curN, int v, int M) {
    if(cnt == M || curN ==chickens.size()) {
        //calc min dist
        int curDist = 0;
        for(auto house : houses) {
            int minD = INT_MAX;
            for(int i = 0; i < selected.size(); i++) {
                minD = min(minD, (abs(house.first - selected[i].first) + abs(house.second - selected[i].second)));
            }
            curDist += minD;
        }
        minDist = min(minDist, curDist);
        return;
    }

    for(int i = curN; i < chickens.size(); i++) {
        if(((1<<i) & v) == 0) {
            int next = (1<<i) | v;
            selected.push_back({chickens[i].first, chickens[i].second});
            backTrackingHelper(houses, chickens, cnt+ 1, i+1, next, M);
            selected.pop_back();
        }
    }
}



int main (void) {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int N, M;
    cin >> N >> M;

    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            int tmp;
            cin >> tmp;
            if(tmp == 1) houses.push_back({i, j});
            if(tmp == 2) chickens.push_back({i, j});
        }
    }
    backTrackingHelper(houses, chickens,0, 0, 0, M);

    cout << minDist;
}