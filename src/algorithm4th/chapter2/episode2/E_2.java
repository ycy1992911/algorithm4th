package algorithm4th.chapter2.episode2;

/**
 * 自顶向下的归并排序 时间复杂度：O(NlgN) 空间复杂度：O(N)
 *
 * 分治思想，大的分成小的
 * @author chongyang18@gmail.com
 * @date 30/01/2018
 */
public class E_2 {
    /**
     * 测试用例
     */
    private static final int[] ARR = new int[]{3, 2, 1, 5, 7, 4, 6, 22, 8, 88};
    /**
     * 临时数组
     */
    private static int[] temp = new int[ARR.length];

    /**
     * 原地归并的抽象方法
     *
     * @param arr 需要合并的数组（其中包含两个已经排好序的数组，由mid区分）
     * @param lo  最低位
     * @param mid 中间喂
     * @param hi  最高位
     */
    private static void merge(int[] arr, int lo, int mid, int hi) {
        int a = lo;
        int b = mid + 1;
        for (int j = lo; j <= hi; j++) {
            temp[j] = arr[j];
        }

        // 总共对比次数一定与数组元素相同
        for (int i = lo; i <= hi; i++) {
            // 当a或b都代表相应部分的最后一个元素来作比较时，当一方较小并被写入目标数组时，另一个元素此时做自增时会超出范围
            // 会出现a > mid 或 b > hi的情况，这代表另一半已经没有元素，此时应将剩余的那一半全部写入目标数组
            if (a > mid) {
                arr[i] = temp[b++];
            } else if (b > hi) {
                arr[i] = temp[a++];
            } else if (temp[b] < temp[a]) {
                arr[i] = temp[b++];
            } else {
                arr[i] = temp[a++];
            }
        }
    }

    /**
     * 递归入口，排序
     *
     * @param arr 待排序数组
     * @param lo 起始位
     * @param hi 结束位
     */
    private static void sort(int[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        // 对左半部排序
        sort(arr, lo, mid);
        // 对右半部排序
        sort(arr, mid + 1, hi);
        // 归并左右
        merge(arr, lo, mid, hi);
    }

    public static void main(String[] args) {
        sort(ARR, 0, ARR.length - 1);
        for (int i : ARR) {
            System.out.print(i + " ");
        }
    }
}
