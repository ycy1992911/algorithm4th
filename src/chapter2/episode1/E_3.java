package chapter2.episode1;

import java.util.Random;

/**
 * 插入排序 时间复杂度O(N^2) 空间复杂度O(1)
 *
 * 默认前面的都已经是有序，将当前元素与前面的元素比较并插入相应位置，其他元素右移
 *
 * @author chongyang18@gmail.com
 * @date 29/01/2018
 */
public class E_3 {
    static void insSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[200000];
        for (int i = 0; i < 200000; i++) {
            arr[i] = new Random().nextInt(1000);
        }
        Long start = System.currentTimeMillis();
        insSort(arr);
        for (int i : arr) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
