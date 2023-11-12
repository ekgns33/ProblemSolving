class Solution {
public:
    int numTrees(int n) {
        vector<int> dp (n + 1, 0);
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <i; j++) {
                //left 
                int left = dp[j - 1];
                //right
                int right = dp[i - j];
                dp[i] += left * right;
            }
            dp[i] += dp[i-1];
        }
        return dp[n];
    }
};
/*

bst
1 : 1
2 : 2

1 -> 0 : 0
2 -> 1: 0 + 0 : 1
3 -> 0: 3 + 3 :0 ...
*/