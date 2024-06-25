//
// Created by 문다훈 on 2024/05/12.
//
#include <bits/stdc++.h>

using namespace std;

vector<int> heap (100001, -1);
int heapSize = 0;
/*
 *          0
 *      1       2
 *  3    4     5    6
 * 7 8 9 10 11 12 13 14
 * */
void insert(int val) {
    heapSize++;
    heap[heapSize-1] = val;
    int index = heapSize - 1;
    while(index >0 && heap[(index) /2] > heap[index]) {
        int tmp = heap[index];
        heap[index] = heap[(index) /2];
        heap[(index) / 2] = tmp;
        index = (index) /2;
    }
}
int remove() {
    if(heapSize == 0) return 0;
    int ret = heap[0];
    heap[0] = heap[heapSize -1];
    heap[heapSize -1] = -1;
    heapSize--;

    int curr = 0;
    while(curr <= heapSize-1/ 2){
        int left = 2 * curr;
        int right = 2 * curr + 1;
        int min = curr;

        if(left < heapSize && heap[left] < heap[min]) {
            min = left;
        }
        if(right < heapSize && heap[right] < heap[min]) {
            min = right;
        }
        if(min != curr) {
            int tmp = heap[min];
            heap[min] = heap[curr];
            heap[curr] = tmp;
            curr = min;
        } else {
            break;
        }

    }
    return ret;

}


int main (void) {

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int N;
    cin >> N;
    for(int i = 0; i < N; i++) {
        int nextN;
        cin >> nextN;
        if(nextN != 0) {
            insert(nextN);
        } else {
            cout << remove() << '\n';
        }
    }
    return 0;
}