class Solution {
public:
    vector<int> maxDepthAfterSplit(string seq) {
        vector<int> result;
        int depth = 0;
        
        for (int i = 0; i < seq.length(); ++i) {
            if (seq[i] == '(') {
                // Alternate assignment based on the current depth parity
                result.push_back(depth % 2);
                depth++;
            } else {
                depth--;
                // Closing parenthesis matches the opening one's group
                result.push_back(depth % 2);
            }
        }
        
        return result;
    }
};