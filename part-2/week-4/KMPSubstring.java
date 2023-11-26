/* *****************************************************************************
 *  Name: Knuth Morris Pratt substring searching
 *
 *  Details:
 *   - need to precompute DFA from pattern
 *   - text pointer 'i' never decrements
 *
 *  DFA(Deterministic finite state automation)
 *   - finite number of states (including start and halt)
 *   - exactly one transition for each char in alphabet
 *   - accept if sequence of transitions leads to halt state
 *   - running time: "M" character access but space/time proportional to "RM"
 *
 *  How to build DFA from pattern?
 *   - if in state 'j' and next char c == path.charAt(j) got to j+1
 *   - if in state 'j' and nex char c != path.charAt(j) then the lat j-1 chars.
 *     of input are pat[1...j-1] followed by c.
 *
 *  Analysis: substring search accesses no more than M+N chars to search for a pattern
 *  of length M in a text of length N.
 **************************************************************************** */

public class KMPSubstring {
    private final int R = 256; // extended ASCII chars
    private String pat;
    private int M;
    private int[][] dfa;

    public KMPSubstring(String pat) {
        // save the pattern
        this.pat = pat;
        // length of the pattern
        M = pat.length();
        // initialize a tuple of length R/M where R is the length of the
        // characters we want to consider in our case is 256(ASCII chars) and M the
        // length of the pattern
        dfa = new int[R][M];
        // access the first character from the pattern and at position 0 assign 1
        // this essentially means if we find the same character in the string
        // we know that we have a match and the next character we want to match
        // is at position 1
        dfa[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < M; j++) {
            // For each state 'j' copy dfa[][x] to dfa[][j] for mismatch case

            // Loop through dfa[R] and update position j to the position saved in x for each char.
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][x];
            }

            // Set dfa[path.charAt(j)][j] to j+1 for match case
            dfa[pat.charAt(j)][j] = j + 1;

            // update 'x'
            // x stores the j position for each char in the pattern in case there was a mismatch
            // dfa[N] - is an array of integers representing the next position depending on
            // what character is the next in the 'text', where N is just every character we want
            // to consider in our search (could be only the english alphabet or any ASCII chars and so on).
            x = dfa[pat.charAt(j)][x];
        }
    }

    public int search(String text) {
        int i, j, N = text.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[text.charAt(i)][j];
        }
        if (j == M) return i - M;
        return N;
    }

    public static void main(String[] args) {

    }
}
