package chapter1.episode1;

/**
 * 编写一个递归的静态方法计算ln(N!)的最大整数值
 *
 * @author chongyang18@gmail.com
 * @date 21/01/2018
 */
public class P20 {

    /**
     * calculate the factorial of n
     *
     * @param n an integer
     * @return the factorial of n
     */
    private static double factorial(int n) {
        if (n > 1) {
            return Math.log(n) * factorial(n - 1);
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
}
