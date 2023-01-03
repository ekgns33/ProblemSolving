/**

input: string array
output : the number of columns that have to be deleted
constraints: 
the length of words in array are same
there are no empty word
words are consist of lowercase english letters

edgecase:

solution 1)
time: O(nm), when n is the length of word, m is the length of array
space: O(m)


compare word's 'each letter from previous word

if it breaks the lexicographical rule than check the index and increment the count

for i from 0 to n-1
    compare with prev word for each indices

    if break rule change to 'E' which indicates that index column shold be removed


아래 풀이는 우선 속도, 공간상에서 좋지는 않다.
간단하게 아래로 나아가면서 비교하면되는데, prev문자와 비교한다는 점고 그게 후에 영향을 미친다는 점에서
dp느낌으로 풀었던 것 같다. maximum prefix sum 뭐 이런느낌으로

 */
class Solution {
    public int minDeletionSize(String[] strs) {
        
        char[] prev = new char[strs[0].length()];

        // a is the smallest letter in lexicographical order
        Arrays.fill(prev, 'a'); 

        int cnt = 0;

        for(int i = 0; i < strs.length; i++) {
            String curWord = strs[i];
            for(int j = 0; j < curWord.length(); j++) {
                char curC = curWord.charAt(j);
                if(prev[j] == 'E') continue;

                if(curC >= prev[j]) {
                    prev[j] = curC;
                } else {
                    prev[j] = 'E';
                    cnt++;
                }
            }
        }

        return cnt;

    }
}

// Better Solution

/**

단어들을 좌에서 우로 보면서 진행했는데
위에서 아래로 보면서 그냥 열마다 보면 중간에 끊을 수 있다.
메모리도 더 필요하지 않음.
time : O(mn)
space: O(1)

참고) prev 변수만들어서 비교하는게 배열 직접참조하는 거보다 속도면에서 좋다.

 */
 class Solution {
    public int minDeletionSize(String[] strs) {
        
        int cnt = 0;

        for(int i = 0; i < strs[0].length(); i++) {
            char prev = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++) {
                char curC = strs[j].charAt(i);
                if(curC < prev) {
                    cnt++;
                    break;
                }
                prev = curC;
            }
        }

        return cnt;

    }
}
