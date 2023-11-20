/* *****************************************************************************
 *  Name: Weighted Edge API
 **************************************************************************** */

public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    // Constructor
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // Return either endpoint
    public int either() {
        return v;
    }

    // Return the endpoint that is not 'v'
    public int other(int o) {
        if (o == v) return w;
        return v;
    }

    public double weight() {
        return weight;
    }

    // Compare this edge to that edge
    public int compareTo(Edge that) {
        if (weight > that.weight)
            return 1;
        else if (weight < that.weight)
            return -1;
        else
            return 0;
    }

    public static void main(String[] args) {

    }
}
