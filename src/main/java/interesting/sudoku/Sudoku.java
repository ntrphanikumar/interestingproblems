package interesting.sudoku;

import static interesting.sudoku.SudokuConstants.SUDOKU_3X3_NIGHTMARE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.concat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Sudoku {

    private static final BinaryOperator<Boolean> OR = (a, b) -> b || a;
    private static final BinaryOperator<Boolean> AND = (a, b) -> a && b;
    private static final Predicate<Sudoku> NOT_NULL = v -> v != null;

    private Integer[][] matrix;
    private List<List<Set<Integer>>> pencilmarks;
    private final int length, blockSize;

    public Sudoku(Integer[][] sudoku) {
        this.matrix = sudoku;
        this.length = sudoku.length;
        this.blockSize = (int) Math.sqrt((double) length);
        this.pencilmarks = range(0, sudoku.length)
                .mapToObj(i -> range(0, sudoku.length).mapToObj(j -> allowedValues()).collect(toList()))
                .collect(toList());
        range(0, sudoku.length).forEach(row -> range(0, sudoku.length)
                .filter(col -> sudoku[row][col] != null && !pencilmarks.get(row).get(col).isEmpty())
                .forEach(col -> setValueAtPosition(row, col, sudoku[row][col])));
    }

    public Sudoku solve() {
        boolean needsAdjustment = fillCellsWithSinglePossibility();
        needsAdjustment = fillSingleValueOnlyPossibleCells() || needsAdjustment;
        if (needsAdjustment) {
            solve();
        }
        if (!isFilled()) {
            Sudoku solved = tryWithRandomPickedValueForACell();
            matrix = solved.matrix;
            pencilmarks = solved.pencilmarks;
        }
        return this;
    }

    public boolean isValid() {
        return range(0, length)
                .mapToObj(i -> asList(matrix[i]).containsAll(allowedValues())
                        && range(0, length).mapToObj(row -> matrix[row][i]).allMatch(allowedValues()::contains)
                        && range(0, blockSize).mapToObj(
                                row -> range(0, blockSize).mapToObj(col -> matrix[blockRow(i, row)][blockCol(i, col)]))
                                .flatMap(identity()).allMatch(allowedValues()::contains))
                .reduce(TRUE, AND);
    }

    private Sudoku tryWithRandomPickedValueForACell() {
        return range(0, length).mapToObj(row -> {
            return range(0, length).filter(col -> matrix[row][col] == null).mapToObj(col -> tryAPencilValue(row, col))
                    .filter(NOT_NULL).findAny().orElse(null);
        }).filter(NOT_NULL).findAny().orElse(this);
    }

    private Sudoku tryAPencilValue(Integer row, Integer col) {
        if (pencilmarks.get(row).get(col).isEmpty()) {
            return this;
        }
        return pencilmarks.get(row).get(col).stream().map(value -> tryValue(row, col, value)).filter(NOT_NULL).findAny()
                .orElse(null);
    }

    private Sudoku tryValue(Integer row, Integer col, Integer value) {
        Integer[][] clone = Arrays.stream(matrix).map(Integer[]::clone).toArray(Integer[][]::new);
        clone[row][col] = value;
        Sudoku attempt = new Sudoku(clone).solve();
        return attempt.isValid() ? attempt : null;
    }

    private boolean isFilled() {
        return range(0, length)
                .mapToObj(row -> range(0, length).mapToObj(col -> matrix[row][col]).filter(v -> v == null).count() > 0)
                .filter(TRUE::equals).count() == 0;
    }

    private boolean fillSingleValueOnlyPossibleCells() {
        return range(0, length)
                .mapToObj(idx -> concat(concat(
                        subtract(allowedValues(), asList(matrix[idx])).map(value -> fillInRow(idx, value)),
                        subtract(allowedValues(), range(0, length).mapToObj(row -> matrix[row][idx]).collect(toSet()))
                                .map(value -> fillInCol(idx, value))),
                        subtract(allowedValues(),
                                range(0, blockSize)
                                        .mapToObj(row -> range(0, blockSize)
                                                .mapToObj(col -> matrix[blockRow(idx, row)][blockCol(idx, col)]))
                                        .flatMap(identity()).collect(toSet())).map(value -> fillInBlock(idx, value))))
                .flatMap(l -> l).reduce(FALSE, OR);
    }

    private int blockRow(int block, int row) {
        return ((block / blockSize) * blockSize) + row;
    }

    private int blockCol(int block, int col) {
        return ((block % blockSize) * blockSize) + col;
    }

    static class Cell {
        private final int row, col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private boolean fillInBlock(int block, Integer value) {
        return fillInCell(range(0, blockSize).mapToObj(row -> range(0, blockSize)
                .filter(col -> pencilmarks.get(blockRow(block, row)).get(blockCol(block, col)).contains(value))
                .mapToObj(col -> new Cell(row, col))).flatMap(l -> l).collect(toList()), value);
    }

    private boolean fillInCol(int col, Integer value) {
        return fillInCell(range(0, length).filter(row -> pencilmarks.get(row).get(col).contains(value))
                .mapToObj(row -> new Cell(row, col)).collect(toList()), value);
    }

    private boolean fillInRow(int row, Integer value) {
        return fillInCell(range(0, length).filter(col -> pencilmarks.get(row).get(col).contains(value))
                .mapToObj(col -> new Cell(row, col)).collect(toList()), value);
    }

    private boolean fillInCell(List<Cell> cells, Integer value) {
        return cells.size() == 1 ? setValueAtPosition(cells.get(0).row, cells.get(0).col, value) : false;
    }

    private <T> Stream<T> subtract(Set<T> from, Collection<T> sub) {
        return from.stream().filter(val -> !sub.contains(val));
    }

    private boolean fillCellsWithSinglePossibility() {
        return range(0, length).mapToObj(row -> range(0, length).mapToObj(col -> {
            if (matrix[row][col] == null && pencilmarks.get(row).get(col).size() == 1) {
                setValueAtPosition(row, col, pencilmarks.get(row).get(col).iterator().next());
                return true;
            }
            return false;
        }).reduce(FALSE, OR)).reduce(FALSE, OR);
    }

    private boolean setValueAtPosition(int row, int col, Integer value) {
        matrix[row][col] = value;
        pencilmarks.get(row).get(col).clear();
        range(0, length).forEach(idx -> {
            pencilmarks.get(row).get(idx).remove(value);
            pencilmarks.get(idx).get(col).remove(value);
        });
        int startRow = (row / blockSize) * blockSize, startCol = (col / blockSize) * blockSize;
        range(0, blockSize).forEach(
                r -> range(0, blockSize).forEach(c -> pencilmarks.get(r + startRow).get(c + startCol).remove(value)));
        return true;
    }

    private Set<Integer> allowedValues() {
        return rangeClosed(1, length).boxed().collect(toSet());
    }

    @Override
    public String toString() {
        return range(0, length).mapToObj(row -> range(0, length)
                .mapToObj(col -> matrix[row][col] == null ? "X" : String.valueOf(matrix[row][col]))
                .collect(joining(" "))).collect(joining("\n"));
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(SUDOKU_3X3_NIGHTMARE);
        System.out.println(sudoku);
        if (sudoku.solve().isValid()) {
            System.out.println("Solved");
        } else {
            System.out.println("Unsolved");
        }
        System.out.println(sudoku);
    }
}
