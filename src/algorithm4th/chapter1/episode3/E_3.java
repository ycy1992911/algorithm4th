package algorithm4th.chapter1.episode3;

import java.util.NoSuchElementException;

/**
 * 实现双向链表
 *
 * @author chongyang18@gmail.com
 * @date 23/01/2018
 */
public class E_3<E> {
    private Node head;
    private Node tail;
    private int size;
    E_3() {
        head = new Node();
        tail = head;
    }

    public static void main(String[] args) {
        E_3 e = new E_3();
        e.addToTail("1");
        e.addToTail("1");
        e.addToTail("2");
        e.addToTail("3");
        System.out.println("size: " + e.size);
        System.out.println("\n=====================\n");
        Node first = e.head;
        System.out.print("包含元素：");
        while (first != null) {
            System.out.print(first.ele + " ");
            first = first.next;
        }
        System.out.println("\n=====================\n");
        e.addToHead("4");
        Node secFirst = e.head;
        System.out.print("包含元素：");
        while (secFirst != null) {
            System.out.print(secFirst.ele + " ");
            secFirst = secFirst.next;
        }
        System.out.println("\n=====================\n");
        e.remove("1");
        Node thirFirst = e.head;
        System.out.print("包含元素：");
        while (thirFirst != null) {
            System.out.print(thirFirst.ele + " ");
            thirFirst = thirFirst.next;
        }
    }

    private void addToHead(E e) {
        if (size == 0) {
            head.ele = e;
        } else {
            Node newHead = new Node();
            newHead.ele = e;
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
        size++;
    }

    private void addToTail(E e) {
        if (size == 0) {
            addToHead(e);
        } else {
            tail.next = new Node();
            tail.next.ele = e;
            tail.next.prev = tail;
            tail = tail.next;
            size++;
        }
    }

    /**
     * remove the element at the tail
     *
     * @param e element to remove
     */
    private void remove(E e) {
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            head.ele = null;
            head.next = null;
        } else {
            Node cur = tail.prev;
            tail.prev = null;
            tail.ele = null;
            cur.next = null;
            tail = cur;
        }
        size--;
    }

    private static class Node<E> {
        E ele;
        Node next;
        Node prev;
    }
}
