package com.keyin;

import com.keyin.BinaryTree.BinarySearchTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    private BinarySearchTree binarySearchTree;

    @BeforeEach
    public void setUp() {
        binarySearchTree = new BinarySearchTree();
    }

    @Test
    public void testInsert() {
        binarySearchTree.insert(5);
        binarySearchTree.insert(3);
        binarySearchTree.insert(7);
        binarySearchTree.insert(2);
        binarySearchTree.insert(4);

        BinarySearchTree.Node root = binarySearchTree.getRoot();
        assertNotNull(root);

        assertEquals(5, root.getValue());
        assertEquals(3, root.getLeft().getValue());
        assertEquals(7, root.getRight().getValue());
        assertEquals(2, root.getLeft().getLeft().getValue());
        assertEquals(4, root.getLeft().getRight().getValue());
    }

    @Test
    public void testToJson() {
        // Test an empty tree
        assertEquals("{}", binarySearchTree.toJson());

        // Test a tree with nodes
        binarySearchTree.insert(5);
        binarySearchTree.insert(3);
        binarySearchTree.insert(7);

        String expectedJson = "{\n\n" +
                "  \"value\":5,\n" +
                "  \"left\":\n" +
                "    \"value\":3,\n" +
                "    \"left\":null,\n" +
                "    \"right\":null,\n" +
                "  \"right\":\n" +
                "    \"value\":7,\n" +
                "    \"left\":null,\n" +
                "    \"right\":null\n" +
                "}";
        assertEquals(expectedJson, binarySearchTree.toJson());
    }
}
