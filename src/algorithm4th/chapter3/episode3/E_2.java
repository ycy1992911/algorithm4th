package algorithm4th.chapter3.episode3;

/**
 * 红黑树（由2-3树演化而来）的实现
 * 1.红链接均为左链接
 * 2.没有任何一个节点同时和两条红链接相连
 * 3.该树是完美黑色平衡的（任意空链接到根节点的路径上的黑链接数量相同）
 *
 * @author chongyang18@gmail.com
 * @date 22/02/2018
 */
public class E_2<Key extends Comparable<Key>, Value> {
    /**
     * 树的节点
     */
    private class Node {
        /**
         * 左子节点
         */
        private Node left;
        /**
         * 右字节点
         */
        private Node right;
        /**
         * 节点颜色
         */
        private boolean colour;
        /**
         * 以当前节点为根节点的树所包含的节点总数
         */
        private int size;
        /**
         * 节点中键值的键
         */
        private Key key;
        /**
         * 节点中键值对的值
         */
        private Value value;


        public Node(boolean colour, int size, Key key, Value value) {
            this.left = left;
            this.right = right;
            this.colour = colour;
            this.size = size;
            this.key = key;
            this.value = value;
        }

    }

    /**
     * 红色键
     */
    private static final boolean RED = true;
    /**
     * 黑色键
     */
    private static final boolean BLACK = false;
    /**
     * 根节点
     */
    private Node root;
    /**
     * 树的大小
     */
    private int size = 0;

    /**
     * 左旋转
     *
     * @param node （子）树的根节点，该节点右子节点为红
     * @return 新的根节点
     */
    private Node rotateLeft(Node node) {
        Node newNode = node.right;
        //右节点左旋转
        node.right = newNode.left;
        newNode.left = node;
        //更新新节点颜色
        newNode.colour = node.colour;
        //更新旧节点颜色
        node.colour = RED;
        //更新新节点的大小
        newNode.size = node.size;
        //更新旧节点的大小
        node.size = node.left.size + node.right.size + 1;
        //返回新根节点
        return newNode;
    }

    /**
     * 右旋转
     *
     * @param node （子）树的根节点，该节点左子节点为红
     * @return 新的根节点
     */
    private Node rotateRight(Node node) {
        Node newNode = node.left;
        //左节点右旋转
        node.left = newNode.right;
        newNode.right = node;
        //更新新节点颜色
        newNode.colour = node.colour;
        //更新旧节点颜色
        node.colour = RED;
        //更新新节点大小
        newNode.size = node.size;
        //更新旧节点大小
        node.size = node.left.size + node.right.size + 1;
        //返回新根节点
        return newNode;
    }

    /**
     * 变色。
     * 将目标节点变红，两个子节点变黑
     *
     * @param node 目标节点
     */
    private void flipColour(Node node) {
        node.colour = RED;
        node.right.colour = BLACK;
        node.left.colour = BLACK;
    }

    /**
     * 节点是否为红
     *
     * @param node 目标节点
     * @return 是否为红色节点
     */
    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.colour == RED;
    }

    /**
     * @return 返回树的大小
     */
    private int getSize() {
        return size;
    }

    /**
     * 添加方法的对外API
     *
     * @param key   需添加的键
     * @param value 需添加的值
     */
    void put(Key key, Value value) {
        // 遍历树并添加
        put(root, key, value);
        // 在具体put()实现中用的是递归，所以新建节点为红色；
        // 若树初始为空，则得到红色根节点，与红黑树定义不符，故手动设为黑色
        root.colour = BLACK;
    }

    /**
     * 添加方法的具体实现
     *
     * @param node  开始的节点
     * @param key   需添加的键
     * @param value 需添加的值
     */
    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            // 递归实现，每次添加总是默认添加红色节点，后期再通过方法调整相应节点颜色
            return new Node(RED, 1, key, value);
        }

        // 比较结果
        int cmp = key.compareTo(node.key);
        // 大于则继续右侧递归遍历，小于则左侧递归遍历，等于则更新键所对应的值
        if (cmp > 0) {
            // 对结果接收以方便下面的自动平衡
            node.right = put(node.right, key, value);
        } else if (cmp < 0) {
            // 对结果接收以方便下面的自动平衡
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }

        // 三种情况：
        // i. 新键最小，操作为1，3
        // ii. 新键最大，操作为3
        // iii. 新建介于两者之间，操作为1，2，3

        // 1. 右红左黑，需左旋
        if (isRed(node.right) && !isRed(node.left)) {
            rotateLeft(node);
        }
        // 2. 左子节点及其左子节点均为红色节点，需右旋
        if(isRed(node.left) && isRed(node.left.left)) {
            rotateRight(node);
        }
        // 3. 左右两个子节点均为红色节点，需变色
        if(isRed(node.left) && isRed(node.right)) {
            flipColour(node);
        }

        // 跟新以节点为根所包含的节点数
        node.size = node.left.size + node.right.size + 1;
        return node;
    }
}
