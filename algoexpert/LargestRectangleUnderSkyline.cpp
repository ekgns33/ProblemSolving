//
// Created by 문다훈 on 2023/11/13.
//
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int largestRectangleUnderSkyline(vector<int> buildings) {
    stack<int> stk;
    buildings.push_back(-1);
    stk.push(-1);
    //base case
    int maxSpace = 0;
    for(int i = 0; i < buildings.size(); i++) {
        int curHeight = buildings[i];
        while(stk.size() > 1 && buildings[stk.top()] > curHeight) {
            int prevHeight = buildings[stk.top()];
            stk.pop();
            maxSpace = max(maxSpace, prevHeight * (i - stk.top() - 1));
        }
        stk.push(i);
    }

    return maxSpace;
}
/*

        -
      ---
      -------
    ----------------
INF 1 3 3 2 4 1 5 3 2 INF

*/
