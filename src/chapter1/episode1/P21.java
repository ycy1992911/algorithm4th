package chapter1.episode1;

import java.util.Scanner;

/**
 * 从标准输入按行读取数据，其中每行都包含一个名字和两个整数。然后用printf()打印一张表格，每行的若干列数据包括名字、两个整数和
 * 第一个整数除以第二个整数的结果，精确到小数点后三位。
 *
 * @author chongyang18@gmail.com
 * @date 21/01/2018
 */
public class P21 {
    public static void main(String[] args) {
        // read line
        Scanner sc = new Scanner(System.in);
        String lineOne = sc.nextLine();
        String[] arrOne = lineOne.split(" ");
        String lineTwo = sc.nextLine();
        String[] arrTwo = lineTwo.split(" ");
        String lineThree = sc.nextLine();
        String[] arrThree = lineThree.split(" ");

        System.out.println("Name  " + "First  " + "Second  " + "Third");
        for (String s : arrOne) {
            System.out.printf(" " + s + "    ");
        }
        System.out.printf("%.3f", Double.valueOf(arrOne[1]) / Double.valueOf(arrOne[2]));
        System.out.println();
        for (String s : arrTwo) {
            System.out.printf(" " + s + "    ");
        }
        System.out.printf("%.3f", Double.valueOf(arrTwo[1]) / Double.valueOf(arrTwo[2]));
        System.out.println();
        for (String s : arrThree) {
            System.out.printf(" " + s + "    ");
        }
        System.out.printf("%.3f", Double.valueOf(arrThree[1]) / Double.valueOf(arrThree[2]));
    }


}
