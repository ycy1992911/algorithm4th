package chapter5.episode1;

import java.util.Random;

/**
 * 低位优先字符串排序
 *
 * @author chongyang18@gmail.com
 * @date 25/02/2018
 */
public class E_1 {
    /**
     * sort array by first x characters
     *
     * @param arr          source array
     * @param firstXDigits first x characters
     */
    private static void sort(String[] arr, int firstXDigits) {
        // 256 according to ASCII
        int countLen = 256;
        int len = arr.length;
        String[] aux = new String[len];
        // 从右往左从上往下
        for (int i = firstXDigits - 1; i > -1; i--) {
            int[] count = new int[countLen + 1];
            // 创建计数表
            for (int j = 0; j < len; j++) {
                count[arr[j].charAt(i) + 1]++;
            }
            // 更新计数表为索引表
            for (int k = 0; k < countLen; k++) {
                count[k+1] += count[k];
            }
            // 查找索引表写入aux
            for (int l = 0; l < len; l++) {
                aux[count[arr[l].charAt(i)]++] = arr[l];
            }
            // 写入原数组
            for (int m = 0; m < len; m++) {
                arr[m] = aux[m];
            }
        }
    }

    public static void main(String[] args) {
        String[] s =  new String[10];
        // filling the array with string made up by ten random numbers
        for (int i = 0; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                sb.append(new Random().nextInt(5));
            }
            s[i] = sb.toString();
        }

        sort(s, 5);
        for (String s1 : s) {
            System.out.println(s1);
        }
    }
}
