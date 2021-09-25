package interesting.hackerrank.warmup.ds;

public class QueueFromStack {

    private java.util.Stack<Integer> stack1 = new java.util.Stack<>();
    private java.util.Stack<Integer> stack2 = new java.util.Stack<>();

    public int enqueue(int number) {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            stack1.add(number);
        } else if (stack1.isEmpty()) {
            stack1.add(number);
            while (!stack2.isEmpty()) {
                stack1.add(stack2.pop());
            }
        } else {
            stack2.add(number);
            while (!stack1.isEmpty()) {
                stack2.add(stack1.pop());
            }
        }
        return number;
    }

    public int dequeue() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stack1.isEmpty() ? stack2.pop() : stack1.pop();
    }

    public int peek() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stack1.isEmpty() ? stack2.peek() : stack1.peek();
    }

    public static void main(String[] args) throws Exception {
        QueueFromStack queue = new QueueFromStack();
        while (true) {
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(System.in));

            // Reading data using readLine
            String name = reader.readLine();

            // Printing the read line
            System.out.println(name);
        }
    }
}
