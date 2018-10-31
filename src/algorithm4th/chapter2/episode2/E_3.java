package algorithm4th.chapter2.episode2;

/**
 * 自底向上的归并排序
 * <p>
 * 小的合成大的，从1开始，与其他的合并
 *
 * @author chongyang18@gmail.com
 * @date 01/02/2018
 */
public class E_3 {
    /**
     * test case
     */
    private static final int[] ARR = new int[]{1, 11, 3, 5, 7, 9, 2, 4, 6, 8, 10};
    /**
     * temporary array
     */
    private static int[] temp;

    /**
     * 从小到大在不同颗粒度上进行排序
     *
     * @param arr 待排序数组
     */
    private static void sort(int[] arr) {
        int size = arr.length;
        temp = new int[size];

        // 颗粒度，此处i不用等于size，因为等于size时相当于就是整个数组，无需合并
        for (int i = 1; i < size; i += i) {
            // 根据上面的颗粒度，来决定合并的次数
            // 对于奇数对数组，最后一个元素需在做merge排序合并时，一起带进去
            // merge的低中高分别为:j, j-1+i, j-1+i+i（或size - 1），其中因为mid是在j的基础上加一个i，当遍历到最后一次循环时，
            // 若剩余元素少于i个，则mid已经越界，因此，外层的for循环最大值应该减掉一个i，即：小于size - i
            // 对于hi来说，取j-1+i+i和size-1中较小的一个
            for (int j = 0; j < size - i; j += i + i) {
                merge(arr, j, j - 1 + i, Math.min(j - 1 + i + i, size - 1));
            }
        }
    }

    /**
     * 原地归并的抽象方法，同自顶向下
     *
     * @param arr 目标数组
     * @param lo 起始位
     * @param mid 中间位
     * @param hi 结束位
     */
    private static void merge(int[] arr, int lo, int mid, int hi) {
        int a = lo;
        int b = mid + 1;

        System.arraycopy(arr, lo, temp, lo, hi + 1 - lo);

        // 合并
        for (int j = lo; j <= hi; j++) {
            if (a > mid) {
                arr[j] = temp[b++];
            } else if (b > hi) {
                arr[j] = temp[a++];
            } else if (temp[b] < temp[a]) {
                arr[j] = temp[b++];
            } else {
                arr[j] = temp[a++];
            }
        }
    }

    public static void main(String[] args) {
        sort(ARR);
        for (int i : ARR) {
            System.out.print(i + " ");
        }
    }
}
