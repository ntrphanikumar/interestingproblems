package interesting.sudoku;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {
    public boolean isValid(Integer[][] sudoku) {
        int max = sudoku.length, squareDimention = (int) Math.sqrt((double) max);
        Set<Integer> values = rangeClosed(1, max).boxed().collect(toSet());
        return range(0, max).boxed().map(i -> {
            if (!values.equals(new HashSet<>(asList(sudoku[i])))) {
                return false;
            }
            if (!values.equals(range(0, max).boxed().map(row -> sudoku[row][i]).collect(toSet()))) {
                return false;
            }

            int startRow = (i / squareDimention) * squareDimention, startCol = (i % squareDimention) * squareDimention;
            Set<Integer> innerSquareNumbers = range(startRow, startRow + squareDimention).boxed()
                    .map(row -> range(startCol, startCol + squareDimention).boxed().map(col -> sudoku[row][col])
                            .collect(toSet()))
                    .flatMap(Set::stream).collect(toSet());
            if (!values.equals(innerSquareNumbers)) {
                return false;
            }
            return true;
        }).reduce(true, (t, u) -> t && u);
    }

    public static void main(String[] args) {
        Integer[][] sudoku2X2 = new Integer[][] { 
            new Integer[] { 2, 1, 4, 3 }, 
            new Integer[] { 3, 4, 2, 1 },
            new Integer[] { 4, 3, 1, 2 }, 
            new Integer[] { 1, 2, 3, 4 } 
        };
        System.out.println(new SudokuValidator().isValid(sudoku2X2));
    }
}
