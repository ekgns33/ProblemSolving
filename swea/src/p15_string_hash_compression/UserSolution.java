package p15_string_hash_compression;

import java.util.*;

class UserSolution
{

  String initString = "";
  LinkedList<Character> deque = new LinkedList<>();
  boolean isReversed = false;
  int[] map = new int[(1<<20)];

  void init(char mStr[])
  {
    isReversed = false;
    for(int i = 0; i < map.length; i++) {
      map[i] = 0;
    }
    deque.clear();
    for(int i = 0; mStr[i] != '\0'; i++) {
      deque.add(mStr[i]);
      updateKey(1);
    }
  }

  void updateKey(int del) {
    if(isReversed) {
      int key = 0;
      for(int i = 0; i < Math.min(4, deque.size()); i++) {
        key <<= 5;
        key += (deque.get(i) - 'a' + 1);
        map[key] += del;
      }
    } else {
      int key = 0;
      int digit = 1;
      for(int i = deque.size()-1; i >= Math.max(0, deque.size() - 4); i--) {
        key += digit * (deque.get(i) - 'a' + 1);
        digit <<= 5;
        map[key] += del;
      }
    }
  }

  void appendWord(char mWord[])
  {
      if(isReversed) {
        for(int i = 0; mWord[i] != '\0'; i++) {
          deque.addFirst(mWord[i]);
          updateKey(1);
        }
      } else {
        for(int i = 0; mWord[i] != '\0'; i++) {
          deque.addLast(mWord[i]);
          updateKey(1);
        }
      }

  }

  void cut(int k)
  {
    if(isReversed) {
      for(int i = 0; i < k; i++) {
        updateKey(-1);
        deque.removeFirst();
      }
    } else {
      for(int i = 0; i < k; i++) {
        updateKey(-1);
        deque.removeLast();
      }
    }
  }

  void reverse()
  {
    isReversed = !isReversed;
  }

  int countOccurrence(char mWord[])
  {
   int key = 0;
   if(!isReversed) {
     for(int i = 0; mWord[i] != '\0'; i++) {
       key <<= 5;
       key += mWord[i] - 'a' + 1;
     }
   } else {
     int digit = 1;
     for(int i = 0; mWord[i] != '\0'; i++) {
       key += digit * (mWord[i] - 'a' + 1);
       digit <<= 5;
     }
   }
   return map[key];
  }
}