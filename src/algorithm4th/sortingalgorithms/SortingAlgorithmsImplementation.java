package algorithm4th.sortingalgorithms;

import java.util.Random;

/**
 * @author chongyang18@gmail.com
 * @date 14/02/2018
 */
class SortingAlgorithmsImplementation {

    private int[] aux = new int[Test.ARR.length];

    private void swap(int[] arr, int a, int b) {
        // swap
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    void bubble(int[] arr) {
        for (int i = arr.length - 1; i > -1; i--) {
            int max = i;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }
            swap(arr, i, max);
        }
    }

    void selection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
    }

    void insertion(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    void shell(int[] arr) {
        int p = 1;
        while (p < arr.length / 3) {
            p = p * 3 + 1;
        }

        while (p > 0) {
            for (int i = p; i < arr.length; i++) {
                for (int j = i; j >= p && j < arr.length; j -= p) {
                    if (arr[j] < arr[j - p]) {
                        swap(arr, j, j - p);
                    }
                }
            }
            p /= 3;
        }
    }

    void merge(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        merge(arr, lo, mid);
        merge(arr, mid + 1, hi);
        mergeSort(arr, lo, mid, hi);
    }

    private void mergeSort(int[] arr, int lo, int mid, int hi) {
        System.arraycopy(arr, lo, aux, lo, hi + 1 - lo);
        int left = lo;
        int right = mid + 1;

        for (int i = lo; i < hi + 1; i++) {
            if (left > mid) {
                arr[i] = aux[right++];
            } else if (right > hi) {
                arr[i] = aux[left++];
            } else if (aux[left] > aux[right]) {
                arr[i] = arr[right++];
            } else {
                arr[i] = arr[left++];
            }
        }
    }

    private int quickPartition(int[] arr, int lo, int hi) {
        int guard = arr[lo];
        int left = lo;
        int right = hi + 1;

        while (true) {
            while (arr[--right] > guard) {
                if (right == left) {
                    break;
                }
            }
            while (arr[++left] < guard) {
                if (left == right) {
                    break;
                }
            }

            if (left >= right) {
                break;
            }

            swap(arr, right, left);
        }
        swap(arr, lo, right);
        return right;
    }

    void quick(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int p = quickPartition(arr, lo, hi);
        quick(arr, lo, p - 1);
        quick(arr, p + 1, hi);
    }

    void heap(int[] arr) {
        int len = arr.length - 1;
        for (int i = len/2; i > -1; i--) {
            sink(arr, i, len);
        }
        while(len > 1) {
            swap(arr, 0, len--);
            sink(arr, 0, len);
        }
    }

    private void swim(int[] arr, int k) {
        while (k > -1) {
            int rootIndex = k / 2;
            if (arr[k] > arr[rootIndex]) {
                swap(arr, k, rootIndex);
            }
            k = rootIndex;
        }
    }

    private void sink(int[] arr, int k, int len) {
        while (2 * k <= len) {
            int leftChildIndex = k * 2;
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
}

class Test {
    /**
     * test case
     */
    static final int[] ARR = new int[1000];
    //static final int[] ARR = new int[]{2, 4, 6, 8, 10, 1, 3, 5, 7, 9};

    private static SortingAlgorithmsImplementation test = new SortingAlgorithmsImplementation();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            ARR[i] = new Random().nextInt(200);
        }
        long t1 = System.currentTimeMillis();
        //test.bubble(ARR);
        //test.selection(ARR);
        //test.insertion(ARR);
        //test.shell(ARR);
        //test.merge(ARR, 0, ARR.length - 1);
        //test.quick(ARR, 0, ARR.length - 1);
        //test.heap(ARR);
        long t2 = System.currentTimeMillis();
        System.out.println("Time: " + (t2 - t1));
        boolean isSorted = true;
        for (int i = 1; i < ARR.length; i++) {
            if (ARR[i] < ARR[i - 1]) {
                isSorted = false;
            }
        }
        System.out.println("Sorted: " + isSorted);
    }
}
