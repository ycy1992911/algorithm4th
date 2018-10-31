package algorithm4th.chapter2.episode4;

/**
 * 用序列 P R I O * R * * I * T * Y * * * Q U E * * * U * E（字母表示插入元素，星号表示删除最大元素）
 * 操作一个初始为空的优先队列。给出每次删除最大元素返回的字符。
 *
 * @author chongyang18@gmail.com
 * @date 08/02/2018
 */
public class P_1 {
    /**
     * 为了方便，题目总共出现13个元素
     */
    private static final String[] arr = new String[14];
    private static int size = 0;

    /**
     * add element
     *
     * @param value string to add
     */
    private static void add(String value) {
        heapify(value, size + 1);
        size++;
    }

    /**
     * swim the value
     *
     * @param value the string to heapify
     */
    private static void heapify(String value, int pos) {
        arr[pos] = value;
        int k = pos;
        while (k / 2 >= 1) {
            if (arr[k].compareTo(arr[k / 2]) <= 1) {
                break;
            }

            swap(arr, k, k / 2);
            k = k / 2;
        }
    }

    /**
     * remove the greatest element
     */
    private static void del() {
        swap(arr, 1, size);
        arr[size] = null;
        size--;
        sink();
    }

    /**
     * sink the element to the appropriate position after deletion
     */
    private static void sink() {
        int pos = 1;
        while (2 * pos < size) {
            int k = 2 * pos;
            if (arr[k].compareTo(arr[k + 1]) < 0) {
                k++;
            }
            if (arr[pos].compareTo(arr[k]) > 0) {
                break;
            }
            swap(arr, pos, k);
            pos = k;
        }
    }

    /**
     * swap two elements
     *
     * @param arr source array
     * @param a   position of first element
     * @param b   position of second element
     */
    private static void swap(String[] arr, int a, int b) {
        String temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        add("P");
        add("R");
        add("I");
        add("O");
        del();
        add("R");
        del();
        del();
        add("I");
        del();
        add("T");
        del();
        add("Y");
        del();
        del();
        del();
        add("Q");
        add("U");
        add("E");
        del();
        del();
        del();
        add("U");
        del();
        add("E");

        System.out.println("Array size: " + size);
        System.out.print("Heap elements: ");
        for (String s : arr) {
            if (s != null) {
                System.out.print(s + " ");
            }
        }
        System.out.println();
        System.out.print("Array element: ");
        for (String s : arr) {
            System.out.print(s + " ");
        }
    }
}
