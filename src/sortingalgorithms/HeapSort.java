package sortingalgorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author chongyang18@gmail.com
 * @date 20/03/2018
 */
public class HeapSort {
    /**
     * 交换数组中两个元素位置
     *
     * @param arr 目标数组
     * @param fir 第一个元素
     * @param sec 第二个元素
     */
    private static void swap(int[] arr, int fir, int sec) {
        int temp = arr[fir];
        arr[fir] = arr[sec];
        arr[sec] = temp;
    }

    /**
     * 堆排序；构建最大堆，将0号元素移动到数组末尾；
     * 每次增量-1，继续构建最大堆，移动0号元素到数组倒数第二位，以此类推
     *
     * @param arr 带排序数组
     */
    private static void heapSort(int[] arr) {
        // 先验条件
        if (arr == null) {
            return;
        }

        int len = arr.length - 1;
        // 构建最大堆
        for (int i = len / 2; i > -1; i--) {
            sink(arr, i, len);
        }

        // 排序
        while (len > 1) {
            swap(arr, 0, len--);
            sink(arr, 0, len);
        }
    }

    /**
     * 元素下沉
     *
     * @param arr 带排序的数组
     * @param k   需下沉元素的位置
     * @param len 未排序部分长度
     */
    private static void sink(int[] arr, int k, int len) {
        while (2 * (k + 1) < len) {
            int leftChildIndex = (k + 1) * 2 - 1;
            if (arr[leftChildIndex] < arr[leftChildIndex + 1]) {
                leftChildIndex++;
            }
            if (arr[k] > arr[leftChildIndex]) {
                break;
            }
            swap(arr, k, leftChildIndex);
            k = leftChildIndex;
        }

    }

    @Test
    public void test() {
        // 待排序数组，十亿个大小在0-1000之间的元素
        final int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(1000);
        }
        // 排序后数组
        final int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        Arrays.sort(res);
        heapSort(arr);
        assertArrayEquals(res, arr);
    }
}
