class Solution {
    class Node {
        char leftChar, rightChar;
        int pref, suff, max;

        Node(char c) {
            leftChar = c;
            rightChar = c;
            pref = 1;
            suff = 1;
            max = 1;
        }

        Node(char leftChar, char rightChar, int pref, int suff, int max) {
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.pref = pref;
            this.suff = suff;
            this.max = max;
        }
    }

    private Node[] tree;
    private char[] chars;

    private Node merge(Node left, Node right) {
        char lc = left.leftChar;
        char rc = right.rightChar;
        int p = left.pref;
        int s = right.suff;
        int m = Math.max(left.max, right.max);

        // If the boundary characters match, combine their repeating segments
        if (left.rightChar == right.leftChar) {
            if (left.pref == (end(left) - start(left) + 1)) { // Entire left node is same char
                p += right.pref;
            }
            if (right.suff == (end(right) - start(right) + 1)) { // Entire right node is same char
                s += left.suff;
            }
            m = Math.max(m, left.suff + right.pref);
        }

        return new Node(lc, rc, p, s, m);
    }

    // Helper dummy values for tracking segment boundaries during merge
    private int start(Node n) { return 0; }
    private int end(Node n) { return 0; }

    // Actual segment tree implementation with index tracking
    class SegmentTree {
        int n;
        char[] s;
        Node[] tree;

        SegmentTree(String str) {
            s = str.toCharArray();
            n = s.length;
            tree = new Node[4 * n];
            build(1, 0, n - 1);
        }

        private void build(int node, int start, int end) {
            if (start == end) {
                tree[node] = new Node(s[start]);
                return;
            }
            int mid = (start + end) / 2;
            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);
            tree[node] = combine(tree[2 * node], tree[2 * node + 1], start, mid, end);
        }

        private Node combine(Node left, Node right, int start, int mid, int end) {
            char lc = left.leftChar;
            char rc = right.rightChar;
            int p = left.pref;
            int sCount = right.suff;
            int m = Math.max(left.max, right.max);

            if (left.rightChar == right.leftChar) {
                if (left.pref == (mid - start + 1)) {
                    p += right.pref;
                }
                if (right.suff == (end - mid)) {
                    sCount += left.suff;
                }
                m = Math.max(m, left.suff + right.pref);
            }
            return new Node(lc, rc, p, sCount, m);
        }

        public void update(int node, int start, int end, int idx, char c) {
            if (start == end) {
                s[idx] = c;
                tree[node] = new Node(c);
                return;
            }
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2 * node, start, mid, idx, c);
            } else {
                update(2 * node + 1, mid + 1, end, idx, c);
            }
            tree[node] = combine(tree[2 * node], tree[2 * node + 1], start, mid, end);
        }

        public int queryMax() {
            return tree[1].max;
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int k = queryIndices.length;
        int[] result = new int[k];
        SegmentTree st = new SegmentTree(s);

        for (int i = 0; i < k; i++) {
            st.update(1, 0, s.length() - 1, queryIndices[i], queryCharacters.charAt(i));
            result[i] = st.queryMax();
        }

        return result;
    }
}