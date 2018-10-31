package algorithm4th.chapter3.episode4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * HashMap （散列表法实现符号表）
 *
 * @author chongyang18@gmail.com
 * @date 23/02/2018
 */
public class E_5<Key extends Comparable, Value> {
    /**
     * 节点
     */
    private class Node<Key, Value> {
        private Key key;
        private Value val;
        private Node<Key, Value> next;

        public Node(Key key, Value val, Node<Key, Value> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private static final double LOAD_FACTOR = 0.75;
    private static final int MAX_CAPACITY = 128;
    private static int capacity = 16;
    private int size;
    private int threshold;
    private Node<Key, Value>[] table;

    /**
     * 默认无参构造器
     */
    E_5() {
        this(capacity);
    }

    /**
     * 有参构造器
     *
     * @param capacity 表的大小
     */
    private E_5(int capacity) {
        table = (Node<Key, Value>[]) new Node[capacity];
        size = 0;
        threshold = (int) (capacity * LOAD_FACTOR);
    }

    /**
     * 计算哈希值，高位与地位异或，增加低位的随机性
     *
     * @param key 键
     * @return 哈希值
     */
    private int hash(Key key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 获取在表中的位置
     *
     * @param key 键
     * @return 位置
     */
    private int indexFor(Key key, int capacity) {
        return (capacity - 1) & hash(key);
    }

    /**
     * 扩容2倍
     */
    private void resize() {
        // 旧表容量
        int oldTableLength = table.length;
        int newTableLength = oldTableLength << 1;
        // 创建新表并赋予2倍长度
        Node<Key, Value>[] newTable = (Node<Key, Value>[]) new Node[newTableLength];
        // 更新阈值
        threshold = (int) (newTableLength * LOAD_FACTOR);
        // 更新容量
        capacity = newTableLength;
        // 拷贝旧表元素
        transfer(newTable);
        // 引用指向新表
        table = newTable;
    }

    /**
     * 转移旧数据到新表
     *
     * @param newTable 新表
     */
    private void transfer(Node<Key, Value>[] newTable) {
        Node<Key, Value>[] oldTable = table;
        // 旧表容量
        int oldTableLength = oldTable.length;
        // 新表容量
        int newTableLength = newTable.length;
        // 遍历旧表
        for (int i = 0; i < oldTableLength; i++) {
            // 遍历bucket
            Node<Key, Value> head = oldTable[i];
            while (head != null) {
                Node<Key, Value> next = head.next;
                // 添加入新表
                put(head.key, head.val, newTableLength, newTable);

                head = next;
                // 释放空间
                oldTable[i] = null;
            }
        }

    }

    /**
     * 对外的添加元素API
     *
     * @param key 键
     * @param val 值
     */
    void put(Key key, Value val) {
        put(key, val, capacity, table);
    }

    /**
     * 添加新元素
     *
     * @param key         键
     * @param value       值
     * @param tableLength 元素位置
     * @param table 加入的表
     */
    private void put(Key key, Value value, int tableLength, Node<Key, Value>[] table) {
        // 判断是否扩容
        if (size >= threshold && capacity < MAX_CAPACITY) {
            resize();

        }
        //计算位置索引
        int index = indexFor(key, tableLength);
        Node<Key, Value> head = table[index];
        // 空bucket
        if (head == null) {
            table[index] = new Node<>(key, value, null);
        } else {
            // 非空bucket
            // 若只有一个元素且与需插入元素相等
            if(head.key.equals(key)) {
                head.val = value;
                return;
            }
            // 多于一个元素
            while(head.next != null) {
                if(head.key.equals(key)) {
                    head.val = value;
                    break;
                }
                head = head.next;
            }
            head.next = new Node<>(key, value,null);
        }
        size++;
    }

    /**
     * @return 元素数量
     */
    int getSize() {
        return size;
    }

    /**
     * @return 表的容量
     */
    int getCapacity() {
        return capacity;
    }

    /**
     * @return 表的阈值
     */
    int getThreshold() {
        return threshold;
    }

    /**
     * 打印整个HashMap
     */
    void print() {
        List<List<Node<Key, Value>>> list = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Node<Key, Value> head = table[i];
            List<Node<Key, Value>> bucket = new LinkedList<>();
            while (head != null) {
                bucket.add(head);
                head = head.next;
            }
            list.add(bucket);
        }

        for (List<Node<Key, Value>> nodeList : list) {
            System.out.print("[");
            for (Node<Key, Value> node : nodeList) {
                System.out.print("(");
                System.out.print(node.key);
                System.out.print(":");
                System.out.print(node.val);
                if(node.next != null) {
                    System.out.print(")  ");
                }else{
                    System.out.print(")");
                }
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        E_5 hashMap = new E_5();
        for (int i = 0; i < 300; i++) {
            hashMap.put(String.valueOf(i)+"haha", String.valueOf(new Random().nextInt(600)));
            hashMap.put("lol"+String.valueOf(300-i), new Random().nextInt(600));

        }
        hashMap.print();
    }
}
