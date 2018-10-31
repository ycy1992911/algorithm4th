package chapter3.episode2;

/**
 * Implementation of binary-search-tree based dictionary
 *
 * @author chongyang18@gmail.com
 * @date 21/02/2018
 */
public class E_1<Key extends Comparable<Key>, Value> {
    /**
     * node
     */
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int currentNodeSize;

        private Node(Key key, Value value, int currentNodeSize) {
            this.key = key;
            this.value = value;
            this.currentNodeSize = currentNodeSize;
        }
    }

    /**
     * tree root
     */
    private Node root = null;

    /**
     * add a node
     *
     * @param key   the key
     * @param value the value
     * @return the added/updated node
     */
    private Node put(Node root, Key key, Value value) {
        // empty tree
        if (root == null) {
            return new Node(key, value, 1);
        }

        int compareResult = key.compareTo(root.key);
        // go down and search
        if (compareResult > 0) {
            // key does not exist
            put(root.right, key, value);
        } else if (compareResult < 0) {
            // key does not exist
            put(root.left, key, value);
        } else {
            // key exists
            root.value = value;
        }
        root.currentNodeSize = root.left.currentNodeSize + root.right.currentNodeSize + 1;
        return root;
    }

    /**
     * search for a node
     * @param root the root of tree
     * @param key the key
     * @return <code>null</code> if not found, otherwise return the <code>node</code> looking for
     */
    private Node getNode(Node root, Key key) {
        if (root == null) {
            return null;
        }

        int compareResult = key.compareTo(root.key);
        if (compareResult < 0) {
            getNode(root.left, key);
        } else if (compareResult > 0) {
            getNode(root.right, key);
        }

        return root;
    }

    /**
     * find the node with the key that has minimum gap with parameter
     *
     * @param key target key
     * @return the node
     */
    private Node floor(Node root, Key key) {

        if(root.left == null || root.right == null) {
            return root;
        }

        int compareResult = key.compareTo(root.key);
        if(compareResult < 0) {
            floor(root.left, key);
        }else if(compareResult > 0) {
            floor(root.right, key);
        }
        return root;
    }

    /**
     * find the max node
     *
     * @return the max node
     */
    private Node max() {
        while(root.right != null) {
            root = root.right;
        }
        return root;
    }

    private Node put(Key key, Value value) {
        return put(root, key, value);
    }

    private Node getNode(Key key) {
        return getNode(root, key);
    }

    /**
     * find the min node
     * @return the min node
     */
    private Node min(Node root) {
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }

    private Node floor (Key key) {
        if(root == null) {
            return null;
        }
        return floor(root, key);
    }

    private Node deleteMin(Node root) {
        if(root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        root.currentNodeSize = root.left.currentNodeSize + root.right.currentNodeSize + 1;
        return root;
    }

    /**
     * 删除树中某一个节点
     *
     * @param root 根节点
     * @param key 节点的key
     * @return 树的新根节点
     */
    private Node delete(Node root, Key key) {
        if(root == null) {
            return null;
        }

        int cmp = key.compareTo(root.key);
        if(cmp > 0) {
            root.right = delete(root.right, key);
        }else if(cmp < 0) {
            root.left = delete(root.left, key);
        }else{
            if(root.left == null) {
                return root.right;
            }
            if(root.right == null) {
                return root.left;
            }
            Node con = root;
            root = min(con.right);
            root.right = deleteMin(con.right);
            root.left = con.right;
        }
        root.currentNodeSize = root.left.currentNodeSize + root.right.currentNodeSize + 1;
        return root;
    }

    /**
     * 递归先序遍历一棵树（先根后子树）
     * @param root 根节点
     */
    private void preOrderTraversal(Node root) {
        if(root == null) {
            return;
        }
        System.out.println(root.key);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * 递归中序遍历一棵树 (先左后右)，也可以先右后左
     * @param root 根节点
     */
    private void inOrderTraversal(Node root) {
        if(root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.key + " ");
        inOrderTraversal(root.right);
    }

    /**
     * 递归后序遍历一棵树（先子树后根），以下是先左侧子树
     * @param root 根节点
     */
    private void postOrderTraversal(Node root){
        if(root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.key);
    }

}
