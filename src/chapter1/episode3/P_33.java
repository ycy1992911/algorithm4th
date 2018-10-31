package chapter1.episode3;

import java.util.EmptyStackException;

/**
 * 实现双向队列
 *
 * @author chongyang18@gmail.com
 * @date 23/01/2018
 */
public class P_33 {
    private Node head;
    private Node tail;
    private int size;

    P_33() {
        this.head = new Node();
        this.tail = head;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int size() {
        return size;
    }

    void pushRight(int val) {
        if (size == 0) {
            head.val = val;
        } else {
            Node newTail = new Node();
            newTail.val = val;
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    void pushLeft(int val) {
        if (size == 0) {
            pushRight(val);
        } else {
            Node newHead = new Node();
            newHead.val = val;
            newHead.next = head;
            head = newHead;
        }
    }

    int popLeft() {
        int pop = -1;
        if (size == 0) {
            throw new EmptyStackException();
        } else if (size == 1) {
            head = null;
        } else {
            Node newHead = head.next;
            pop = head.val;
            head.next = null;
            head = newHead;
        }

        return pop;
    }

    int popRight() {
        int pop = -1;
        if (size == 0 || size == 1) {
            popLeft();
        } else {
            Node newTail = head;
            while (newTail.next.next != null) {
                newTail = newTail.next;
            }

            newTail.next = null;
            tail = newTail;
        }

        return pop;
    }

    private class Node {
        int val;
        Node next;
    }
}
