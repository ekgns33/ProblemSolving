/*
1:05 ~

input: 2D matrix that represents the balloon's horizontal diameter's range
ex) point[1] = [1, 5]
    point[2] = [3, 4] 
      ////  < range
  /////////  <range
0 1 2 3 4 5
      if arrow shot at 4 > two balloons burst
output the minimum number of  arrow to burst all the balloons

constraints :
1) balloons number : at least one
2) range of x > - 2^31 && x < 2^31
3) point Xstart < Xend

edge case : 
1) if point.length == 1 > return 1
    if only one balloon exists than we need one arraw to burst one.

solution 1)

         [    ]
     [     ]
[      ]         [   ]

sort by start point

from the first element
pop and get end point

while next element's start point is smaller than current end point count up

sort O(nlogn)

at the end check if we have to burst one


> 3 arrow

 */

// time : O(nlogn) space : O(n)
class Solution {
    public int findMinArrowShots(int[][] points) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare (int[] a, int[] b){
                if(a[0] == b[0]) return Integer.compare(a[1], b[1]);
                return Integer.compare(a[0], b[0]);
            } 
        });
        for(int[] point : points) {
            pq.add(point);
        }
        int cnt = 0;
        int endPoint = 0;
        while(!pq.isEmpty()) {
            int[] curRange = pq.poll();
            endPoint = curRange[1];
            while(!pq.isEmpty() && pq.peek()[0] <= endPoint) {
                if(pq.peek()[1] < endPoint) {
                    endPoint = pq.peek()[1];
                }
                pq.poll();
            }
            cnt++;
        }


        return cnt;
        
    }
}
// time: O(nlogn) space : O(1)
class Solution {
    public int findMinArrowShots(int[][] points) {

       if(points.length == 1 ) return 1;

       // element의 범위가 int 양수 음수인데 그냥 연산하면 오버플로우 생김.

       Arrays.sort(points, (a,b) -> (Integer.compare(a[1],b[1])));

        // 처음 endpoint에 화살을 하나 쏘고 시작.
        int cnt = 1;
        int endPoint = points[0][1];

        for(int i = 1; i < points.length; i++) {
            if(points[i][0] <= endPoint) {
                continue;
            }
            cnt++;
            endPoint = points[i][1];

        }
        return cnt;
        
    }
}
/**

    
    [      ]
           ^
        [     ]
      [            ]
                 [              ]
                                ^
  [                                 ]
                
 */