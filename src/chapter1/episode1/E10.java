package chapter1.episode1;

import java.util.Arrays;

/**
 * 二分查找的普通实现和递归实现
 *
 * @author chongyang18@gmail.com
 * @date 22/01/2018
 */
public class E10 {
    private static int[] arr = new int[]{10, 2, 3, 4, 15, 5};

    /**
     * implementation of binary search
     *
     * @param arr array to search in
     * @param n   key to search
     * @return index of the key
     */
    private static int binarySearch(int[] arr, int n) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (n < arr[mid]) {
                hi = mid - 1;
            } else if (n > arr[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     * implement by recursion
     *
     * @param arr array to search in
     * @param lo  low index
     * @param hi  high index
     * @param key key to search
     * @return the index of key
     */
    private static int recBinarySearch(int[] arr, int lo, int hi, int key) {
        if (lo > hi) {
            return -1;
        }

        int mid = lo + (hi - lo) / 2;
        if (key > arr[mid]) {
            return recBinarySearch(arr, mid + 1, hi, key);
        } else if (key < arr[mid]) {
            return recBinarySearch(arr, lo, mid - 1, key);
        } else {
            return mid;
        }
    }

    private static int invokeRBS(int[] arr, int key) {
        return recBinarySearch(arr, 0, arr.length - 1, key);
    }

    public static void main(String[] args) {
        // 数组必须升序
        Arrays.sort(arr);

        // 第一个测试
        if (binarySearch(arr, 5) != -1) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
        //第二个测试
        if (invokeRBS(arr, 5) != -1) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }


    }
}
