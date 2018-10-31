package sortingalgorithms;

/**
 * 快排实现
 *
 * @author chongyang18@gmail.com
 * @date 05/03/2018
 */
public class QuickSort {
    private static void swap(int[] arr, int a , int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void quick(int[] arr, int lo, int hi) {
        if(lo >= hi) {
            return;
        }

        int p = partition(arr, lo, hi);
        quick(arr, lo, p - 1);
        quick(arr, p + 1, hi);
    }

    private static int partition(int[] arr, int lo, int hi) {
        int guard = arr[lo];
        int left = lo;
        int right = hi + 1;

        while(true) {
            while(arr[++left] < guard) {
                if(left >= right) {
                    break;
                }
            }
            while(arr[--right] > guard) {
                if(right <= left) {
                    break;
                }
            }
            if(right <= left) {
                break;
            }

            swap(arr, right, left);
        }
        swap(arr, lo, right);
        return right;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,6,8,3,2,1,99,66,55,33,11,3,6,9};
        quick(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
