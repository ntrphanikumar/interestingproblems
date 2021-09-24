package hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class MergeNode {

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep) throws IOException {
        while (node != null) {
            System.out.print(node.data);
            node = node.next;

            if (node != null) {
                System.out.print(sep);
            }
        }
        System.out.println();
    }

    // Complete the findMergeNode function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode node1 = head1;
        SinglyLinkedListNode node2 = head2;
        while (node1 != node2) {
            node1 = node1.next == null ? head2 : node1.next;
            node2 = node2.next == null ? head1 : node2.next;
        }
        return node1.data;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int index = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        SinglyLinkedList llist1 = new SinglyLinkedList();

        int llist1Count = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < llist1Count; i++) {
            int llist1Item = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            llist1.insertNode(llist1Item);
        }

        SinglyLinkedList llist2 = new SinglyLinkedList();

        int llist2Count = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < llist2Count; i++) {
            int llist2Item = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            llist2.insertNode(llist2Item);
        }

        SinglyLinkedListNode ptr1 = llist1.head;
        SinglyLinkedListNode ptr2 = llist2.head;
        printSinglyLinkedList(llist1.head, "->");
        printSinglyLinkedList(llist2.head, "->");

        for (int i = 0; i < llist1Count; i++) {
            if (i < index) {
                ptr1 = ptr1.next;
            }
        }

        for (int i = 0; i < llist2Count; i++) {
            if (i != llist2Count - 1) {
                ptr2 = ptr2.next;
            }
        }

        ptr2.next = ptr1;

        printSinglyLinkedList(llist1.head, "->");
        printSinglyLinkedList(llist2.head, "->");

        int result = findMergeNode(llist1.head, llist2.head);
        System.out.println(result);


        scanner.close();
    }
}

