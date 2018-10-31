package algorithm4th.chapter2.episode4;

/**
 * Implementation of PriorityQueue by using binary heap（for convenience, all inputs are greater than 0）
 *
 * @author chongyang18@gmail.com
 * @date 07/02/2018
 */
public class E_4 {
    private static int[] arr = new int[3];
    private static int size;

    /**
     * add new element
     * @param value new element to add
     */
    private static void add(int value) {
        if(size == arr.length - 1) {
            int[] newArr = new int[arr.length * 2];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        arr[size + 1] = value;
        goUp(size + 1);
        size ++;
    }

    /**
     * remove greatest element
     */
    private static void del() {
        // exchange the greatest element with the last element
        arr[1] = arr[size];
        arr[size] = 0;
        size--;
        goDown();
    }

    /**
     * float up the new added element
     *
     * @param index new added element's position
     */
    private static void goUp(int index) {
        int k = index;
        while (k > 1 && arr[k] > arr[k / 2]) {
            int temp = arr[k];
            arr[k] = arr[k / 2];
            arr[k / 2] = temp;
            k = k / 2;
        }
    }

    /**
     * sink the value in arr[1] down after the greatest value is deleted
     */
    private static void goDown() {
        int j = 1;
        while(j <= size) {
            int k = 2 * j;
            // add k by one if the left leaf is less than the right one
            if(k < size && arr[k] < arr[k + 1]) {
                k++;
            }
            // stop the loop if the root is greater than the leaf
            if(arr[j] > arr[k]) {
                break;
            }

            int temp = arr[j];
            arr[j] = arr[k];
            arr[k] = temp;
            j = k;
        }
    }

    public static void main(String[] args) {
        System.out.print("Heap instantiation：");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        // 13 should be at arr[1]
        System.out.print("Heap after addition：");
        add(1);
        add(2);
        add(13);
        add(3);
        add(3);
        for (int i : arr) {
            if(i != 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        System.out.print("Heap after deletion：");
        del();
        for (int i : arr) {
            if(i != 0) {
                System.out.print(i + " ");
            }
        }
    }
}