package chapter2.episode2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 归并有序的队列。编写一个静态方法，将两个有序的队列作为参数，返回一个归并后的有序队列
 *
 * @author chongyang18@gmail.com
 * @date 01/02/2018
 */
public class P_14 {
    private static Queue<Integer> merge(Queue q1, Queue q2) {
        Queue<Integer> q3 = new ArrayDeque<>();
        int aEle = (int) q1.poll();
        int bEle = (int) q2.poll();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (aEle > bEle) {
                q3.add(bEle);
                bEle = (int) q2.poll();
            } else {
                q3.add(aEle);
                aEle = (int) q1.poll();
            }
        }

        if (q1.isEmpty() && !q2.isEmpty()) {
            for (Object o : q2) {
                q3.add((Integer) o);
            }
        } else if (q2.isEmpty() && !q1.isEmpty()) {
            for (Object o : q1) {
                q3.add((Integer) o);
            }
        }

        return q3;
    }

    public static void main(String[] args) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            q1.add(i);
            q2.add(i * 2 - 2);
        }
        Queue q = merge(q1, q2);
        for (Object o : q) {
            System.out.print(o + " ");
        }
    }
}
