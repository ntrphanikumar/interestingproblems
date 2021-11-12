package interesting.problems.linkedlists;

public class MaxStack {
    class Node {
        int value, max;
        Node next;
        Node(int value, int max) {
            this.value = value;
            this.max = max;
        }

    }

    private Node stack;

    public void push(int n) {
        if(stack == null) {
            stack = new Node(n, n);
        } else {
            Node node = new Node(n, Math.max(n, stack.max));
            node.next = stack;
            stack = node;
        }
    }

    public int pop() {
        if(stack == null) {
            throw new IllegalStateException("Stack is empty");
        }
        Node top = stack;
        stack = stack.next;
        top.next = null;
        return top.value;
    }

    public  int max() {
        if(stack == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.max;
    }

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(10);
        System.out.println(stack.max());
        stack.push(100);
        System.out.println(stack.max());
        stack.push(15);
        System.out.println(stack.max());
        stack.push(20);
        System.out.println(stack.max());
        stack.push(1000);
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        stack.push(40);
        System.out.println(stack.max());
    }
}
