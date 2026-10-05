class Solution {
public:
    int scoreOfParentheses(string s) {
        stack<int> st;
        st.push(0); // Base score for the outer level
        
        for (char c : s) {
            if (c == '(') {
                st.push(0);
            } else {
                int v = st.top();
                st.pop();
                // If v is 0, it means we hit "()", so score is 1. 
                // Otherwise, it means we hit "(A)", so score is 2 * v.
                int innerScore = (v == 0) ? 1 : 2 * v;
                st.top() += innerScore;
            }
        }
        
        return st.top();
    }
};