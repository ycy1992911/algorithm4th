package chapter1.episode5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 使用union-find（加权quick-union）算法处理序列9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2。、
 * 对于输入的每一对整数，给出id[]数组的内容和访问数组的次数
 *
 * @author chongyang18@gmail.com
 * @date 28/01/2018
 */
public class P_3 {
    private static int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static int[] sz = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private static int count;

    private static boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private static int find(int p) {
        count++;
        int root = p;
        while (root != arr[root]) {
            root = arr[root];
        }
        while (p != root) {
            int newP = arr[p];
            arr[p] = root;
            p = newP;
        }
        return root;
    }

    private static void union(int p, int q) {
        if (!connected(p, q)) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (sz[pRoot] < sz[qRoot]) {
                arr[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            } else {
                arr[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(new File(
                "/Users/mike/IdeaProjects/MyJavaSE/algs4thExtensions/algs4-data/miniUF.txt"))));
        System.out.println(arr.length);
        while (sc.hasNext()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.printf("%ncount: %d %n", count);
            count = 0;
            if (!connected(p, q)) {
                union(p, q);
            }
        }
    }
}
