/* *****************************************************************************
 *  Name: Rabin karp fingerprint search
 *
 *  Details: modular hashing
 *  - compute a hash of pattern characters 0 to M - 1
 *  - for each 'i' compute a hash of text characters 'i' to M + i - 1
 *  - if pattern hash = text substring hash, check for a match.
 *
 **************************************************************************** */

public class RK {
    private long patHash; // pattern hash value
    private int R; // radix
    private int Q; // modulus
    private int M; // pattern length
    private long RM; // R^(M-1) % Q

    public RK(String pat) {
        M = pat.length();
        R = 256;
        // If Q is a sufficiently large random prime(about MN^2) then the
        // probability of a false collision is about 1/N, also make sure
        // Q is not so large to cause overflow.
        Q = 997; // longRandomPrime();

        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }

    public int search(String text) {
        int N = text.length();
        long textHash = hash(text, M);
        if (patHash == textHash) return 0;
        for (int i = M; i < N; i++) {
            textHash = (textHash + Q - (RM * text.charAt(i - M) % Q)) % Q;
            textHash = (textHash * R + text.charAt(i)) % Q;
            // check for substring match if hash match continue with search if it
            // was a false collision
            if (patHash == textHash) return i - M + 1;
        }
        return N;
    }

    public static void main(String[] args) {

    }
}
