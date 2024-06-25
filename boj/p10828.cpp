//
// Created by 문다훈 on 2024/05/23.
//
#include <bits/stdc++.h>
using namespace std;

vector<int> stk;
void init() {
    stk = vector<int>();
}

void push(int x) {
    stk.push_back(x);
}
int empty() {
    return stk.empty();
}
int pop() {
    if(!empty()) {
        int ret = stk[stk.size() - 1];
        stk.pop_back();
        return ret;
    }
    return -1;
}
int top() {
    if(!empty()) {
        return stk[stk.size() -1];
    }
    return -1;
}

int size() {
    return stk.size();
}


pair<string, string> read_input() {
    string command, arg = "";
    cin >> command;
    if(command == "push") {
        cin >> arg;
    }
    return make_pair(command, arg);
}
void command() {
    pair<string, string> input = read_input();

    string cmd = input.first;
    int arg;

    if(cmd == "push") {
        arg = stoi(input.second);
        push(arg);
    }
    if(cmd == "top") { cout << top() << '\n';}
    if(cmd == "size") { cout << size() << '\n';}
    if(cmd == "empty") {cout << empty() << '\n';}
    if(cmd == "pop") { cout << pop() << '\n'; }

}


int main(void) {
    int N;
    cin >> N;
    for(int i = 0; i < N; i++) {
        command();
    }
}
