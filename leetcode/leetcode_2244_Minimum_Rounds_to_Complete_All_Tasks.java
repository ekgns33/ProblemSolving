/**

complete 2 or 3 same difficulty task

get the minimum round to complete all the task
if not return -1

input: integer array
output: the minimum round to complete all the task
constraints :
1) input array is not empty
2) the value of array is positive
3) unsorted array

edgecase : 
1) if the length of input array is 1 return -1

solution 1)

what i want is the minimum round that could complete given tasks

>> at each round, there are 2 choice
    1) complete 2 same difficulty task
    2) complete 3 same difficulty task
>> kinda dp problem

ex
    0 1 2 3 4 5
    2 2 2 3 3 4 4 4 4 4

 0  M 1 1 M 2 M 3 3 4 M
      1 1   2 
 
    2 3 3
 0  M M M


    time: O(nlogn);
    space: O(n);


    Mathmatic approach

    어차피 같은애들은 같은 애들끼리 처리하니까 순서는 큰 의미가 없음.
    모든 애들의 출현빈도를 구해서 그 빈도가 우리가 처리가능한 개수인지를 보는 방법이 더 쉬움.
    2개 또는 3개는 처리가능하니 2의배수, 3의 배수는 상관없음. 
    또 3의 배수 + 2의배수 꼴도 해결가능함.
    근데 1남는건안됨.
    이거를 이제 해쉬맵을 써서 해결하면 O(n), O(n) 가능하다.

    나는 2 or 3이라는 선택지가 존재함과, 매 순간의 선택으로 최소의 라운드값을 구해야한다는 점에서
    dp로 푸는게 아닐까 생각함.


   1 > fail
   2 > 2
   3 > 3 / 3k  
   4 > 2 * k  / 3k + 1
   5 > 2 + 3 / 3 k + 2
   6 > 3 * k

 */

class Solution {
    public int minimumRounds(int[] tasks) {

        if(tasks.length == 1) return -1;

        Arrays.sort(tasks);


        int[] dp = new int[tasks.length + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = 0;
        dp[2] = (tasks[0] == tasks[1] ? 1 : Integer.MAX_VALUE);

        for(int i = 2; i < tasks.length; i++) {

            int rangeOfTwo = tasks[i-1];
            int rangeOfThree = tasks[i-2];
            
            //could process two continuous task
            if(rangeOfTwo == tasks[i] && dp[i-1] != Integer.MAX_VALUE) {
                dp[i+1] = dp[i -1] + 1;
            }
            //could process three continuous task
            if(rangeOfThree == tasks[i] && dp[i-2] != Integer.MAX_VALUE) {
                dp[i+1] = Math.min(dp[i+1], dp[i-2] + 1);
            }
        }
        // for(int i = 0 ; i < dp.length; i++) {
        //     System.out.print(dp[i] + " ");
        // }
        // System.out.println();
        return dp[dp.length - 1] != Integer.MAX_VALUE ? dp[dp.length - 1] : -1;
        
    }
}

class Solution {
    public int minimumRounds(int[] tasks) {

        if(tasks.length == 1) return -1;
        Map<Integer, Integer> map = new HashMap<>();

        for(int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
     
        int roundCnt = 0;
        for(int freq : map.values()) {
            if(freq == 1) return -1;
            roundCnt += (freq + 2) / 3;
        }
        return roundCnt;
        
    }
}
class Solution{
    public int minimumRounds(int[] A) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int a : A)
            count.put(a, count.getOrDefault(a, 0) + 1);
        int res = 0;
        for (int freq : count.values()) {
            if (freq == 1) return -1;
            res += (freq + 2) / 3;
        }
        return res;
    }
}