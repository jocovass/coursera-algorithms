/* *****************************************************************************
 *  Name: Single source shortest path
 *
 *  Finds the shortest path from s to every other vertex.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;

public class SingleSourceShortestPath {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public boolean hasPathTo(int v) {
        return edgeTo[v] != null;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        // edgeTo[v] at position v we are going to store the vertex
        // we came from
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        DirectedEdge e = edgeTo[v];
        while (e != null) {
            path.push(e);
            e = edgeTo[e.from()];
        }
        return path;
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public static void main(String[] args) {

    }
}
