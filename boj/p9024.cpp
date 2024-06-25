//
// Created by 문다훈 on 2024/05/29.
//
/*
 * input : unique integers and target K
 * output : the number of pairs which sum is nearest to K
 *
 * constraints:
 * 1. unique integers
 * 2. integer range - 10^8 ~ 10^8
 * 3. input would be valid
 * 4. input array's size n 2<= 2 <= 10^6
 *
 * solution1) brute force
 * get all combination of pair_sum
 * O(n^2)
 * save to tree map (sorted)
 * find nearest value.
 * O(logn)
 *
 * O(n^2 + logn) ~= O(n^2)
 *
 * solution 2) using two pointers
 * sort input array
 * O(nlogn)
 *
 * sum of two pointers indicating value leads to K
 * (sorted)
 * O(N)
 * time : O(Nlogn)
 * *
 * */
#include <bits/stdc++.h>
using namespace std;
vector<int> nums;

void readInput(vector<int>& v, int n) {
    int input;
    for(int i = 0; i < n; i++) {
        cin >> input;
        v.push_back(input);
    }
}

void solution(int N, int K) {
    nums.clear();
    readInput(nums, N);
    sort(nums.begin(), nums.end());
    int l = 0, r = nums.size()-1;
    int sum = 0, minDiff = INT_MAX, count = 0;
    while(l < r) {
        sum = nums[l] + nums[r];
        int diff = abs(K - sum);
        if(diff < minDiff) {
            minDiff = diff;
            count = 1;
        } else if (diff == minDiff) {
            count++;
        }
        if(sum <= K) {
            l++;
        } else {
            r--;
        }
    }
    cout<< count << '\n';
}
int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int T, N, K;
    cin >> T;
    for(int i = 0; i < T; i++) {
        cin >> N >> K;
        solution(N, K);
    }
}