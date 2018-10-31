package algorithm4th.chapter4.episode1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * @author chongyang18@gmail.com
 * @date 28/02/2018
 */
public class Graph {
    // 定点数
    private final int V;
    // 边的数目
    private int E;
    // 邻接表
    private Bag<Integer>[] adj;

    Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for(int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    int V() {
        return V;
    }

    int E() {
        return E;
    }

    Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
