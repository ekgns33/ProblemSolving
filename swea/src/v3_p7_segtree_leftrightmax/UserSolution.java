package v3_p7_segtree_leftrightmax;

class UserSolution
{

  static class Node {
    int max;
    int leftMaxIdx;
    int rightMaxIdx;

    public Node(int max, int leftMaxIdx, int rightMaxIdx) {
      this.max = max;
      this.leftMaxIdx = leftMaxIdx;
      this.rightMaxIdx = rightMaxIdx;
    }
  }

  static Node[] tree = new Node[1 << 22];
  static int[] loads = new int[100001];

  Node merge(Node a, Node b) {
    if(a.max > b.max) return a;
    if(b.max > a.max) return b;
    return new Node(a.max, a.leftMaxIdx, b.rightMaxIdx);
  }

  void update(int n, int l, int r, int idx, int val) {
    if(l == r) {
      tree[n] = new Node(val, idx, idx);
      return;
    }
    int mid = (l + r) >> 1;
    if(idx <= mid) {
      update(n << 1, l, mid, idx, val);
    } else {
      update(n << 1 | 1, mid + 1, r, idx, val);
    }
    tree[n] = merge(tree[n << 1], tree[n << 1 | 1]);
  }

  Node query(int n, int l, int r, int ql, int qr) {
    if(qr < l || r < ql) return new Node(0, 0, 0);
    if(ql <= l && r <= qr) return tree[n];
    int mid = (l + r) >> 1;
    Node left = query(n << 1, l, mid, ql, qr);
    Node right = query(n << 1 | 1, mid + 1, r, ql, qr);
    return merge(left, right);
  }
  static int N = 0;
  static int sum = 0;
  void calc() {
    Node top = tree[1];
    sum = top.max * (top.rightMaxIdx - top.leftMaxIdx + 1);
    int leftIndx = top.leftMaxIdx - 1, rightIndx = top.rightMaxIdx + 1;
    while(leftIndx >= 1) {
      Node nextTop = query(1, 1, N, 1, leftIndx);
      sum += (leftIndx - nextTop.leftMaxIdx + 1) * nextTop.max;
      leftIndx = nextTop.leftMaxIdx - 1;
    }

    while(rightIndx <= N){
      Node nextTop = query(1, 1, N, rightIndx, N);
      sum += (nextTop.rightMaxIdx - rightIndx + 1) * nextTop.max;
      rightIndx = nextTop.rightMaxIdx + 1;
    }
  }

  static {
    for(int i = 0; i < tree.length; i++) {
      tree[i] = new Node(0, 0,0 );
    }
  }


  public void init(int N)
  {
    this.N = N;
    sum = 0;
    for(int i = 1; i <= N; i++) {
      update(1, 1, N, i, 0);
      loads[i] = 0;
    }
  }

  public int stock(int mLoc, int mBox)
  {
    loads[mLoc] += mBox;
    update(1, 1, N, mLoc, loads[mLoc]);
    Node leftMostTop = query(1, 1, N, 1, mLoc-1);
    Node rightMostTop = query(1, 1, N, mLoc+1, N);
    if(Math.min(leftMostTop.max, rightMostTop.max) >= loads[mLoc]) return sum;
    calc();
    return sum;
  }

  public int ship(int mLoc, int mBox)
  {
    Node leftMostTop = query(1, 1, N, 1, mLoc-1);
    Node rightMostTop = query(1, 1, N, mLoc+1, N);
    boolean shouldUpdate = true;
    if(Math.min(leftMostTop.max, rightMostTop.max) >= loads[mLoc]) shouldUpdate = false;
    loads[mLoc] -= mBox;
    update(1, 1, N, mLoc, loads[mLoc]);
    if(shouldUpdate) calc();
    return sum;
  }

  public int getHeight(int mLoc)
  {
    return loads[mLoc];
  }
}
