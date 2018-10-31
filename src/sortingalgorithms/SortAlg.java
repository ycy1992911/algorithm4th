package sortingalgorithms;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author chongyang18@gmail.com
 * @date 26/03/2018
 */
public class SortAlg {
    private static int[] arr = new int[10000];
    private static int[] res = new int[arr.length];
    private static int[] temp = new int[arr.length];

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 冒泡
     */
    private static void bubble(int[] arr) {
        boolean isSorted = true;
        for (int i = arr.length - 1; i > -1; i--) {
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j , j + 1);
                    isSorted = false;
                }
            }
            if(isSorted) {
                return;
            }
        }
    }

    /**
     * 插入
     */
    private static void insertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j ,j - 1);
            }
        }
    }

    /**
     * 选择
     */
    private static void selection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    /**
     * 希尔
     */
    private static void shell(int[] arr) {
        int len = arr.length;
        int p = 1;
        while (p < len / 3) {
            p = p * 3 + 1;
        }

        while (p > 0) {
            for (int i = p; i < len; i++) {
                for (int j = i; j >= p && arr[j] < arr[j - p]; j -= p) {
                    swap(arr, j, j - p);
                }
            }
            p = p / 3;
        }
    }

    /**
     * 快速
     */
    private static void quick(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivot = partition(arr, lo, hi);
        quick(arr, lo, pivot - 1);
        quick(arr, pivot + 1, hi);
    }

    /**
     * 快速切分点
     */
    private static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[lo];
        int left = lo;
        int right = hi + 1;
        while (true) {
            while (arr[++left] < pivot) {
                if (left == hi) {
                    break;
                }
            }
            while (arr[--right] > pivot) {
                if (right == lo) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            swap(arr, left, right);
        }
        swap(arr, lo, right);
        return right;
    }

    /**
     * 归并
     */
    private static void merge(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        merge(arr, lo, mid);
        merge(arr, mid + 1, hi);
        mergeImpl(arr, lo, mid, hi);
    }

    /**
     * 归并合并
     */
    private static void mergeImpl(int[] arr, int lo, int mid, int hi) {
        System.arraycopy(arr, lo, temp, lo, hi + 1 - lo);
        int left = lo;
        int right = mid + 1;

        for (int i = lo; i <= hi; i++) {
            if (left > mid) {
                arr[i] = temp[right++];
            } else if (right > hi) {
                arr[i] = temp[left++];
            } else if (temp[left] > temp[right]) {
                arr[i] = temp[right++];
            } else {
                arr[i] = temp[left++];
            }
        }
    }

    /**
     * 堆排
     */
    private static void heap(int[] arr) {
        int len = arr.length - 1;
        // 构建最大堆;
        for (int i = len / 2; i > -1; i--) {
            sink(arr, i, len);
        }
        while (len > -1) {
            swap(arr, 0, len--);
            sink(arr, 0, len);
        }
    }

    /**
     * 下沉
     */
    private static void sink(int[] arr, int k, int len) {
        while (2 * (k + 1) <= len) {
            int leaf = 2 * (k + 1) - 1;
            if (leaf < len && arr[leaf] < arr[leaf + 1]) {
                leaf = leaf + 1;
            }
            if (arr[k] > arr[leaf]) {
                break;
            }
            swap(arr, k, leaf);
            k = leaf;
        }
    }

    @Before
    public void initArr() {
        // 待排序数组，一百个大小在0-100之间的元素
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(1000);
        }
        // 排序后数组
        System.arraycopy(arr, 0, res, 0, arr.length);
        Arrays.sort(res);
    }

    @Test
    public void bubbleTest() {
        bubble(arr);
        assertArrayEquals(res, arr);
    }

    @Test
    public void insertionTest() {
        insertion(arr);
        assertArrayEquals(res, arr);
    }

    @Test
    public void selectionTest() {
        selection(arr);
        assertArrayEquals(res, arr);
    }

    @Test
    public void shellTest() {
        shell(arr);
        assertArrayEquals(res, arr);
    }

    @Test
    public void quickTest() {
        quick(arr, 0, arr.length - 1);
        assertArrayEquals(res, arr);
    }

    @Test
    public void mergeTest() {
        merge(arr, 0, arr.length - 1);
        assertArrayEquals(res, arr);
    }

    @Test
    public void heapTest() {
        heap(arr);
        assertArrayEquals(res, arr);
    }
}
