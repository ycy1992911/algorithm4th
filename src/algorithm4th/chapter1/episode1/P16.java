package algorithm4th.chapter1.episode1;

/**
 * 这段代码返回的结果是什么
 *
 * @author chongyang18@gmail.com
 * @date 21/01/2018
 */
public class P16 {
    private static String exR1(int n) {
        if (n <= 0) {
            return "";
        }
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    public static void main(String[] args) {
        // 结果胃：31111361142246
        System.out.println(exR1(6));
    }
}
