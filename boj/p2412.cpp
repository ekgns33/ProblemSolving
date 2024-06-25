//
// Created by 문다훈 on 2024/05/14.
//
#include <bits/stdc++.h>
#include <vector>
#include <unordered_map>
using namespace std;
/*´*/

unordered_map<int, vector<int>> mmap;
struct Compare {
    bool operator() (const int a, const int b) {
        return a -b;
    }
};

int minStep = INT_MAX;
void dfsHelper(int curHeight, int curStep, int x, int T) {
    cout << x << "  :  " << curHeight << endl;
    if(curHeight >= T) {
        minStep = min(minStep, curStep);
        return;
    }
    for(int i = 0; i <= 2; i++) {
        if(mmap[curStep + i].empty()) continue;
        auto low_idx =  lower_bound(mmap[curHeight + i].cbegin(), mmap[curHeight + i].cend(), x-2, Compare());
        auto greater_idx = upper_bound(mmap[curHeight + i].cbegin(), mmap[curHeight + i].cend(), x+2, [](int a, int b){return a-b;});

        int l = *low_idx;
        int r = *greater_idx;
        vector<int> v = mmap[curStep+i];
        if(v[l] <= x) {
            if (v[l] == x) l--;
            while(l >= 0 && x - v[l] <= 2) {
                dfsHelper(curHeight + i, curStep + 1, v[l], T);
                l--;
            }
        }
        if(v[r] >= x) {
            if(v[r] == x) r++;
            while(r < mmap[curStep+ i].size() && v[r] - x <= 2) {
                dfsHelper(curHeight + i, curStep + 1, v[r], T);
                r++;
            }
        }
    }
}
int main (void) {

    int n, T;
    cin >> n >> T;


    for(int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        mmap[y].push_back(x);
    }
    for(auto [key,value] : mmap) {
        sort(value.begin(), value.end());
    }

    dfsHelper(0, 0, 0, T);
    cout << minStep << '\n';

    return 0;
}

/*      *
 *3  *  -   *  -   *
 *2   \   /
 *      *
 *     / \
 *1   *   *
 *   / | /
 *0 *   *
 *  0 1 2 3 4 5
 *
 * [0,1],[1,1], [2,2], [0,3], [2,4]
 * [0,1] [0,3], [1,1], [2,2], [2,4]
 *
 * */
