package interesting.problems.apple.linkedlists;

public class MergeSortedLists {
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this(value, null);
        }

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private static class MyList {
        Node head;

        MyList add(int value) {
            if (head == null) {
                head = new Node(value);
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = new Node(value);
            }
            return this;
        }

        void print() {
            if (head == null) {
                System.out.println("Null");
                return;
            }
            Node current = head;
            while (current != null) {
                System.out.print(current.value + " ");
                current = current.next;
            }
            System.out.println();
        }
    }

    public static MyList merge(MyList list1, MyList list2) {
        if ((list1 == null || list1.head == null) && (list2 == null || list2.head == null)) {
            return new MyList();
        }
        if (list1 == null || list1.head == null) {
            return list2;
        }
        if (list2 == null || list2.head == null) {
            return list1;
        }
        MyList result = new MyList();
        Node list1Node = list1.head, list2Node = list2.head;
        while (list1Node != null && list2Node != null) {
            if (list1Node.value <= list2Node.value) {
                result.add(list1Node.value);
                list1Node = list1Node.next;
            } else {
                result.add(list2Node.value);
                list2Node = list2Node.next;
            }
        }
        while (list1Node != null) {
            result.add(list1Node.value);
            list1Node = list1Node.next;
        }
        while (list2Node != null) {
            result.add(list2Node.value);
            list2Node = list2Node.next;
        }
        return result;
    }

    public static void main(String[] args) {
        MyList list1 = new MyList().add(1).add(2).add(4).add(6).add(7);
        MyList list2 = new MyList().add(2).add(3).add(5).add(7);
        list1.print();
        list2.print();
        MyList merge = merge(list2, list1);
        merge.print();
    }
}
