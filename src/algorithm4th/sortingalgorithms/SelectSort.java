package algorithm4th.sortingalgorithms;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author chongyang18@gmail.com
 * @date 16/03/2018
 */
public class SelectSort {
    private static int[] arr = new int[]{3, 2, 1, 0, 44, 34, 11, 2, 11};

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void selection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
    }

    @Test
    public void test() {
        selection(arr);
        assertArrayEquals(new int[]{0, 1, 2, 2, 3, 11, 11, 34, 44}, arr);
    }
}
