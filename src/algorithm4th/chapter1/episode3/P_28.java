package algorithm4th.chapter1.episode3;

/**
 * 用递归方法解答：编写一个方法max(), 接受一条链表的首节点作为参数，返回链表中键最大的节点的值。
 * 假设所有键均为正整数，如果链表为空则返回0
 *
 * @author chongyang18@gmail.com
 * @date 23/01/2018
 */
public class P_28 {
    Node head;
    private Node tail;
    private int size;

    P_28() {
        head = new Node();
        tail = head;
    }

    void add(int i) {
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

    class Node {
        int val;
        Node next;
    }
}

class P_28_Test {
    public static void main(String[] args) {
        P_28 p = new P_28();
        p.add(1);
        p.add(2);
        p.add(13);
        p.add(4);
        p.add(15);

        P_28.Node n = p.head;
        while (n != null) {
            System.out.print(n.val + " ");
            n = n.next;
        }

        System.out.println("\n" + max(p.head.val, p.head));
    }

    private static int max(int val, P_28.Node node) {
        // 空链表返回0
        if (node == null) {
            return 0;
        }

        // 截至条件
        if (node.next == null) {
            return node.val >= val ? node.val : val;
        }

        return max(node.val, node.next) >= val ? max(node.val, node.next) : val;
    }
}
