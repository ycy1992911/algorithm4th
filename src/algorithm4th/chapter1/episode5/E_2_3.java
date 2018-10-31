package algorithm4th.chapter1.episode5;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * quick-union算法，最坏情况下union(): O(树的高度)  find(): O(树的高度)
 * <p>
 * 提高quick-find算法的速度，将全部改为需连通的触点的分量的思路变成：每个以连通分量为索引的触点内包含下一个已经连接的触点的索引，
 * 相当于创建了一个指针。判断两个触点是否连通时，跟随第一个触点跳到它指向的下一个触点，直到有一个触点指向自己（必然存在），
 * 当且仅当分别由这两个开始的这个过程到达同一个根触点时，他们存在于同一个连通分量中
 *
 * @author chongyang18@gmail.com
 * @date 28/01/2018
 */
@SuppressWarnings("All")
public class E_2_3 {
    private static int[] id;
    private static int count;
    private static int cost;
    private static int unionCount;
    private static int total;

    E_2_3(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        StdDraw.setXscale(0, 900);
        StdDraw.setYscale(0, 1300);
        StdDraw.setPenRadius(.005);
    }

    private static int count() {
        return count;
    }

    private static boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private static int find(int p) {
        while (p != id[p]) {
            p = id[p];
            cost++;
        }
        StdDraw.setPenColor(Color.red);
        StdDraw.point(unionCount, cost + 650);
        total += cost;
        return p;
    }

    private static void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot != qRoot) {
            id[pRoot] = qRoot;
            cost++;
        }
        count --;
        total += cost;
        cost = 0;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/mike/IdeaProjects/MyJavaSE/algs4thExtensions/algs4-data/mediumUF.txt");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        Scanner sc = new Scanner(bf);
        int N = sc.nextInt();
        System.out.println(N);
        E_2_3 qu = new E_2_3(N);
        while(sc.hasNext()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            unionCount++;
            if(!connected(p, q)){
                union(p, q);
                System.out.printf("链接：%d %d %n", p, q);
            }
            StdDraw.setPenColor(Color.green);
            StdDraw.point(unionCount, total/unionCount + 650);
        }

        sc.close();
        bf.close();
        System.out.println(count() + "components");
    }
}
