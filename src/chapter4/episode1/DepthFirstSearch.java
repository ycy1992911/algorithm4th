package chapter4.episode1;

import edu.princeton.cs.algs4.In;

import java.io.File;
import java.util.Stack;

/**
 * @author chongyang18@gmail.com
 * @date 28/02/2018
 */
public class DepthFirstSearch {
    // 起点
    private final int s;
    private boolean[] marked;
    private int[] edgeTo;

    DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    void dfs(Graph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if(!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }
        }
    }

    boolean hasPathTo(int v) {
        return marked[v];
    }

    Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        int s = 0;
        Graph G = new Graph(new In(new File("/Users/mike/IdeaProjects/MyJavaSE/algs4thExtensions/algs4-data/tinyG.txt")));
        DepthFirstSearch dfs = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            System.out.print(s + " to " + v + ": ");
            if(dfs.hasPathTo(v)) {
                for (int x : dfs.pathTo(v)) {
                    if(x == s) {
                        System.out.print(x);
                    }else{
                        System.out.print("-" + x);
                    }
                }
            }
            System.out.println();
        }
    }
}
