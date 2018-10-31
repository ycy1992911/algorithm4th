package algorithm4th.chapter1.episode5;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * union-find算法实现（加权quick-union，最坏情况下union(): O(lgN)  find(): O(lgN)
 * <p>
 * quick-union的链接相当于创建一个以根节点为根的树，不同的树构成了一个森林；但是树在归并的时候总是 p 的根往 q 的根，会出现大树往小树并，
 * 从而加深了单科树的深度。在极端情况下，一直往小树并，使时间复杂度变成2(1+2+3...+N) ~ N平方 （因为p和q都要查找，故是2倍）
 * <p>
 * union-find算法通过额外添加一个数组表示树的深度，从而保证了只有小树并向大树
 *
 * @author chongyang18@gmail.com
 * @date 28/01/2018
 */
public class E_2_6 {
    private static int[] id;
    private static int[] size;
    private static int count;
    private static int cost;
    private static int total;
    private static int unionCount;

    E_2_6(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            // 各个节点初始状态均为根节点
            id[i] = i;
        }
        size = new int[N];
        for (int i = 0; i < N; i++) {
            // 各个根节点初始深度为1
            size[i] = 1;
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
            total += cost;
        }
        return p;
    }

    private static void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot != qRoot) {
            // 根节点pRoot小于等于根节点qRoot，说明pRoot为小树（或两棵树一样大），将pRoot的分量指向qRoot完成合并
            if (size[pRoot] <= size[qRoot]) {
                id[pRoot] = qRoot;
                cost++;
                size[qRoot] += size[pRoot];
            } else {
                //根节点qRoot小于根节点pRoot，说明qRoot为小树，将qRoot的分量指向pRoot完成合并
                id[qRoot] = pRoot;
                cost++;
                size[pRoot] += size[qRoot];
            }
        }
        StdDraw.setPenColor(Color.red);
        StdDraw.point(unionCount, cost + 650);
        count--;
        total += cost;
        cost = 0;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/mike/IdeaProjects/MyJavaSE/algs4thExtensions/algs4-data/mediumUF.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        Scanner sc = new Scanner(br);
        int N = sc.nextInt();
        E_2_6 uf = new E_2_6(N);
        System.out.println(N);
        while (sc.hasNext()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            unionCount++;
            if (!connected(p, q)) {
                union(p, q);
                System.out.printf("链接：%d %d %n", p, q);
            }
            StdDraw.setPenColor(Color.green);
            StdDraw.point(unionCount, total/unionCount + 650);
        }

        sc.close();
        br.close();
        System.out.println(count() + "component");
    }
}
