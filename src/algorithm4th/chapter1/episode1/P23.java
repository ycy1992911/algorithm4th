package algorithm4th.chapter1.episode1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 为BinarySearch测试用例添加一个参数：+ 打印出标准输入中不再白名单上的值；- 打印出标准输入中在白名单上的值
 *
 * @author chongyang18@gmail.com
 * @date 22/01/2018
 */
public class P23 {
    private static int[] arr = new int[]{11, 12, 13, 9, 4, 5};

    @SuppressWarnings("Duplicates")
    private static int binarySearch(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] < key) {
                lo = mid + 1;
            } else if (arr[mid] > key) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String exclude = "+";
        String include = "-";

        Arrays.sort(arr);
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int res = binarySearch(arr, 5);

        if (exclude.equals(s)) {
            if (res != -1) {
                for (int i = 0; i < arr.length; i++) {
                    if (i != res) {
                        System.out.print(arr[i] + " ");
                    }
                }
            } else {
                for (int i : arr) {
                    System.out.print(arr[i] + " ");
                }
            }
        } else if (include.equals(s)) {
            System.out.println(arr[res]);
        }
    }
}
