package algorithm4th.chapter1.episode5;

import edu.princeton.cs.algs4.StdDraw;

import java.io.*;
import java.util.Scanner;

/**
 * 使用quick-find算法处理序列9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2。对于输入的每一对整数，给出id[]数组的内容和访问数组的次数
 *
 * @author chongyang18@gmail.com
 * @date 28/01/2018
 */
public class P_1 {
    private static int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static int count;

    private static boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private static int find(int p) {
        count++;
        return arr[p];
    }

    private static void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if(pID == qID) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == pID) {
                arr[i] = qID;
            }
        }

        /*
        // 这样写是错的，因为p不会变，当i=p时，arr[i]和arr[p]时数组中同一个对象，对arr[i]赋值arr[q],则相当于改变了arr[p]的值
        if (!connected(p, q)) {
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] == arr[p]) {
                    arr[i] = arr[q];
                }
            }
        }
        */
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(new File(
                "/Users/mike/IdeaProjects/MyJavaSE/algs4thExtensions/algs4-data/miniUF.txt"))));
        System.out.println(arr.length);

        while(sc.hasNext()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.printf("%ncount: %d %n", count);
            count = 0;
            if(!connected(p, q)) {
                union(p, q);
            }
        }
    }
}
