package sortingalgorithms;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author chongyang18@gmail.com
 * @date 16/03/2018
 */
public class InsertionSort {
    private static final int[] arr = new int[]{9, 7, 5, 3, 1, 8, 6, 4, 2};

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void insert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    @Test
    public void test() {
        insert(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
    }
}
