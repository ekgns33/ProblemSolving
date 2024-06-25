//
// Created by 문다훈 on 2024/05/20.
//
#include <bits/stdc++.h>
using namespace std;
int main(void) {

    int N, M;
    cin >> N >> M;
    vector<int> trees (N, 0);
    for(int i = 0; i < N; i++) {
        cin >> trees[i];
    }
    sort(trees.begin(), trees.end());
    int lowerBound = 0;
    int upperBound = trees[N - 1];
    int ret = 0;
    while(lowerBound <= upperBound) {
        int mid = upperBound - (upperBound - lowerBound) / 2;
        long clearHeight = 0;
        for(auto tree : trees) {
            if(tree - mid > 0) {
                clearHeight += (tree - mid);
            }
        }
        if(clearHeight  < M) {
            upperBound = mid - 1;
        } else {
            ret = max(ret, mid);
            lowerBound = mid + 1;
        }
    }
    cout << ret << '\n';
    return 0;
}
