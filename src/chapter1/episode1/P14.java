package chapter1.episode1;

/**
 * 编写一个静态方法lg(), 接受一个整形参数N， 返回不大于Log2 N 的最大整数，不要使用Math库
 *
 * @author chongyang18@gmail.com
 * @date 21/01/2018
 */
public class P14 {
    /**
     * use recursion
     *
     * @param n an Integer
     * @return the power
     */
    private static int exponent(int n) {
        if (n < 1) {
            return -1;
        }

        if (n == 1) {
            return 0;
        }

        return 1 + exponent(n / 2);
    }

    public static void main(String[] args) {
        System.out.println(exponent(16));
    }
}
