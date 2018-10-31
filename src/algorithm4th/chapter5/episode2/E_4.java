package algorithm4th.chapter5.episode2;

/**
 * 基于单词查找树的符号表
 *
 * @author chongyang18@gmail.com
 * @date 05/03/2018
 */
public class E_4 {
    private static int R = 256;
    private static Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    private static int getSize(Node node) {
        if (node == null) {
            return 0;
        }

        int size = 0;
        if (node.val != null) {
            size++;
        }
        for (char i = 0; i < R; i++) {
            size += getSize(node.next[i]);
        }
        return size;
    }

    private static void put(String key, Object val) {
        root = put(root, key, val, 0);
    }

    private static Node put(Node x, String key, Object val, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.val = val;
            return x;
        }

        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    private static Object get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    private static Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

}
