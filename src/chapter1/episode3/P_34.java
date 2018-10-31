package chapter1.episode3;

import java.util.Random;

/**
 * 随机背包
 *
 * @author chongyang18@gmail.com
 * @date 23/01/2018
 */
public class P_34 {
    private int size;
    private Object[] arr;

    private P_34() {
        arr = new Object[10];
    }

    public static void main(String[] args) {
        P_34 p = new P_34();
        for (int i = 0; i < 1000; i++) {
            p.add(i);
        }
        System.out.println(p.get());
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private int size() {
        return size;
    }

    private void add(int val) {
        if (size < arr.length) {
            arr[size] = val;
        } else {
            Object[] copyArr = new Object[arr.length * 2];
            System.arraycopy(arr, 0, copyArr, 0, arr.length);
            arr = copyArr;
        }
        size++;
    }

    private Object get() {
        Random random = new Random();
        return arr[random.nextInt(size)];
    }
}
