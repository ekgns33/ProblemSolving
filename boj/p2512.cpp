//
// Created by 문다훈 on 2024/05/20.
//
#include <bits/stdc++.h>
using namespace std;

int getTotalBudget(const vector<int>& requests, int upperBound) {
    int ret = 0;
    for(auto request : requests) {
        if(request < upperBound) {
            ret += request;
        } else {
            ret += upperBound;
        }
    }
    return ret;
}

int main (void) {

    int N, budget;
    cin >> N;

    vector<int> requests (N, 0);
    int minB = INT_MAX, maxB = 0;
    for(int i = 0; i < N; i++) {
        cin >> requests[i];
        minB = min(minB, requests[i]);
        maxB = max(maxB, requests[i]);
    }

    cin >> budget;
    int lowerBound = 0, upperBound = maxB;
    int ret = 0;
    int mid = 0;
    while(lowerBound <= upperBound) {
        mid = upperBound - (upperBound - lowerBound) / 2;
        int totalBudget = getTotalBudget(requests, mid);
        if(totalBudget > budget) {
            upperBound = mid -1;
        } else if (totalBudget <= budget) {
            ret = max(mid, ret);
            lowerBound = mid + 1;
        }
    }
    cout << lowerBound -1 << '\n';

    return 0;
}