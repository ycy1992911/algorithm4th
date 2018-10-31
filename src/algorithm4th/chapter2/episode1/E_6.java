package algorithm4th.chapter2.episode1;

import java.util.Random;

/**
 * 希尔排序 时间复杂度：和增量n有关，总是小于 O(N^2) 空间复杂度：O(1)
 * <p>
 * 插入排序的改进，将一个数组分为n个：
 * A B C D E F G H I J K  (n = 3, 所以为3组)
 * A-----D-----G-----J
 * B-----E-----H-----K
 * C-----F-----I
 * 对这3组分别排序，减小n，重复排序，直到n = 1，此时就是基本的插入排序
 *
 * @author chongyang18@gmail.com
 * @date 29/01/2018
 */
public class E_6 {
    static void shellSort(int[] arr) {
        int n = 1;
        while (n < arr.length / 3) {
            n = n * 3 + 1;
        }
        while (n >= 1) {
            for (int i = 0; i < arr.length; i++) {
                // 按照插入排序的逻辑，从后往前插入
                for (int j = i; j >= n && arr[j] < arr[j-n]; j -= n) {
                    int temp = arr[j];
                    arr[j] = arr[j - n];
                    arr[j - n] = temp;
                }
            }
            n /= 3;
        }
    }

    public static void main(String[] args) {
        // 初始化2千万个数字
        int[] arr = new int[20000000];
        for (int i = 0; i < 20000000; i++) {
            arr[i] = new Random().nextInt(10000);
        }
        Long start = System.currentTimeMillis();
        shellSort(arr);
        Long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start)/1000 + "s");

    }
}
