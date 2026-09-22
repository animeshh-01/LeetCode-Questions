import java.util.List;
import java.util.ArrayList;

class Node {
    int[] remain = new int[5];
    int prod = 1;
}

class SegmentTree {
    private int n;
    private int k;
    private Node[] tree;

    public SegmentTree(List<Integer> nums, int k) {
        this.n = nums.size();
        this.k = k;
        this.tree = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node();
        }
        build(nums, 0, 0, n - 1);
    }

    private Node merge(Node left, Node right) {
        Node node = new Node();
        node.prod = (left.prod * right.prod) % k;
        for (int i = 0; i < k; ++i) {
            node.remain[i] = left.remain[i];
        }
        for (int i = 0; i < k; ++i) {
            node.remain[(i * left.prod) % k] += right.remain[i];
        }
        return node;
    }

    private void build(List<Integer> nums, int cur, int left, int right) {
        if (left == right) {
            tree[cur].remain[nums.get(left)] = 1;
            tree[cur].prod = nums.get(left);
            return;
        }
        int mid = (left + right) / 2;
        build(nums, 2 * cur + 1, left, mid);
        build(nums, 2 * cur + 2, mid + 1, right);
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private void update(int treeIndex, int lo, int hi, int i, int val) {
        if (lo == hi) {
            for (int j = 0; j < k; ++j) {
                tree[treeIndex].remain[j] = 0;
            }
            tree[treeIndex].remain[val] = 1;
            tree[treeIndex].prod = val;
            return;
        }
        int mid = (lo + hi) / 2;
        if (i <= mid) {
            update(2 * treeIndex + 1, lo, mid, i, val);
        } else {
            update(2 * treeIndex + 2, mid + 1, hi, i, val);
        }
        tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    private Node query(int treeIndex, int lo, int hi, int i, int j) {
        if (i <= lo && hi <= j) {
            return tree[treeIndex];
        }
        int mid = (lo + hi) / 2;
        if (j <= mid) {
            return query(2 * treeIndex + 1, lo, mid, i, j);
        }
        if (i > mid) {
            return query(2 * treeIndex + 2, mid + 1, hi, i, j);
        }
        return merge(query(2 * treeIndex + 1, lo, mid, i, mid),
                     query(2 * treeIndex + 2, mid + 1, hi, mid + 1, j));
    }

    public void update(int i, int val) {
        update(0, 0, n - 1, i, val);
    }

    public Node query(int i, int j) {
        return query(0, 0, n - 1, i, j);
    }
}

class Solution {
    public int[] resultArray(int[] numsArr, int k, int[][] queries) {
        List<Integer> nums = new ArrayList<>();
        for (int num : numsArr) {
            nums.add(num % k);
        }
        int n = nums.size();
        SegmentTree tree = new SegmentTree(nums, k);
        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1] % k;
            int start = queries[q][2];
            int x = queries[q][3];

            tree.update(index, value);
            ans[q] = tree.query(start, n - 1).remain[x];
        }

        return ans;
    }
}