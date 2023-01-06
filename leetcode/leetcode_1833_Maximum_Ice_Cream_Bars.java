/*
1:32~1:45

as many icecream

input : integer array indicates the cost of n ice cream bars
output : the maximum number of bars the boy can buy with given coins

constraints : 
1) boy can buy ice cream bars in any order
2) at least one ice cream bar exists
3) cannot buy same ice cream bar


edgecase:
1) if every cost of ice cream bar is larger than given coin return 0;
2)

solution 1) brute force 
time : O(2^n)
space : O(n)
algorithm : back tracking

solution 2) better sol
time : O(nlogn)
space : O(n) >> Arrays.sort() needs O(n) space complexity in worst
using 'modified merge sort'

sort by cost 
start from first cost 
add until sum of costs is bigger than given coins

we cannot buy the same ice cream bar.
Therefore we should buy ice cream bars from the cheapest one to save the budget.


아니 개쉬운데...

그리디접근법이라는데 아직 그리디에 대한 감이 덜잡힘.
상식적으로 그냥 작은거부터 사면 제일 적겠지 싶었음.
 */
class Solution {
    public int maxIceCream(int[] costs, int coins) {

        Arrays.sort(costs);
        int sum = 0;
        int cnt = 0;
        for(int cost : costs) {
            sum += cost;
            if(sum > coins) {
                break;
            }
            cnt++;
        }
        return cnt;
        
    }
}
// 사람들이 적어놓은 코드인데 나랑 다르게 coins를 줄여나가면서 따로 변수선언을 안함.
// 근데 input variable 을 그대로 쓰는건 좀 별로인것같음. 차라리 coin변수를 둬서 그거로 해야징
public int maxIceCream(int[] costs, int coins) {
    Arrays.sort(costs);
    for (int i = 0; i < costs.length; ++i)
        // coins 가 줄어듦에 따라 뒤에 영향을 미친다. 그리디?
        if (coins >= costs[i])
            coins -= costs[i];
        else
            return i;
    return costs.length;
}