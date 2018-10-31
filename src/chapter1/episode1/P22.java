package chapter1.episode1;

import java.util.Arrays;

/**
 * 使用递归方法实现BinarySearch并跟踪该方法的调用。每当该方法被调用时，打印出它的参数lo和hi并按照递归深度缩进
 *
 * @author chongyang18@gmail.com
 * @date 22/01/2018
 */
public class P22 {
    private static int[] arr = new int[]{1, 2, 3, 9, 12, 5, 7};

    private static int invoke(int[] arr, int key) {
        return recBinarySearch(arr, 0, arr.length - 1, key, 0);
    }

    private static int recBinarySearch(int[] arr, int lo, int hi, int key, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println("low: " + lo + ", high: " + hi);
        if (lo > hi) {
            return -1;
        }

        int mid = lo + (hi - lo) / 2;
        if (key > arr[mid]) {
            return recBinarySearch(arr, mid + 1, hi, key, depth + 1);
        } else if (key < arr[mid]) {
            return recBinarySearch(arr, lo, mid - 1, key, depth + 1);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        Arrays.sort(arr);
        // 测试
        if (invoke(arr, 5) != -1) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
    }
}
