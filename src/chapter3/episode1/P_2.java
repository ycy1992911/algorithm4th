package chapter3.episode1;

import org.jetbrains.annotations.NotNull;

/**
 * @author chongyang18@gmail.com
 * @date 20/02/2018
 */
public class P_2 {
    private class Entry {
        String key;
        Object value;

        Entry(@NotNull String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] arr;
    private int size;

    private P_2() {
        arr = new Entry[6];
        size = 0;
    }

    private void add(@NotNull String key, Object value) {
        for (int i = 0; i < size; i++) {
            if (key.equals(arr[i].key)) {
                arr[i].value = value;
                return;
            }
        }

        if (size == arr.length) {
            Entry[] newArr = new Entry[size * 2];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }

        Entry newEntry = new Entry(key, value);
        arr[size] = newEntry;
        size++;
    }

    private void sequentialAdd(String key, Object value) {
        int lo = 0;
        int hi = size - 1;
        int location = -1;

        // binary search
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid].key.compareTo(key) < 0) {
                lo = mid + 1;
            }else if(arr[mid].key.compareTo(key) > 0) {
                hi = mid - 1;
            }else{
                location = mid;
                break;
            }
        }

        // extend the capacity
        if (size == arr.length) {
            Entry[] newArr = new Entry[size * 2];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }

        // add entry
        if(location != -1) {
            arr[location].value = value;
        }else{
            System.arraycopy(arr, lo, arr, lo + 1, size - lo);
            arr[lo] = new Entry(key, value);
            size++;
        }
    }

    private void delete(@NotNull String key) {
        for (int i = 0; i < size; i++) {
            if (key.equals(arr[i].key)) {
                System.arraycopy(arr, i + 1, arr, i, size - i);
                size--;
                return;
            }
            if (i == size - 1 && !arr[i].key.equals(key)) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        P_2 p = new P_2();
        //p.add("1", 1);
        //p.add("2", 2);
        //p.add("1", 2);
        //p.add("3", 3);
        p.sequentialAdd("6", 3);
        p.sequentialAdd("2", 2);
        p.sequentialAdd("0", 5);
        p.sequentialAdd("5", 5);
        p.sequentialAdd("1", 1);
        p.sequentialAdd("4", 4);
        p.sequentialAdd("3", 3);
        //p.delete("2");

        for (int i = 0; i < p.size; i++) {
            System.out.println(p.arr[i].key);
        }
    }
}
