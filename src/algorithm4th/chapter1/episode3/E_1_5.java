package algorithm4th.chapter1.episode3;

/**
 * 先进先出队列：基于先进先出策略的集合类型
 * <p>
 * 基于数组列表实现
 *
 * @author chongyang18@gmail.com
 * @date 23/01/2018
 */
public class E_1_5 {
    private Object[] arr;
    private int size;

    private E_1_5() {
        arr = new Object[10];
    }

    public static void main(String[] args) {
        E_1_5 e = new E_1_5();
        e.push("1");
        e.push("2");
        e.push("3");
        System.out.println(e.size);
        System.out.println("\n================== \n");
        e.poll();
        System.out.println(e.size);
    }

    private void push(Object o) {
        if (size < arr.length) {
            arr[size] = o;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

        size++;
    }

    private Object poll() {
        Object ele = null;
        if (size >= 0) {
            ele = arr[0];
            System.arraycopy(arr, 1, arr, 0, arr.length - 1);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        size--;
        return ele;
    }

}

/**
 * 链表实现队列
 */
class E_1_5_v2 {
    private Node first;
    private Node last;
    private int size;
    E_1_5_v2() {
        first = new Node();
        last = first;
    }

    public static void main(String[] args) throws Exception {
        E_1_5_v2 e = new E_1_5_v2();
        e.push("1");
        e.push("2");
        e.push("3");
        System.out.println("size: " + e.size);
        System.out.println("\n================\n");
        System.out.println("value: " + e.poll().val);
        System.out.println("size: " + e.size);
    }

    private void push(Object o) {
        if (size == 0) {
            first.val = o;
        } else {
            Node node = new Node();
            last.val = o;
            last.next = node;
            last = node;
        }
        size++;
    }

    private Node poll() throws Exception {
        Node node = first;
        if (size == 0) {
            throw new Exception("empty queue");
        } else {
            first = first.next;
        }
        return node;
    }

    private static class Node {
        Node next;
        Object val;

        Node() {
        }

        Node(Object val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
