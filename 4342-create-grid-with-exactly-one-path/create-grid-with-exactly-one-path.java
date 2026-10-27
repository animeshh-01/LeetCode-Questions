class Solution {
    public String[] createGrid(int m, int n) {
        StringBuilder[] grid = new StringBuilder[m];
        
        // Initialize all rows with '#' (obstacles)
        for (int i = 0; i < m; i++) {
            grid[i] = new StringBuilder();
            for (int j = 0; j < n; j++) {
                grid[i].append('#');
            }
        }
        
        // Open the top row completely (move right)
        for (int j = 0; j < n; j++) {
            grid[0].setCharAt(j, '.');
        }
        
        // Open the last column completely (move down)
        for (int i = 0; i < m; i++) {
            grid[i].setCharAt(n - 1, '.');
        }
        
        // Convert StringBuilder array to String array
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = grid[i].toString();
        }
        
        return result;
    }
}