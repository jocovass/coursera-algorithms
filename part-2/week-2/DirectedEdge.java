/* *****************************************************************************
 *  Name: Directed weighted edge
 **************************************************************************** */

public class DirectedEdge implements Comparable<DirectedEdge> {
    public final int from;
    public final int to;
    public final double weight;

    public DirectedEdge(int v, int w, int weight) {
        this.from = v;
        this.to = w;
        this.weight = weight;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public double weight() {
        return weight;
    }

    public int compareTo(DirectedEdge that) {
        if (this.weight > that.weight) {
            return 1;
        }
        else if (this.weight < that.weight) {
            return -1;
        }
        else return 0;
    }

    public static void main(String[] args) {

    }
}
