/**
input : string pattern and string s
output: consider if s follows pattern
constraints: 
1) pattern is not a empty string
2) pattern is consist of lowercase english letter
3) s is not a empty string
4) s contains lowercase eng and ' '
5) s does not starts with ' '
6) the words in s is separated by ' '

edge case : 
1) if the number of words in s is smaller than length of pattern return false;
2) if the length of s is shorter than the length of pattern return false;
    ex) pattern: 'aa' s: 'ssss' s should contain same number of words with the length of pattern


solution 1) using hashmap

split the string s with " " which means get all the separate word

check if the number of splited words is same with pattern's length

from i = 0 to n-1, when n is the length of pattern

    put word in to map if it does not contains

    if does not contain as key and value, put and go next

    if not check if current word is the value of key (current pattern key)

        if it matches continue

        else return false;


time : O(m + n) => O(Max(m,n)), when m is the length of s and n is the length of pattern

space: O(m)

 */
class Solution {
    public boolean wordPattern(String pattern, String s) {
        // edge case
        if(pattern.length() > s.length()) return false;

        String[] words = s.split(" ");

        if(words.length != pattern.length()) return false;

        Map<String, Character> dict = new HashMap<>();

        for(int i = 0; i < pattern.length(); i++) {
            char curC = pattern.charAt(i);
            String curWord = words[i];

            if (!dict.containsKey(curWord)) {
                if(dict.containsValue(curC)) return false;
                dict.put(curWord, curC);
            } else {
                if(dict.get(curWord) != curC) return false;
            }
        } 
        return true;
    }
}