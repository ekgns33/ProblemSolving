/*
1:55 ~ 2: 17

서로다른 유저 신고가능
동일유저 신고 1회처리

k이상 신고당한 유저 > 정지
신고한유저한테 정지사실 전송

유저별로 정지사실 받을 메일 횟수

map <유저이름 : 유저정보객체>

유저가 신고 
유저 정보객체의 신고한사람 set에 추가
유저 정보객체 신고당한사람 횟수 1업


유저 : {
id
신고당한 횟수
신고한 유저
}
    
유저정보를 활용할게 많아서 그냥 유저 클래스를 새로만들고
key-value 식으로 자료구조를 설정함.

유저 리폿리스트쭉읽으면서 유저 정보에 기록하고
밴당한유저들 따로모아서 유저 밴리스트에 밴당한유저들이 있는지 확인하고 수에 맞게 리턴해줌.

다른 사람 풀이를 확인해보니
JAVA Stream 쓰면 중복의 문제를 매우 쉽게 해결가능.. 
List<String> list = Arrays.stream(report).distinct().collect(Collectors.toList());


*/
import java.util.*;
class Solution {
    
    class User {
        int uid;
        int hit;
        Set<String> reportList;
        
        public User(int uid) {
            this.uid = uid;
            this.hit = 0;
            this.reportList = new HashSet<String>();
        }
        
        public void report(String target) {
            if(!this.reportList.contains(target)) {
                reportList.add(target);
            }
        }
        
    }
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        Map<String, User> map = new HashMap<>();
        //init userlist
        for(int i = 0; i < id_list.length; i++) {
            map.put(id_list[i], new User(i));
        }
        
        //read reportlist
        
        for(String reportInfo : report) {
            String[] users = reportInfo.split(" ");
            
            // report user
            if(!map.get(users[0]).reportList.contains(users[1])) {
                map.get(users[0]).report(users[1]);
                map.get(users[1]).hit++;
            }
            // target user hit !
            
        }
        
        // make list
        Set<String> bannedUser = new HashSet<>();
        
        for(User userInfo : map.values()) {
            if(userInfo.hit >= k) {
                bannedUser.add(id_list[userInfo.uid]);
            }
        }
        
        
        
        for(int i = 0; i < id_list.length; i++) {
            String userName = id_list[i];
            
            Set<String> reportList = map.get(userName).reportList;
            
            //check report list and if contain cnt++;
            for(String str : bannedUser) {
                if(reportList.contains(str)) {
                    answer[i]++;
                }
            }
        }
        
        
        return answer;
    }
}