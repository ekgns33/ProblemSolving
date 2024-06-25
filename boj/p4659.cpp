//
// Created by 문다훈 on 2024/03/28.
//

#include <bits/stdc++.h>
using namespace std;

set<char> vowel = {'a', 'e', 'i', 'o', 'u'};
bool validatePassword(string& input) {

    bool isVowelExist = false;
    bool lastWordVowel = vowel.find(input[0])!= vowel.end();
    isVowelExist = lastWordVowel;
    int continuousTypeLen = 1;
    for(int i = 1; i < input.size(); i++) {
        char curC = input[i];
        // check if vowel exists
        bool isVowel = vowel.find(curC) != vowel.end();
        if(!isVowelExist && isVowel) {
            isVowelExist = true;
        }
        if(isVowel && lastWordVowel && continuousTypeLen == 2) return false;
        if(!isVowel && !lastWordVowel && continuousTypeLen == 2) return false;

        if(!isVowel != !lastWordVowel) {
            continuousTypeLen = 1;
            lastWordVowel =  isVowel;
        } else {
            if (input[i - 1] == input[i] && input[i] != 'e' && input[i] != 'o') {
                return false;
            }
            lastWordVowel = isVowel;
            continuousTypeLen++;
        }
    }

    return isVowelExist;
}


int main (void) {
    string input;
    cin >> input;
    // until end loop
    while(input != "end") {
        if (validatePassword(input)) {
            cout << "<" << input << ">" << " is acceptable.";
        } else {
            cout << "<" << input << ">" << " is not acceptable.";
        }
        cout << '\n';
        cin >> input;
    }


}