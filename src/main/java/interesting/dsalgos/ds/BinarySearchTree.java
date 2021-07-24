package interesting.dsalgos.ds;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(25).insert(20).insert(15).insert(27).insert(30).insert(29).insert(26).insert(22).insert(32);
        bst.printPreOrder();
        bst.printInOrder();
        bst.printPostOrder();
        bst.printLevelOrder();
        System.out.println("Min: " + bst.min());
        System.out.println("Max: " + bst.max());
        System.out.println("Find 27: " + bst.get(27));
        System.out.println("Find 45: " + bst.get(45));
        System.out.println("Find 10: " + bst.get(10));
        bst.delete(25);
        bst.printLevelOrder();
        bst.delete(26);
        bst.printLevelOrder();
    }

    public BinarySearchTree<T> insert(T value) {
        if (root == null) {
            root = new TreeNode<>(value);
        } else {
            root.insert(value);
        }
        return this;
    }

    public void printLevelOrder() {
        if (root == null) {
            return;
        }
        System.out.print("LevelOrder:");
        root.printLevelOrder();
        System.out.println();
    }

    public void printInOrder() {
        if (root == null) {
            return;
        }
        System.out.print("InOrder   :");
        root.printInOrder();
        System.out.println();
    }

    public void printPreOrder() {
        if (root == null) {
            return;
        }
        System.out.print("PreOrder  :");
        root.printPreOrder();
        System.out.println();
    }

    public void printPostOrder() {
        if (root == null) {
            return;
        }
        System.out.print("PostOrder :");
        root.printPostOrder();
        System.out.println();
    }

    public T min() {
        return root == null ? null : root.min();
    }

    public T max() {
        return root == null ? null : root.max();
    }

    public TreeNode<T> get(T value) {
        return root == null ? null : root.get(value);
    }

    public void delete(T value) {
        root = delete(root, value);
    }

    private TreeNode<T> delete(TreeNode<T> subTreeRoot, T value) {
        if (subTreeRoot == null) {
            return null;
        }
        if (value.compareTo(subTreeRoot.data) < 0) {
            subTreeRoot.leftNode = delete(subTreeRoot.leftNode, value);
        } else if (value.compareTo(subTreeRoot.data) > 0) {
            subTreeRoot.rightNode = delete(subTreeRoot.rightNode, value);
        } else {
            if (subTreeRoot.leftNode == null) {
                return subTreeRoot.rightNode;
            }
            if (subTreeRoot.rightNode == null) {
                return subTreeRoot.leftNode;
            }
            subTreeRoot.data = subTreeRoot.rightNode.min();
            subTreeRoot.rightNode = delete(subTreeRoot.rightNode, subTreeRoot.data);
        }
        return subTreeRoot;
    }

    private static class TreeNode<T extends Comparable<T>> {
        private T data;
        private TreeNode<T> leftNode;
        private TreeNode<T> rightNode;

        private TreeNode(T data) {
            this.data = data;
        }

        public void insert(T value) {
            if (value.equals(data)) {
                return;
            }
            if (value.compareTo(data) < 0) {
                if (leftNode == null) {
                    leftNode = new TreeNode<>(value);
                } else {
                    leftNode.insert(value);
                }
            } else {
                if (rightNode == null) {
                    rightNode = new TreeNode<>(value);
                } else {
                    rightNode.insert(value);
                }
            }
        }

        public void printLevelOrder() {
            Queue<TreeNode<T>> nodes = new LinkedList<>();
            nodes.add(this);
            while (!nodes.isEmpty()) {
                TreeNode<T> node = nodes.remove();
                System.out.print(" " + node.data);
                if (node.leftNode != null) {
                    nodes.add(node.leftNode);
                }
                if (node.rightNode != null) {
                    nodes.add(node.rightNode);
                }
            }
        }

        public void printPreOrder() {
            System.out.print(" " + data);
            if (leftNode != null) {
                leftNode.printPreOrder();
            }
            if (rightNode != null) {
                rightNode.printPreOrder();
            }
        }

        public void printInOrder() {
            if (leftNode != null) {
                leftNode.printInOrder();
            }
            System.out.print(" " + data);
            if (rightNode != null) {
                rightNode.printInOrder();
            }
        }

        public void printPostOrder() {
            if (leftNode != null) {
                leftNode.printPostOrder();
            }
            if (rightNode != null) {
                rightNode.printPostOrder();
            }
            System.out.print(" " + data);
        }

        public TreeNode<T> get(T value) {
            if (value.equals(data)) {
                return this;
            }
            if (value.compareTo(data) < 0) {
                return leftNode == null ? null : leftNode.get(value);
            } else {
                return rightNode == null ? null : rightNode.get(value);
            }
        }

        public T min() {
            return leftNode == null ? data : leftNode.min();
        }

        public T max() {
            return rightNode == null ? data : rightNode.max();
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    '}';
        }
    }
}
