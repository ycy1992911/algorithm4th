package algorithm4th.chapter1.episode1;

/**
 * 给出使用欧几里得算法计算105和24的最大公约数( greatest common divisor )的过程中得到的一系列p和q值。扩展该算法中的代码得到一个程序Euclid，从命令行接受两个参数，
 * 计算它们的最大公约数并打印出每次调用递归方法时得两个参数。使用你的程序计算1 111 111 和 1 234 567的最大公约数。
 * <p>
 * 欧几里得算法：两个整数的最大公约数等于其中较小的数和两数的差的最大公约数
 *
 * @author chongyang18@gmail.com
 * @date 22/01/2018
 */
public class P24 {
    private static int getGCD(int a, int b) {
        if (a % b == 0) {
            System.out.println("Greatest Common Divisor is : " + a);
            return b;
        }

        // store the smaller one
        int p = a < b ? a : b;
        // store the gap
        int q = a < b ? b % a : a % b;
        // print p and q
        System.out.println("p: " + p + " q: " + q);

        return getGCD(p, q);
    }

    public static void main(String[] args) {
        getGCD(1111111, 1234567);
    }

}
