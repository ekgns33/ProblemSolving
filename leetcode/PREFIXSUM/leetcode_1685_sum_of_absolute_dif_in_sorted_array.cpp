class Solution {
public:
    vector<int> getSumAbsoluteDifferences(vector<int>& nums) {
        int n = nums.size();

        vector<int> preSum (n, 0);

        //init presum
        preSum[0] = nums[0];
        for(int i = 1; i < n; i++) {
            preSum[i] = preSum[i-1] + nums[i];
        }

        vector<int> res (n, 0);
        res[0] = preSum[n-1] - (n) * nums[0];
        res[n-1] = abs(preSum[n-1] - (n) * nums[n-1]);

        for(int curIdx = 1; curIdx < n-1; curIdx++) {

            res[curIdx] = abs(preSum[curIdx-1] - (curIdx * nums[curIdx])) + abs((preSum[n-1] - preSum[curIdx]) - (n - curIdx - 1) * nums[curIdx]);

        }

        return res;
    }
};
/*
12 : 02 ~ 12:26 (25 min used, passed at once)

input : integer array which is sorted in non-decreasing order
output : build a integer array such that result[i] is equal to sum of absolute differences b/t nums[i] and other ...

result[i] = sum({j != i && j in range [0, n-1]|nums[i]-nums[j]})
constraints;
1) input array is sorted in non-decreasing order
2) input array could have duplicates? yes
3) could input array be empty? no
4) the length of input array is in range [2, 10^5]
5) elements of input array is positive integers

edge cases) ? i'll come back later

example

2 3 5
1 + 3, 1 + 2, 3 + 2
[4, 3, 5]

1, 5, 10, 15

solution 1) brute-force

iterate through the input array from index i = 0 to n-1
    for each ith element
        iterate through the input array from index j = 0 to n-1
            get all the sum of absolute difference
time : O(n^2) , when n is the lenght of input array
space: O(1);

looking at the summation process


for i = 0

arr[0]- arr[0] 0
arr[1] - arr[0] pos
arr[2] - arr[0] pos
arr[3] - arr[0] pos

for i = 2

arr[0] - arr[2] neg
arr[1] - arr[2] neg  -> sum of element from |[0,1] - 2 * arr[2] |
arr[2] - arr[2] 0
arr[3] - arr[2] pos   -> sum of element from |[2, 3] - 2 * arr[2]|

we could pre calculate the sum of subarray using prefix sum

so each process that calculating the sum of all difference could be shorten by using sum of subarray

solution 2) prefix sum

// calc prefix sum

iterate through the array from i = 0 to n-1

    for i 
        res[i] = abs(pre[i-1] - i * nums[i]) + abs(pre[n-1] - pre[i] - (n - i - 1) * nums[i])

// just make sure when i == 0 and n-1
    logic for index boundary

*/