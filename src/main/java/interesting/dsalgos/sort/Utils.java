package interesting.dsalgos.sort;

class Utils {
    static final <T> void print(T[] array) {
        for (T ele : array) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    static final <T> void swap(T[] array, int i, int j) {
        if (array[i].equals(array[j])) {
            return;
        }
        T temp = array[j];
        array[j] = array[i];
        array[i] = temp;
        System.out.println("Swapping idx: " + i + " with idx: " + j);
    }
}
