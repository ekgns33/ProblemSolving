//
// Created by 문다훈 on 2024/03/28.
//

#include<bits/stdc++.h>
using namespace std;

int main (void) {
    int N, M, J;
    cin >> N >> M;
    cin >> J;
    int leftLimit = 1;
    int rightLimit;
    int movement = 0;
    for(int i = 0; i < J; i++) {
        int nextApple;
        cin >> nextApple;
        rightLimit = leftLimit + M - 1;

        if(leftLimit <= nextApple && nextApple <=rightLimit) continue;

        if(leftLimit > nextApple) {
            movement += leftLimit - nextApple;
            leftLimit = nextApple;
        } else if (leftLimit <  nextApple){
            movement += nextApple - rightLimit;
            leftLimit += nextApple - rightLimit;
        }
    }
    cout << movement;

}