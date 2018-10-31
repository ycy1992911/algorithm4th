package chapter3.episode4;

/**
 * 基于线性探测法的符号表实现
 *
 * @author chongyang18@gmail.com
 * @date 23/02/2018
 */
public class E_6<Key extends Comparable<Key>, Value> {
    private int size;
    private int capacity;
    private Key[] keys;
    private Value[] vals;

    public E_6() {
        size = 0;
        capacity = 16;
        keys = (Key[]) new Object[capacity];
        vals = (Value[]) new Object[capacity];
    }

    /**
     * 求哈希值
     *
     * @param key 键
     * @return 键的哈希值
     */
    private int hash(Key key) {
        return (key.hashCode() & 0xfffffff) % capacity;
    }

    /**
     * 扩容
     */
    private void resize() {
        Key[] newKey = (Key[]) new Object[capacity * 2];
        Value[] newVal = (Value[]) new Object[capacity * 2];
        System.arraycopy(keys, 0, newKey, 0, keys.length);
        keys = newKey;
        System.arraycopy(vals, 0, newVal, 0, vals.length);
        vals = newVal;
        capacity *= 2;
    }

    /**
     * 添加元素
     *
     * @param key 键
     * @param val 值
     */
    private void put(Key key, Value val) {
        if (size >= capacity / 2) {
            resize();
        }
        int hashCode = hash(key);
        for (int i = hashCode; keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
            keys[i] = key;
            vals[i] = val;
            size++;
        }

    }

    /**
     * 删除元素
     *
     * @param key 键
     */
    private void delete(Key key) {
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % capacity;
        }

        keys[i] = null;
        vals[i] = null;
        // 删除以后留下的空位会导致原本该键簇被空位分割后右边的元素无法被查到
        // 因此需要将右侧剩下的簇左移
        i = (i + 1) % capacity;
        while (keys[i] != null) {
            Key newKey = keys[i];
            Value newVal = vals[i];
            keys[i] = null;
            vals[i] = null;
            // 下面调用put方法size会增加，所以这里要减去
            size--;
            put(newKey, newVal);
            i = (i + 1) % capacity;
        }
        size--;
    }
}
