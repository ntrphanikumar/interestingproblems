package interesting.leetcode;

public class MergeLists {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return partition(lists, 0, lists.length - 1);
    }

    private ListNode partition(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int partition = (start + end) / 2;
        return merge(partition(lists, start, partition), partition(lists, partition + 1, end));
    }

    private ListNode merge(ListNode first, ListNode second) {
        if (first == null || second == null) {
            return first == null ? second : first;
        }
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        ListNode temp = newHead;
        while (first != null && second != null) {
            if (first.val <= second.val) {
                first = copyAndMove(temp, first);
            } else {
                second = copyAndMove(temp, second);
            }
        }
        while (first != null) {
            first = copyAndMove(temp, first);
        }
        while (second != null) {
            second = copyAndMove(temp, second);
        }
        return newHead.next;
    }

    private ListNode copyAndMove(ListNode dest, ListNode source) {
        dest.next = source;
        dest = dest.next;
        source = source.next;
        return source;
    }
}
