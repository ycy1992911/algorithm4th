package chapter1.episode3;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * 范型定容栈
 *
 * @author chongyang18@gmail.com
 * @date 23/01/2018
 */
public class E_2<E> implements Iterable<E> {
    private Object[] arr;
    private int size;

    E_2() {
        // 不支持范型数组，需强转
        arr = (E[]) new Object[20];
    }

    public static void main(String[] args) throws Exception {
        E_2 e = new E_2();
        e.push("1");
        e.push("2");
        e.push("3");
        System.out.println("size: " + e.size);
        System.out.println("\n=================\n");
        for (Object o : e) {
            System.out.print(o);
        }
        System.out.println("\n=================\n");
        System.out.print(e.pop());
        System.out.print(e.pop());
        System.out.println(e.pop());
        System.out.println("size: " + e.size);
    }

    private void push(E e) throws Exception {
        if (size <= arr.length) {
            arr[size] = e;
            size++;
        } else {
            throw new Exception();
        }
    }

    private E pop() throws Exception {
        if (size >= 0) {
            size--;
            return (E) arr[size];
        } else {
            throw new Exception();
        }
    }

    private E peek(int i) {
        return (E) arr[i];
    }

    @NotNull
    @Override
    public Iterator iterator() {
        return new Iterator() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                return peek(count++);
            }
        };
    }
}
