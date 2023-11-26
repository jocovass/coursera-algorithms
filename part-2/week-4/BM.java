/* *****************************************************************************
 *  Name: Boyer Moore
 *
 *  Details:
 *  - scan chars. in pattern from right to left, can skip as manu as M text chars
 *    when finding on not in the pattern.
 *
 *  Analysis:
 *  - substring search with the Boyer Moore mismatched character heuristic
 *    takes about ~N/M(sublinear) character compares to search for a pattern of
 *    length M in a text of length N
 *
 **************************************************************************** */

public class BM {
    private String pat;
    private final int R = 256;
    private int[] right;
    private int M;

    public BM(String pat) {
        this.pat = pat;
        M = pat.length();
        right = new int[R];

        // initialize everything to -1, it means character 'c' does not exist
        // in the pattern
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }

        // update positions in 'right' to the position they appear in the pattern
        for (int j = 0; j < M; j++) {
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String text) {
        int N = text.length();
        int skip;
        for (int i = 0; i < N; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pat.charAt(j) != text.charAt(i + j)) {
                    skip = Math.max(1, j - right[text.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) return i; // found the match
        }
        return N; // no match found
    }

    public static void main(String[] args) {

    }
}
