class Solution {
public:
    int m, n;
    int memo[100][100][201]; // max length of path is ~200, offset balance if needed or since start balance is 0 up to m+n

    bool dfs(int r, int c, int balance, const vector<vector<char>>& grid) {
        // Out of bounds or invalid balance
        if (r >= m || c >= n || balance < 0) return false;

        // Update balance for the current cell
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // If balance drops below 0 after updating
        if (balance < 0) return false;

        // If we reached the bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Check memoization table
        if (memo[r][c][balance] != -1) {
            return memo[r][c][balance];
        }

        // Move down or right
        bool res = dfs(r + 1, c, balance, grid) || dfs(r, c + 1, balance, grid);

        return memo[r][c][balance] = res;
    }

    bool hasValidPath(vector<vector<char>>& grid) {
        m = grid.size();
        n = grid[0].size();

        // Quick check: total length must be even
        if ((m + n - 1) % 2 != 0) return false;

        memset(memo, -1, sizeof(memo));
        return dfs(0, 0, 0, grid);
    }
};