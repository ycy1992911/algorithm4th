package algorithm4th.chapter2.episode3;

import java.util.Random;

/**
 * 给出一段代码，将已知只有两种主键值的数组排序
 *
 * @author chongyang18@gmail.com
 * @date 04/02/2018
 */
public class P_5 {
    private static final int[] ARR = new int[]{2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void sort(int[] arr, int lo, int hi) {
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        int com = arr[lo];

        while (i <= gt) {
            if (arr[i] < com) {
                swap(arr, lt++, i++);
            } else if (arr[i] > com) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }
    }

    private static void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int dice = new Random().nextInt(arr.length - 1);
            swap(arr, i, dice);
        }
    }

    public static void main(String[] args) {
        shuffle(ARR);
        sort(ARR, 0, ARR.length - 1);
        for (int i : ARR) {
            System.out.print(i + " ");
        }
    }
}
