package algorithm4th.chapter2.episode3;

import java.util.Random;

/**
 * 基本快速排序（partition只按大小切分），和归并排序一样，以大化小。
 * 不同点在于，和自顶向下的归并排序相比，基本快速排序的分割并不是取中位一分为二。
 *
 * 时间复杂度：O(NlogN) 空间复杂度：O(logN)
 *
 * @author chongyang18@gmail.com
 * @date 02/02/2018
 */
public class E_1 {
    /**
     * 测试用例
     */
    private static final int[] ARR1 = new int[]{11, 11, 1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
    private static final int[] ARR2 = new int[]{1};
    private static final int[] ARR3 = new int[10000];

    /**
     * 排序
     *
     * @param arr 待排序数组
     * @param lo  子数组的起始位
     * @param hi  子数组的截止位
     */
    private static void sort(int[] arr, int lo, int hi) {
        // 递归终止条件
        if (lo >= hi) {
            return;
        }
        // 计算partition（切分）点
        int partitionPoint = partition(arr, lo, hi);
        // 对切分点左半边排序
        sort(arr, lo, partitionPoint - 1);
        // 对切分点右半边排序
        sort(arr, partitionPoint + 1, hi);
    }

    /**
     * 确定partition（切分）点
     *
     * @param arr 待排序子数组
     * @param lo  子数组在整个数组中的起始位
     * @param hi  子数组在整个数组中的截止位
     * @return splitPoint 切分点
     */
    private static int partition(int[] arr, int lo, int hi) {
        // 挑选切分点为第一个元素
        int partitionValue = arr[lo];
        // 左右扫描指针
        int lt = lo;
        int gt = hi + 1;
        // 设置一个循环进行从两头往中间遍历
        while (true) {
            // 从右侧遍历，直到找到一个比切分点大的停下
            while (partitionValue > arr[++lt]) {
                if (lt == hi) {
                    break;
                }
            }
            // 从左侧遍历，直到找到一个比切分点小的停下
            while (partitionValue < arr[--gt]) {
                if (gt == lo) {
                    break;
                }
            }
            // 若遍历完成，则退出循环
            if (lt >= gt) {
                break;
            }
            // 一般情况，当lt与gt都停下时，进行交换（将大的调到右边，小的调到左边）
            int temp = arr[lt];
            arr[lt] = arr[gt];
            arr[gt] = temp;
        }
        // 最后，将在第一位的切分点调到切分点应该在的位置, 即gt最后停下的位置
        int tempPartitionPoint = arr[gt];
        arr[gt] = arr[lo];
        arr[lo] = tempPartitionPoint;
        // 返回切分点
        return gt;
    }

    /**
     * 打乱数组顺序
     *
     * @param arr 待打乱数组
     */
    private static void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = new Random().nextInt(arr.length - 1);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < ARR3.length; i++) {
            ARR3[i] = new Random().nextInt(2000);
        }
        // 打乱数组顺序，防止选择最大或最小的数作为切分点
        shuffle(ARR3);
        // 排序
        sort(ARR3, 0, ARR3.length - 1);
        // 打印
        for (int i : ARR3) {
            System.out.print(i + " ");
        }
    }
}
