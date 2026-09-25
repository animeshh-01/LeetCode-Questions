import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        // Stack to store groups of sets (for nested evaluation)
        Stack<List<Set<String>>> stack = new Stack<>();
        stack.push(new ArrayList<>());
        stack.peek().add(new HashSet<>());
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '{') {
                stack.push(new ArrayList<>());
                stack.peek().add(new HashSet<>());
            } else if (c == '}') {
                List<Set<String>> poppedGroup = stack.pop();
                Set<String> evaluatedGroup = evaluateGroup(poppedGroup);
                
                // Multiply/concatenate with the current active group
                List<Set<String>> currentGroup = stack.peek();
                Set<String> lastSet = currentGroup.get(currentGroup.size() - 1);
                
                Set<String> multiplied = cartesianProduct(lastSet, evaluatedGroup);
                currentGroup.set(currentGroup.size() - 1, multiplied);
            } else if (c == ',') {
                // Add a new set for the union inside the current group
                stack.peek().add(new HashSet<>());
            } else {
                // It's a single letter character
                List<Set<String>> currentGroup = stack.peek();
                Set<String> lastSet = currentGroup.get(currentGroup.size() - 1);
                
                Set<String> multiplied = cartesianProduct(lastSet, setOf(Character.toString(c)));
                currentGroup.set(currentGroup.size() - 1, multiplied);
            }
        }
        
        Set<String> finalSet = evaluateGroup(stack.pop());
        List<String> result = new ArrayList<>(finalSet);
        Collections.sort(result);
        return result;
    }
    
    // Evaluates the union of all sets in a group (comma-separated elements)
    private Set<String> evaluateGroup(List<Set<String>> group) {
        Set<String> union = new HashSet<>();
        for (Set<String> set : group) {
            union.addAll(set);
        }
        return union;
    }
    
    // Computes the Cartesian product of two sets for concatenation
    private Set<String> cartesianProduct(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>();
        if (set1.isEmpty()) {
            return set2;
        }
        if (set2.isEmpty()) {
            return set1;
        }
        for (String s1 : set1) {
            for (String s2 : set2) {
                result.add(s1 + s2);
            }
        }
        return result;
    }
    
    private Set<String> setOf(String s) {
        Set<String> set = new HashSet<>();
        set.add(s);
        return set;
    }
}