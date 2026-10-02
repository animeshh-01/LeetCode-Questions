class Solution {
private:
    void backtrack(string current, int openCount, int closeCount, int n, vector<string>& result) {
        // Base case: if the string has reached the maximum length
        if (current.length() == 2 * n) {
            result.push_back(current);
            return;
        }
        
        // We can add an opening bracket if we haven't used all n of them
        if (openCount < n) {
            backtrack(current + "(", openCount + 1, closeCount, n, result);
        }
        
        // We can add a closing bracket if it won't exceed the number of open brackets
        if (closeCount < openCount) {
            backtrack(current + ")", openCount, closeCount + 1, n, result);
        }
    }

public:
    vector<string> generateParenthesis(int n) {
        vector<string> result;
        backtrack("", 0, 0, n, result);
        return result;
    }
};