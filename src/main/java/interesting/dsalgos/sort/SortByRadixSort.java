package interesting.dsalgos.sort;

public class SortByRadixSort {
    public static void main(String[] args) {
        String[] input = {"bcdef", "dbaqc", "abcde", "omadd", "bbbbb"};
        Utils.print(input);
        new SortByRadixSort().radixSort(input, 26, 5);
        Utils.print(input);
    }

    public void radixSort(String[] input, int radix, int width) {
        for (int i = 0; i < width; i++) {
            radixSingleSort(input, i, radix);
        }
    }

    private void radixSingleSort(String[] input, int pos, int radix) {
        int[] count = new int[radix];
        for (String value : input) {
            count[getChar(pos, value, radix)]++;
        }
        for (int i = 1; i < radix; i++) {
            count[i] += count[i - 1];
        }
        String[] temp = new String[input.length];
        for (int i = temp.length - 1; i >= 0; i--) {
            temp[--count[getChar(pos, input[i], radix)]] = input[i];
        }
        System.arraycopy(temp, 0, input, 0, input.length);
    }

    private int getChar(int pos, String value, int radix) {
        return (value.charAt(value.length() - pos - 1) - 'a') % radix;
    }
}
