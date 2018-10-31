package algorithm4th.chapter1.episode3;

/**
 * 反转链表
 *
 * @author chongyang18@gmail.com
 * @date 23/01/2018
 */
public class P_30 {
    private Node head;
    private Node tail;
    private int size;

    P_30() {
        head = new Node();
        tail = head;
    }

    public static void main(String[] args) {
        P_30 p = new P_30();
        p.add(1);
        p.add(2);
        p.add(3);
        p.add(4);
        p.add(5);
        Node n = p.head;
        System.out.println("Size: " + p.size);
        while (n != null) {
            System.out.print(n.val + " ");
            n = n.next;
        }
        System.out.println("\n==============\n");
        Node m = p.reverseListRC(p.head);
        // 拷贝一份给下面的测试用例
        Node x = m;
        System.out.println("Size: " + p.size);
        while (m != null) {
            System.out.print(m.val + " ");
            m = m.next;
        }
        System.out.println("\n==============\n");
        Node t = p.reverseList(x);
        System.out.println("Size: " + p.size);
        while (t != null) {
            System.out.print(t.val + " ");
            t = t.next;
        }
    }

    private void add(int i) {
        if (size == 0) {
            head.val = i;
        } else {
            Node newTail = new Node();
            newTail.val = i;
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    /**
     * reverse the linked list by recursion
     *
     * @param head head of a linked list
     * @return head of reversed linked list
     */
    private Node reverseListRC(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node node = reverseListRC(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * reverse linked list in a normal way
     *
     * @param head head of linked list
     * @return head of reversed list
     */
    private Node reverseList(Node head) {
        Node fir = null;
        Node sec = head;

        while (sec != null) {
            Node thi = sec.next;
            sec.next = fir;
            fir = sec;
            sec = thi;
        }

        return fir;
    }

    private class Node {
        int val;
        Node next;
    }
}
