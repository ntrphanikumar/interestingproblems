package interesting.problems.strings;

public class LongStringWithNewLines {
    public static void main(String[] args) {
        String str = "Phani kumar\nHe is and awesome person\nDon't mess with him";
        String[] split = str.split("\n");
        for(String line: str.split("\n")) {
            System.out.println(line);
        }
        System.out.println(split[split.length-1]);
    }
}
