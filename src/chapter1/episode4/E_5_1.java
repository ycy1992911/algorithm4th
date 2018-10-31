package chapter1.episode4;

import java.util.HashSet;

/**
 * 找出一个输入文件中所有和为0的整数对的数量
 * 时间复杂度O(n), 空间复杂度O(n)
 *
 * @author chongyang18@gmail.com
 * @date 24/01/2018
 */
public class E_5_1 {
    public static void main(String[] args) {
        E_5_1 e = new E_5_1();
        // 3对
        System.out.println(e.twoSumCount(new int[]{-1, 1, 0, 0, 3, 4, -5, 5}));
    }

    private int twoSumCount(int[] arr) {
        int count = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (!set.contains(0 - arr[i])) {
                set.add(arr[i]);
            } else {
                count++;
            }
        }

        return count;
    }
}
