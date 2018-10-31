package algorithm4th.sortingalgorithms;

/**
 * @author chongyang18@gmail.com
 * @date 07/03/2018
 */
public class BubbleSort {
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void bubble(int[] arr) {
        boolean isSorted = true;
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                if(arr[j] < arr[j - 1]) {
                    isSorted = false;
                    swap(arr, j, j-1);
                }
            }
            if(isSorted) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,3,1,3,5,6,3,2};
        bubble(arr);
        for (int i : arr) {
            System.out.print( i + " ");
        }
    }
}
