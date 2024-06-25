//
// Created by 문다훈 on 2024/05/22.
//
#include <bits/stdc++.h>
using namespace std;



vector<string> destruct_parenthesis(string input, int start, int end) {
    vector<string> v;
    v.push_back("");
    stack<int> startPos;
    for(int i = start; i <= end; i++) {
        if(input[i] == '(') {
            startPos.push(i);
        } else if(input[i] == ')') {
            int prev_start = startPos.top();
            startPos.pop();
            if(!startPos.empty()) continue;
            vector<string> partial_ret = destruct_parenthesis(input, prev_start+1, i-1);
            vector<string> v_next;
            for(string str : v) {
                for(auto partial_str : partial_ret) {
                    v_next.push_back(str + "(" + partial_str + ")");
                    v_next.push_back(str + partial_str);
                }
            }
            v.swap(v_next);
        } else { // characters
            if(!startPos.empty()) continue;
            for(int j = 0; j < v.size(); j++) {
                v[j] += input[i];
            }
        }
    }

    return v;
}

int main (void) {
    vector<string> combinations;
    string input;
    cin >> input;

    combinations = destruct_parenthesis(input, 0, input.size()-1);
    sort(combinations.begin(), combinations.end());
    set<string> s_set;
    for(int i = 1; i < combinations.size(); i++) {
        if(s_set.find(combinations[i]) == s_set.end()) {
            cout << combinations[i] << '\n';
            s_set.insert(combinations[i]);
        }
    }
    return 0;

}