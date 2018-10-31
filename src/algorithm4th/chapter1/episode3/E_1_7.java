package algorithm4th.chapter1.episode3;

import java.util.Scanner;
import java.util.Stack;

/**
 * Dijkstra 算数表达式求值
 * <p>
 * 1、将操作数压入操作数栈
 * 2、将运算符压入运算符栈
 * 3、忽略左括号
 * 4、遇到右括号时，弹出一个运算符，弹出所需数量的操作数，并将运算符和操作数的运算结果压入操作数栈
 *
 * @author chongyang18@gmail.com
 * @date 22/01/2018
 */
public class E_1_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while (!sc.hasNext("quit")) {
            // 输入需要以空格隔开
            String s = sc.next();
            if (s.equals("(")) {

            } else if (s.equals("+")) {
                ops.push(s);
            } else if (s.equals("-")) {
                ops.push(s);
            } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals("/")) {
                ops.push(s);
            } else if (s.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) {
                    v = vals.pop() + v;
                } else if (op.equals("-")) {
                    v = vals.pop() - v;
                } else if (op.equals("*")) {
                    v = vals.pop() * v;
                } else if (op.equals("/")) {
                    v = vals.pop() / v;
                }
                vals.push(v);
            } else {
                vals.push(Double.parseDouble(s));
            }
        }
        System.out.println(vals.pop());
    }
}
