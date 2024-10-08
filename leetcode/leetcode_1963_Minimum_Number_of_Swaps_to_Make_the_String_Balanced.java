import java.util.Stack;
/**
 * https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
 * idea : 불균형한 pair들의 개수에 따라서 몇번 밸런싱하면되는지 규칙을 찾는다.
 * ]]][[[ > 2번
 * ]]]][[[[ > 2번
 * ]]]]][[[[[ > 3번
 * */
public class leetcode_1963_Minimum_Number_of_Swaps_to_Make_the_String_Balanced {
    class Solution {
        public int minSwaps(String s) {
            Stack<Character> st = new Stack<>();
            int count;
            for(char c: s.toCharArray()) {
                if(c == ']' && !st.isEmpty()) {
                    char lastchar = st.pop();
                    if(lastchar !='[') {
                        // st.pop();
                        st.push(c);
                    }
                } else {
                    st.push(c);
                }
            }
            if(st.size() == 0){
                return 0;
            }
            int size = st.size();
            return (size + 1) / 2;
        }
    }
}
