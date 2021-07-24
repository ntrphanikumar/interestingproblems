package interesting.problems.strings;

public class ReverseWords {

    public static void main(String[] args) {

    }

    public String reverseWords(String s) {
        if (s == null || s.trim().length() <= 1) {
            return s;
        }
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].trim().length() > 0) {
                sb.append(split[i].trim()).append(" ");
            }
        }
        return sb.toString().trim();
    }
}
