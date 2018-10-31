package chapter2.episode4;

/**
 * 堆排序
 * <p>
 * 堆排序主要由两部分，第一部分是将目标数组构建成最大堆（最小堆），然后通过下沉（上浮）来将数组排序。
 * 此处构建最大堆，从右向左下沉更为高效，我们就来实现一遍
 *
 * @author chongyang18@gmail.com
 * @date 07/02/2018
 */
public class E_5 {
    /**
     * build a heap with unused element at index 0
     *
     * @param arr source array to use
     * @return a new array with a size of <code>arr</code> + 1, and unused element at index 0
     */
    private static int[] buildHeap(int[] arr) {
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 1, arr.length);
        return newArr;
    }

    /**
     * make the heap a max heap
     *
     * @param arr the source heap
     * @return <code>newArr</code> max heap
     */
    private static int[] buildMaxHeap(int[] arr) {
        int[] newArr = buildHeap(arr);
        int size = newArr.length;

        // start from the middle which is efficient
        for (int i = size / 2; i >= 1; i--) {
            heapify(newArr, i, size);
        }

        return newArr;
    }

    /**
     * sort the heap
     *
     * @param arr arr to sort
     * @return <code>newArr</code> the sorted array
     */
    private static int[] sort(int[] arr) {
        // input check
        if(arr == null) {
            throw new NullPointerException();
        }
        if(arr.length == 0 || arr.length == 1) {
            return arr;
        }
        // create a new array with an used element at index 0
        int[] newArr = buildMaxHeap(arr);
        // the index of last element for each loop
        int index = newArr.length - 1;
        // sort by move the greatest element to the tail of array
        while (index > 1) {
            exchange(newArr, 1, index--);
            heapify(newArr, 1, index);
        }

        return newArr;
    }

    /**
     * heapify the heap which keeps the heap a max heap
     *
     * @param arr   the source heap
     * @param start the point to start heapify (include)
     * @param end   the point to end heapify (include)
     */
    private static void heapify(int[] arr, int start, int end) {
        while (2 * start < end) {
            int k = 2 * start;
            // if left leaf is smaller than the right leaf
            if (k < end && arr[k] < arr[k + 1]) {
                k++;
            }
            // if the node is greater than both sub-nodes
            if (arr[start] > arr[k]) {
                break;
            }
            // exchange
            exchange(arr, start, k);

            start = k;
        }
    }

    /**
     * swap two elements in an array
     *
     * @param arr the source array
     * @param a   index of the first element
     * @param b   index of the second element
     */
    private static void exchange(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 2, 1, 7, 3, 10, 44, 21};
        int[] newArr = sort(arr);
        for (int i : newArr) {
            System.out.print(i + " ");
        }
    }
}
