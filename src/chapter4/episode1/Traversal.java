package chapter4.episode1;

/**
 * @author chongyang18@gmail.com
 * @date 03/03/2018
 */

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Traversal {
    private static boolean[] isMarked = new boolean[11];

    private static class Node {
        private int val;
        private Node left;
        private Node right;

        private Node(int val) {
            this.val = val;
        }
    }

    /**
     * 创建一棵树: [1,2,3,4,5,6,7,8,9,10,11]
     */
    private static void createTree(Node node) {
        if (node.val >= 6) {
            return;
        }
        node.left = new Node(2 * node.val);
        node.right = new Node(2 * node.val + 1);
        createTree(node.left);
        createTree(node.right);
    }

    /**
     * in-order traversal
     *
     * @param root tree root
     */
    private static void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }

    /**
     * post-order traversal
     *
     * @param root tree root
     */
    private static void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * pre-order traversal
     *
     * @param root tree root
     */
    private static void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * non-recursive traversal
     *
     * @param node tree root
     */
    private static void preOrderTraversalNonRecursive(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            System.out.print(temp.val + " ");
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
    }

    private static void dfs(Node node) {
        if (node == null) {
            return;
        }

        if (!isMarked[node.val - 1]) {
            System.out.print(node.val + " ");
            isMarked[node.val - 1] = true;
            dfs(node.left);
            dfs(node.right);
        }
    }

    /**
     * sequential order to print out the tree
     *
     * @param node tree root
     */
    private static void bfs(Node node) {
        Queue<Node> que = new ArrayDeque<>();
        que.add(node);
        while (!que.isEmpty()) {
            Node temp = que.poll();
            System.out.print(temp.val + " ");
            if (temp.left != null) {
                que.add(temp.left);
            }
            if (temp.right != null) {
                que.add(temp.right);
            }
        }
    }

    /**
     * hierarchical traversal
     *
     * @param node tree root
     */
    private static void hierarchicalTraversal(Node node) {

    }

    public static void main(String[] args) {
        Node root = new Node(1);
        createTree(root);
        System.out.print("先序非递归：");
        preOrderTraversalNonRecursive(root);
        System.out.println();
        System.out.print("先序递归：  ");
        preOrderTraversal(root);
        System.out.println();
        System.out.print("深度优先：  ");
        dfs(root);
        System.out.println("\n\n---------------------------\n");
        System.out.print("广度优先：");
        bfs(root);
        System.out.println();
        System.out.print("中序遍历：");
        inOrderTraversal(root);
        System.out.println();
        System.out.print("后序遍历：");
        postOrderTraversal(root);
    }
}


