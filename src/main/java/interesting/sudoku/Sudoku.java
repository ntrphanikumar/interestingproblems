package interesting.sudoku;

import static interesting.sudoku.SudokuConstants.SUDOKU_3X3_DIFF;
import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BinaryOperator;

public class Sudoku {

    private static final BinaryOperator<Boolean> OR = (a, b) -> b || a;
    private static final BinaryOperator<Boolean> AND = (a, b) -> a && b;

    private final Integer[][] matrix;
    private final List<List<Set<Integer>>> pencilmarks;
    private final int length, blockSize;

    public Sudoku(Integer[][] sudoku) {
        this.matrix = sudoku;
        this.length = sudoku.length;
        this.blockSize = (int) Math.sqrt((double) length);
        this.pencilmarks = range(0, sudoku.length).boxed()
                .map(i -> range(0, sudoku.length).boxed().map(j -> allowedValues()).collect(toList()))
                .collect(toList());
        range(0, sudoku.length).boxed().forEach(row -> range(0, sudoku.length).boxed().forEach(col -> {
            if (sudoku[row][col] != null && !pencilmarks.get(row).get(col).isEmpty()) {
                setValueAtPosition(row, col, sudoku[row][col]);
            }
        }));
    }

    public Sudoku solve() {
        boolean needsAdjustment = fillCellsWithSinglePossibility();
        needsAdjustment = fillSingleValueOnlyPossibleCells() || needsAdjustment;
        if (needsAdjustment) {
            solve();
        }
        return isFilled() ? this : tryWithRandomPickedValueForACell();
    }

    public boolean isValid() {
        Set<Integer> values = allowedValues();
        return range(0, length).boxed().map(i -> {
            if (!values.equals(new HashSet<>(asList(matrix[i])))) {
                return false;
            }
            if (!values.equals(range(0, length).boxed().map(row -> matrix[row][i]).collect(toSet()))) {
                return false;
            }

            int startRow = (i / blockSize) * blockSize, startCol = (i % blockSize) * blockSize;
            Set<Integer> innerSquareNumbers = range(0, blockSize).boxed().map(row -> range(0, blockSize).boxed()
                    .map(col -> matrix[row + startRow][col + startCol]).collect(toSet())).flatMap(Set::stream)
                    .collect(toSet());
            if (!values.equals(innerSquareNumbers)) {
                return false;
            }
            return true;
        }).reduce(true, AND);
    }

    private Sudoku tryWithRandomPickedValueForACell() {
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                Set<Integer> allowed = pencilmarks.get(row).get(col);
                if (matrix[row][col] == null && allowed.isEmpty()) {
                    return this;
                } else {
                    for (int value : allowed) {
                        if (matrix[6][8] == null) {
                            Integer[][] clone = Arrays.stream(matrix).map(Integer[]::clone).toArray(Integer[][]::new);
                            clone[row][col] = value;
                            Sudoku attempt = new Sudoku(clone).solve();
                            if (attempt.isValid()) {
                                return attempt;
                            }
                        }
                    }
                }
            }
        }
        return this;
    }

    private boolean isFilled() {
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                if (matrix[row][col] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean fillSingleValueOnlyPossibleCells() {
        return range(0, length).boxed().map(idx -> {
            boolean changed = false;
            changed = subtract(allowedValues(), asList(matrix[idx])).stream().map(value -> {
                List<Integer> cols = range(0, length).boxed()
                        .filter(col -> pencilmarks.get(idx).get(col).contains(value)).collect(toList());
                if (cols.size() == 1) {
                    setValueAtPosition(idx, cols.get(0), value);
                    return true;
                }
                return false;
            }).reduce(false, OR) || changed;

            changed = subtract(allowedValues(), range(0, length).boxed().map(row -> matrix[row][idx]).collect(toSet()))
                    .stream().map(value -> {
                        List<Integer> rows = range(0, length).boxed()
                                .filter(row -> pencilmarks.get(row).get(idx).contains(value)).collect(toList());
                        if (rows.size() == 1) {
                            setValueAtPosition(rows.get(0), idx, value);
                            return true;
                        }
                        return false;
                    }).reduce(false, OR) || changed;

            int startRow = (idx / blockSize) * blockSize, startCol = (idx % blockSize) * blockSize;
            Map<Integer, Long> missingValuesByPossibleLocations = range(0, blockSize).boxed()
                    .map(row -> range(0, blockSize).boxed()
                            .map(col -> pencilmarks.get(row + startRow).get(col + startCol).stream()).flatMap(l -> l))
                    .flatMap(l -> l).collect(groupingBy(identity(), counting()));

            missingValuesByPossibleLocations.entrySet().stream().filter(t -> t.getValue() == 1).map(Entry::getKey);

            for (Integer value : missingValuesByPossibleLocations.keySet()) {
                if (missingValuesByPossibleLocations.get(value) == 1) {
                    changed = range(0, blockSize).boxed().map(row -> range(0, blockSize).boxed().map(col -> {
                        if (pencilmarks.get(row + startRow).get(col + startCol).contains(value)) {
                            setValueAtPosition(row + startRow, col + startCol, value);
                            return true;
                        }
                        return false;
                    }).reduce(false, OR)).reduce(false, OR) || changed;
                }
            }
            return changed;
        }).reduce(false, OR);
    }

    private <T> Set<T> subtract(Set<T> from, Collection<T> sub) {
        from.removeIf(sub::contains);
        return from;
    }

    private boolean fillCellsWithSinglePossibility() {
        return range(0, length).boxed().map(row -> range(0, length).boxed().map(col -> {
            if (matrix[row][col] == null && pencilmarks.get(row).get(col).size() == 1) {
                setValueAtPosition(row, col, pencilmarks.get(row).get(col).iterator().next());
                return true;
            }
            return false;
        }).reduce(false, OR)).reduce(false, OR);
    }

    private void setValueAtPosition(int row, int col, Integer valueAtPos) {
        matrix[row][col] = valueAtPos;
        pencilmarks.get(row).get(col).clear();

        range(0, length).boxed().forEach(idx -> {
            pencilmarks.get(row).get(idx).remove(valueAtPos);
            pencilmarks.get(idx).get(col).remove(valueAtPos);
        });
        int blockStartRow = (row / blockSize) * blockSize, blockStartCol = (col / blockSize) * blockSize;
        range(0, blockSize).boxed().forEach(r -> range(0, blockSize).boxed()
                .forEach(c -> pencilmarks.get(r + blockStartRow).get(c + blockStartCol).remove(valueAtPos)));
    }

    private Set<Integer> allowedValues() {
        return rangeClosed(1, length).boxed().collect(toSet());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        range(0, length).boxed().forEach(row -> {
            range(0, length).boxed().forEach(col -> {
                builder.append((matrix[row][col] == null ? "X" : String.valueOf(matrix[row][col]))).append(" ");
            });
            builder.append("\n");
        });
        return builder.toString();
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(SUDOKU_3X3_DIFF);
        System.out.println(sudoku);
        sudoku = sudoku.solve();
        System.out.println(sudoku);
        if (!sudoku.isValid()) {
            System.out.println("Unsolved");
        } else {
            System.out.println("Solved");
        }
    }
}
