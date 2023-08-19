package com.keyin.BinaryTree;

public class BinarySearchTree {
    private Node root;

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        }

        return current;
    }

    public Node getRoot() {
        return root;
    }
}
