package algorithm4th.chapter2.episode3;

import java.util.Random;

/**
 * 三项切分的快速排序
 *
 * 由于基础快排分为大小两边，对于处理重复元素不够友好。
 * 三项切分的快排加入了对重复元素的处理，在时间复杂度上有所提升
 *
 * @author chongyang18@gmail.com
 * @date 03/02/2018
 */
public class E_3_2 {
    /**
     * 测试用例
     */
    private static final int[] ARR1 = new int[]{11, 11, 1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
    private static final int[] ARR2 = new int[]{1};
    private static final int[] ARR3 = new int[]{};

    /**
     * 三项切分快排具体实现
     * @param arr 需排序数组
     * @param lo 最低位
     * @param hi 最高位
     */
    private static void sort(int[] arr, int lo, int hi) {
        // 递归截止条件
        if(lo >= hi) {
            return;
        }

        // 起始位
        int lt = lo;
        // 与第一位做对比
        int com = arr[lo];
        // 游标
        int index = lo + 1;
        // 最高位
        int gt = hi;

        // 小于时，将index位置元素移到最左边，指针右移
        // 等于时，仅右移index
        // 大于时，将index移到最右边，hi指针左移
        while(index <= gt) {
            if(arr[index] < com) {
                swap(arr, lt++, index++);
            }else if(arr[index] > com) {
                swap(arr, index, gt--);
            }else{
                index++;
            }
        }

        // while循环结束以后，arr[lo...lt-1] < com = arr[lt...gt] < arr[gt+1..hi]成立
        // 通过递归，继续对两边的元素进行排列
        sort(arr, lo, lt - 1);
        sort(arr, gt + 1, hi);
    }

    /**
     * 交换元素位置
     * @param arr 元素所在数组
     * @param a 需交换的第一个元素索引
     * @param b 需交换的第二个元素索引
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
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
        // 打乱数组顺序，防止选择最大或最小的数作为切分点
        shuffle(ARR1);
        // 排序
        sort(ARR1, 0, ARR1.length - 1);
        // 打印
        for (int i : ARR1) {
            System.out.print(i + " ");
        }
    }
}
