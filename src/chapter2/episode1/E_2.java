package chapter2.episode1;

import edu.princeton.cs.algs4.StdDraw;

/**
 * 选择排序 时间复杂度O(N^2) 空间复杂度O(1)
 * <p>
 * 外层一个循环，逐层递减，选出最小的放在当前层最前
 *
 * @author chongyang18@gmail.com
 * @date 29/01/2018
 */
public class E_2 {
    static void selSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 9, 5, 2, 1, 5, 7};
        selSort(arr);
        for (int i : arr) {
            System.out.printf("%d ", i);
        }
    }
}