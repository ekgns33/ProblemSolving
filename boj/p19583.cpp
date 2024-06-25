//
// Created by 문다훈 on 2024/06/02.
//
#include "bits/stdc++.h"
using namespace std;
int main (void) {
    string s_start, s_end, v_end;
    cin >> s_start >> s_end >> v_end;

    set<string> attendee;
    set<string> checked;
    string log_time;
    string user_name;
    while(cin >> log_time >> user_name) {
        if(cin.eof() == true) break;
        if(log_time <= s_start) {
            attendee.insert(user_name);
        }
        else if (s_end <= log_time && log_time <= v_end) {
            if(attendee.find(user_name) != attendee.end()) {
                checked.insert(user_name);
            }
        }
    }
    cout << checked.size();
}
