package v3_p2_news;

import java.util.*;

class UserSolution {


  class FeedMap {
    List<int[]> feedList = new ArrayList<>();
    Map<Integer, Integer> lastUpdate = new HashMap<>();
    int findIdx(int id) {
      int l = 0;
      int r = feedList.size()-1;
      while(l <= r) {
        int mid = (l+r)/2;
        if(feedList.get(mid)[0] == id) {
          return mid;
        } else if (feedList.get(mid)[0] > id) {
          r = mid-1;
        }  else {
          l = mid+1;
        }
      }
      return l;
    }
    void add(int[] feed) {
      feedList.add(findIdx(feed[0]+1), feed);
    }
    void remove(int id) {
      feedList.removeIf(feed -> feed[1] == id);
    }

    public List<int[]> between(int uID,int mTime) {
      List<int[]> res = new ArrayList<>();
      int s = findIdx(lastUpdate.get(uID));
      int e = findIdx(mTime);
      if(s >= feedList.size() || feedList.get(s)[0] > mTime) return res;
      for(int i = s; i <= e && i < feedList.size(); i++) {
        int val = feedList.get(i)[0];
        if(val > mTime) break;
        res.add(feedList.get(i));
      }
      return res;
    }
  }

  Map<Integer, FeedMap> channel = new HashMap<>();
  Map<Integer, Integer> feed = new HashMap<>();
  Map<Integer, Set<Integer>> subscribedChannels = new HashMap<>();
  Map<Integer, Set<Integer>> subscribers = new HashMap<>();
  Map<Integer, Integer> lastCheck = new HashMap<>();

  int N, K;

  void init(int N, int K)
  {
    this.N = N;
    this.K = K;
    channel.clear();
    subscribedChannels.clear();
    subscribers.clear();
    lastCheck.clear();
  }

  void subscribe(int userId, int channelId) {
    subscribedChannels.putIfAbsent(userId, new HashSet<>());
    subscribedChannels.get(userId).add(channelId);
    subscribers.putIfAbsent(channelId, new HashSet<>());
    subscribers.get(channelId).add(userId);
  }

  void registerUser(int mTime, int mUID, int mNum, int mChannelIDs[])
  {
    for(int i = 0; i < mNum; i++) {
      subscribe(mUID, mChannelIDs[i]);
      channel.putIfAbsent(mChannelIDs[i], new FeedMap());
      channel.get(mChannelIDs[i]).lastUpdate.put(mUID, mTime+1);
    }
  }

  int offerNews(int mTime, int mNewsID, int mDelay, int mChannelID)
  {
    channel.putIfAbsent(mChannelID, new FeedMap());
    FeedMap feedHistory = channel.get(mChannelID);
    feedHistory.add(new int[] {mDelay + mTime, mNewsID});
    feed.put(mNewsID, mChannelID);
    return subscribers.get(mChannelID).size();
  }

  void cancelNews(int mTime, int mNewsID)
  {
    channel.get(feed.get(mNewsID)).remove(mNewsID);
  }

  List<int[]> merge(List<int[]> tmp, List<int[]> list) {
    List<int[]> temp = new ArrayList<>();
    int n = list.size();
    int l = n-1, r = tmp.size()-1;
    int i = 2;
    while((l >= 0 || r >= 0) && i >= 0) {
      if(l < 0) {
        temp.add(0, tmp.get(r));
        r--;
        i--;
        continue;
      }
      if(r < 0) {
        temp.add(0,list.get(l));
        l--;
        i--;
        continue;
      }
      if(list.get(l)[0] > tmp.get(r)[0]) {
        temp.add(0,list.get(l));
        l--;
      } else if (tmp.get(r)[0] > list.get(l)[0]) {
        temp.add(0,tmp.get(r));
        r--;
      } else {
        if(list.get(l)[1] > tmp.get(r)[1]) {
          temp.add(0,list.get(l));
          l--;
        } else {
          temp.add(0,tmp.get(r));
          r--;
        }
      }
      i--;
    }
    return temp;
  }
  int checkUser(int mTime, int mUID, int mRetIDs[])
  {

    Set<Integer> subscribed = subscribedChannels.get(mUID);
    List<int[]> temp = new ArrayList<>();
    int cnt= 0;
    for(int channelId : subscribed) {
      FeedMap feedHistory = channel.get(channelId);
      List<int[]> news = feedHistory.between(mUID, mTime);
      cnt += news.size();
      temp = merge(news, temp);
      channel.get(channelId).lastUpdate.put(mUID, mTime+1);
    }
    Collections.reverse(temp);
    for(int i = 0; i < temp.size(); i++) {
      mRetIDs[i] = temp.get(i)[1];
    }
    return cnt;
  }
}
