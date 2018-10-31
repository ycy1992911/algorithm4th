package algorithm4th.chapter1.episode2;

/**
 * 判断串s是否为串t的回环变位（circular rotation），如ACTGACG就是TGACGAC的一个回环变位，反之亦然
 * <p>
 * 思路：用String类中的indexof()、length()、concat()方法
 * indexof() : 返回指定子字符串在此字符串中第一次出现处的索引
 * concat() : 将指定字符串连接到此字符串的结尾, 如果它不作为一个子字符串出现，则返回 -1
 *
 * @author chongyang18@gmail.com
 * @date 22/01/2018
 */
public class P6 {
    private static boolean isCirRotation(String s, String t) {
        return s.length() == t.length() && s.concat(s).indexOf(t) >= 0;
    }
}
