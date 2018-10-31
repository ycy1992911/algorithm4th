package algorithm4th.chapter1.episode3;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * 数据结构背包：一种不支持从中删除元素集合的数据类型，可重复，无序
 * <p>
 * 可变长数组实现
 *
 * @author chongyang18@gmail.com
 * @date 23/01/2018
 */
public class E_1_4 implements Iterable<Object> {

    private Object[] arr;
    private int size = 0;

    private E_1_4(int capacity) {
        arr = new Object[capacity];
    }

    private E_1_4() {
        arr = new Object[10];
    }

    public static void main(String[] args) {
        E_1_4 e = new E_1_4();
        e.add("1");
        e.add("1");
        e.add("1");
        e.add("1");
        System.out.println(e.size);
        System.out.println(e.get(0));
        System.out.println("\n ====================== \n");
        for (Object o : e) {
            System.out.println(o);
        }
    }

    private void add(Object o) {
        if (size < arr.length) {
            arr[size] = o;
        } else {
            Object[] copyArr = new Object[20];
            System.arraycopy(arr, 0, copyArr, 0, arr.length - 1);
            arr = copyArr;
        }
        size++;
    }

    private Object get(int i) {
        if (i <= size) {
            return arr[i];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * 匿名内部类实现迭代器
     *
     * @return an iterator
     */
    @NotNull
    @Override
    public Iterator<Object> iterator() {
        return new Iterator<>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count != size;
            }

            @Override
            public Object next() {
                return get(count++);
            }
        };
    }
}

/**
 * 链表实现背包
 */
class E_1_4_v2 {
    private Node first;
    private Node last;
    private int size = 0;

    private E_1_4_v2() {
        this.first = new Node();
        this.last = first;
    }

    public static void main(String[] args) {
        E_1_4_v2 e = new E_1_4_v2();
        e.add("1");
        e.add("1");
        e.add("1");
        e.add("1");
        System.out.println(e.size);

        // 遍历打印
        Node iter = new Node();
        iter.next = e.getFirst();
        while (iter.next != null) {
            System.out.println(iter.next.val);
            iter.next = iter.next.next;
        }
    }

    private void add(Object o) {
        if (size == 0) {
            first.val = o;
        } else {
            Node n = new Node(o, null);
            last.next = n;
            last = n;
        }
        size++;
    }

    private Node getFirst() {
        return first;
    }

    /**
     * 一定要静态内部类
     */
    private static class Node {
        Object val;
        Node next;

        Node() {
        }

        Node(Object val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}