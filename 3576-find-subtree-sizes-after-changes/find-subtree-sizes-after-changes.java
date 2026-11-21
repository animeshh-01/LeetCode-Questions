import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> initialTree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            initialTree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            initialTree.get(parent[i]).add(i);
        }

        int[] finalParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        reassignParents(0, s, initialTree, finalParent, lastSeen);

        List<List<Integer>> finalTree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            finalTree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            finalTree.get(finalParent[i]).add(i);
        }

        int[] ans = new int[n];
        computeSubtreeSizes(0, finalTree, ans);
        return ans;
    }

    private void reassignParents(
            int node,
            String s,
            List<List<Integer>> initialTree,
            int[] finalParent,
            int[] lastSeen
    ) {
        int c = s.charAt(node) - 'a';
        if (node > 0 && lastSeen[c] != -1) {
            finalParent[node] = lastSeen[c];
        }

        int prevAncestor = lastSeen[c];
        lastSeen[c] = node;

        for (int child : initialTree.get(node)) {
            reassignParents(child, s, initialTree, finalParent, lastSeen);
        }

        lastSeen[c] = prevAncestor;
    }

    private int computeSubtreeSizes(int node, List<List<Integer>> finalTree, int[] ans) {
        int size = 1;
        for (int child : finalTree.get(node)) {
            size += computeSubtreeSizes(child, finalTree, ans);
        }
        ans[node] = size;
        return size;
    }
}