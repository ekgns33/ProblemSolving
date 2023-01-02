/**

input: string s
output : consider if the string s is following the given cases
constraints: 
1) if s contains whitespace? no
2) if s could be an empty string? no
3) if s is only consists of english letters? no

edgecase)

1) if s.length is 1 return true.


solution 1)

for 0th character of string s check if it is uppercase

    if it is than the value of capitalStart is true

    else false

check for 1th character of string s if it is uppercase
    if it is true
    else false

from i = 2 to n-1 , when n is the length of the string s

    if capitalStart is true 

        1) the last letters must be lowercase  Abbbbb

        2) the last letters must be uppercase AAAAAA

    >> same as the case of left letters should be same
        eg. all lower or all upper

    if capitalStart is false
        all should be lower  aaaaaa


    looking at first and second character

    1) AA >> the rest are upper
    2) Ab >> the rest are lower
    3) aB >> return false
    4) ab >> the rest are lower

    iterate through the string and check the rest letters 
    if they pass the rule above

    time: O(n), when n is the length of string
    space: O(1)
 */

// my first Code 
// failed 2 times..

class Solution {
    public boolean detectCapitalUse(String word) {
        // edge case
        if(word.length() == 1) return true;

        boolean capitalStart = (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z');
        boolean secondCapital = (word.charAt(1) >= 'A' && word.charAt(1) <= 'Z');

        if(!capitalStart && secondCapital) return false;

        for(int i = 2; i < word.length(); i++) {
            char curC = word.charAt(i);
            if(capitalStart) {
                if(secondCapital && (curC >='a' && curC <= 'z')) {
                    return false;
                } else if (!secondCapital && (curC >= 'A' && curC <= 'Z')) {
                    return false;
                }
            } else {
                if(curC >='A' && curC <= 'Z') return false;
            }
        }
        return true;
        
    }
}

// Better Code using Character.isUpperCase

class Solution {
    public boolean detectCapitalUse(String word) {
        if(word.length() < 2) return true;
        if(Character.isUpperCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))){
            for(int i = 2; i < word.length(); i++){
                if(Character.isLowerCase(word.charAt(i))) return false;
            }
        }
        else{
            for(int i = 1; i < word.length(); i++){
                if(Character.isUpperCase(word.charAt(i))) return false;
            }
        }
        return true;
    }
}

// my second code 
// beats 100%, 97%
class Solution {
    public boolean detectCapitalUse(String word) {
        // edge case
        if(word.length() == 1) return true;

        boolean capitalStart = Character.isUpperCase(word.charAt(0));

        char maxCharacter = 'A';
        char minCharacter = 'z';

        for(int i = 1; i < word.length(); i++) {
            char curC = word.charAt(i);

            if(maxCharacter < curC) maxCharacter = curC;
            if(minCharacter > curC) minCharacter = curC;
        }

        // if start is upper >> all down or all up
        if(capitalStart) {
            return !(Character.isUpperCase(minCharacter) ^ Character.isUpperCase(maxCharacter));
        } else { // if start is down >> all down
            return (Character.isLowerCase(minCharacter) && Character.isLowerCase(maxCharacter));

        }
        
    }
}