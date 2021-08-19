package interesting.problems;

public class PrintRecursively {

    public static void display(int i) {
        System.out.print(i);
        if (i <= 3) {
            display(++i);
        }
        System.out.print(i);
    }

    public static void main(String[] args) {
        display(4);
        System.out.println();
        display(3);
        System.out.println();
        display(2);
        System.out.println();
        display(1);
        System.out.println();
    }
}
