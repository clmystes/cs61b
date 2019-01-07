package lab11.graphs;

import java.util.LinkedList;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private LinkedList<Integer> fringeQ = new LinkedList<>();

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        fringeQ.addLast(s);

        while (!fringeQ.isEmpty()) {
            int current = fringeQ.removeFirst();
            marked[current] = true;

            if (s == t) {
                announce();
                return;
            }

            for (int v : maze.adj(current)) {
                if (!marked[v]) {
                    edgeTo[v] = current;
                    fringeQ.addLast(v);
                }
                distTo[v] = distTo[current] + 1;
            }
            announce();
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

