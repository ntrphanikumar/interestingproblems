package interesting.g4g;

public class RemoveLoop {
    class Node {
        int data;
        Node next;
    }

    public static void removeLoop(Node head) {
        Node nodeInLoop = checkLoop(head);
        if (nodeInLoop != null) {
            int loopSize = loopSize(nodeInLoop);
            Node first = head;
            Node second = head;
            for (int i = 0; i < loopSize; i++) {
                first = first.next;
            }
            while (first != second) {
                first = first.next;
                second = second.next;
            }
            while (second.next != first) {
                second = second.next;
            }
            second.next = null;
        }
    }

    private static Node checkLoop(Node head) {
        if (head.next == null || head.next.next == null) {
            return null;
        }
        Node slowNode = head;
        Node fastNode = head.next;
        while (slowNode != null && fastNode != null) {
            if (slowNode == fastNode) {
                return slowNode;
            }
            slowNode = slowNode.next;
            fastNode = fastNode.next == null ? null : fastNode.next.next;
        }
        return null;
    }

    private static int loopSize(Node nodeInLoop) {
        if (nodeInLoop == null) {
            return 0;
        }
        int length = 1;
        Node anotherNode = nodeInLoop.next;
        while (anotherNode != nodeInLoop) {
            anotherNode = anotherNode.next;
            length++;
        }
        return length;
    }
}
