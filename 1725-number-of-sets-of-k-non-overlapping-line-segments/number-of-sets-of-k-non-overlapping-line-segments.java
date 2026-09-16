class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        int total = n + k - 1;
        int r = 2 * k;
        
        if (total < r) return 0;
        
        // We need to compute C(total, r) % MOD
        return (int) combination(total, r, MOD);
    }
    
    private long combination(int n, int r, long mod) {
        if (r > n - r) {
            r = n - r;
        }
        long numerator = 1;
        long denominator = 1;
        
        for (int i = 0; i < r; i++) {
            numerator = (numerator * (n - i)) % mod;
            denominator = (denominator * (i + 1)) % mod;
        }
        
        // Fermat's Little Theorem for modular inverse
        long invDenominator = power(denominator, mod - 2, mod);
        return (numerator * invDenominator) % mod;
    }
    
    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}