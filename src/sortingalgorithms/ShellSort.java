package sortingalgorithms;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author chongyang18@gmail.com
 * @date 16/03/2018
 */
public class ShellSort {
    private static final int[] arr = new int[]{9, 7, 5, 3, 1, 8, 6, 4, 2};

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void shell(int[] arr) {
        int p = 1;
        while (p <= arr.length / 3) {
            p = p * 3 + 1;
        }

        while(p > 0) {
            for (int i = p; i < arr.length; i++) {
                for (int j = i; j >= p && j < arr.length && arr[j] < arr[j - p]; j -= p) {
                    swap(arr, j, j - p);
                }
            }
            p /= 3;
        }
    }

    @Test
    public void test() {
        shell(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
    }
}
