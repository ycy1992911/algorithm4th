package chapter1.episode5;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * quick-find算法，最坏情况下union(): O(n)  find(): O(n)
 * <p>
 * 遍历整个数组，将所有和id[p]相等的元素的值变为id[q]的值
 *
 * @author chongyang18@gmail.com
 * @date 26/01/2018
 */
public class E_2_1 {
    private static int[] id;
    private static int count;
    private static int cost;
    private static int total;
    private static int unionCount;
    private static int conCount;

    E_2_1(int N) {
        count = N;
        id = new int[N];

        // 初始化分量id数组
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1800);
        StdDraw.setPenRadius(.005);
    }

    public static void main(String[] args) throws IOException {
        // 读取文件
        File file = new File("/Users/mike/IdeaProjects/MyJavaSE/algs4thExtensions/algs4-data/mediumUF.txt");
        BufferedReader input = new BufferedReader(new FileReader(file));
        Scanner sc = new Scanner(input);

        // 获取元素个数
        int N = sc.nextInt();
        E_2_1 qf = new E_2_1(N);
        while (sc.hasNext()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            unionCount++;
            if (!qf.connected(p, q)) {
                qf.union(p, q);
                System.out.printf("链接：%d %d %n", p, q);
            }

            StdDraw.setPenColor(Color.green);
            StdDraw.point(unionCount, total/unionCount);
            StdDraw.setPenColor(Color.orange);
            StdDraw.point(unionCount, conCount);
            conCount = 0;
        }

        input.close();
        System.out.println(qf.count() + "components");
    }

    int count() {
        return count;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * p所在的分量的标识符
     *
     * @param p 触点p
     * @return
     */
    int find(int p) {
        cost++;
        total++;
        conCount++;
        return id[p];
    }

    void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            cost++;
            if (id[i] == pID) {
                id[i] = qID;
                cost += 2;
            }
        }
        StdDraw.setPenColor(Color.red);
        StdDraw.point(unionCount, cost);
        count--;
        total += cost;
        cost = 0;
    }


}
