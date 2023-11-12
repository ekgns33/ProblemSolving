//
// Created by 문다훈 on 2023/11/12.
//
#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    int numBusesToDestination(vector<vector<int>>& routes, int source, int target) {
        unordered_map<int, vector<int>> stop_routes;
        for(int i = 0; i < routes.size(); i++) {
            for(int stop : routes[i]) {
                stop_routes[stop].push_back(i);
            }
        }
        queue<int> path_queue;
        set<int> visited = {source};
        int level_size = 1;
        int used_bus = 0;
        path_queue.push(source);
        while(!path_queue.empty()) {
            for(int t  = 0; t < level_size; t++) {
                int stop = path_queue.front();
                path_queue.pop();

                if(stop == target) {
                    return used_bus;
                }

                for(int route : stop_routes[stop]) {
                    for(int next_stop : routes[route]) {
                        if(!visited.contains(next_stop)) {
                            visited.insert(next_stop);
                            path_queue.push(next_stop);
                        }
                    }
                    routes[route].clear();
                }
            }
            level_size = path_queue.size();
            used_bus++;
        }
        return -1;
    }
};