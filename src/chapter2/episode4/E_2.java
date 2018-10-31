package chapter2.episode4;

/**
 * 优先队列的数组（无序）实现，为了方便假设所有输入均大于零
 * <p>
 * 利用一个栈，在栈中添加一个内循环，将最大值调到栈顶（类似于选择排序的实现）
 *
 * @author chongyang18@gmail.com
 * @date 07/02/2018
 */
public class E_2 {
    private static int[] ARR = new int[3];
    private static int size = 0;

    /**
     * add an element at the end of array
     * @param value element to add
     */
    private static void add(int value) {
        if (size < ARR.length - 1) {
            ARR[size + 1] = value;
            sort(ARR);
        } else {
            // double the capacity when array is full
            int[] arr = new int[ARR.length * 2];
            System.arraycopy(ARR, 0, arr, 0, ARR.length);
            arr[size + 1] = value;
            ARR = arr;
            sort(ARR);
        }
        size++;
    }

    /**
     * pop and delete the max value
     */
    private static void del() {
        for (int i = 1; i < ARR.length; i++) {
            ARR[i - 1] = ARR[i];
        }
        ARR[ARR.length - 1] = -1;
        sort(ARR);
        size -- ;
    }

    /**
     * find and swap the max value to the head of array
     *
     * @param arr array to sort
     */
    private static void sort(int[] arr) {
        int maxIndex = 0;
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }

        // swap
        int temp = arr[0];
        arr[0] = arr[maxIndex];
        arr[maxIndex] = temp;
    }

    public static void main(String[] args) {
        // add four elements
        add(3);
        add(2);
        add(12);
        add(1);
        // head is 12
        for (int integer : ARR) {
            System.out.print(integer + " ");
        }
        System.out.println("\n -----------------------");
        // delete the top two max value
        del();
        del();
        // head is 2
        for (int integer : ARR) {
            System.out.print(integer + " ");
        }
    }
}
