package interesting.sudoku;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static interesting.sudoku.SudokuConstants.SUDOKU_3X3_NIGHTMARE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.concat;

public class Sudoku {

    private static final BinaryOperator<Boolean> OR = (a, b) -> b || a;
    private static final BinaryOperator<Boolean> AND = (a, b) -> a && b;
    private static final Predicate<Sudoku> NOT_NULL = v -> v != null;
    private final int length, bSize;
    private Integer[][] matrix;
    private List<List<Set<Integer>>> possibles;

    public Sudoku(Integer[][] sudoku) {
        this.matrix = sudoku;
        this.length = sudoku.length;
        this.bSize = (int) Math.sqrt(length);
        this.possibles = range(0, sudoku.length)
                .mapToObj(i -> range(0, sudoku.length).mapToObj(j -> allowedValues()).collect(toList()))
                .collect(toList());
        range(0, sudoku.length).forEach(row -> range(0, sudoku.length)
                .filter(col -> sudoku[row][col] != null && !possibles.get(row).get(col).isEmpty())
                .forEach(col -> setValueAtPosition(row, col, sudoku[row][col])));
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

    public Sudoku solve() {
        if (concat(fillCellsWithSinglePossibility(), fillSingleValueOnlyPossibleCells()).reduce(FALSE, OR)) {
            solve();
        }
        if (!isFilled()) {
            Sudoku solved = tryWithRandomPickedValueForACell();
            matrix = solved.matrix;
            possibles = solved.possibles;
        }
        return this;
    }

    public boolean isValid() {
        return range(0, length).mapToObj(idx -> asList(matrix[idx]).containsAll(allowedValues())
                && range(0, length).mapToObj(row -> matrix[row][idx]).collect(toSet()).containsAll(allowedValues())
                && cells(idx).map(c -> matrix[c.row][c.col]).collect(toSet()).containsAll(allowedValues()))
                .reduce(TRUE, AND);
    }

    private Sudoku tryWithRandomPickedValueForACell() {
        return range(0, length).mapToObj(row -> {
            return range(0, length).filter(col -> matrix[row][col] == null).mapToObj(col -> tryAPencilValue(row, col))
                    .filter(NOT_NULL).findAny().orElse(null);
        }).filter(NOT_NULL).findAny().orElse(this);
    }

    private Sudoku tryAPencilValue(Integer row, Integer col) {
        if (possibles.get(row).get(col).isEmpty()) {
            return this;
        }
        return possibles.get(row).get(col).stream().map(value -> tryValue(row, col, value)).filter(NOT_NULL).findAny()
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

    private Stream<Boolean> fillSingleValueOnlyPossibleCells() {
        return range(0, length)
                .mapToObj(idx -> concat(
                        concat(subtract(allowedValues(), asList(matrix[idx])).map(value -> fillInRow(idx, value)),
                                subtract(allowedValues(),
                                        range(0, length).mapToObj(row -> matrix[row][idx]).collect(toSet()))
                                        .map(value -> fillInCol(idx, value))),
                        subtract(allowedValues(), cells(idx).map(c -> matrix[c.row][c.col]).collect(toSet()))
                                .map(value -> fillInBlock(idx, value))))
                .flatMap(identity());
    }

    private Stream<Cell> cells(int block) {
        return range(0, bSize)
                .mapToObj(row -> range(0, bSize).mapToObj(col -> new Cell(row(block, row), col(block, col))))
                .flatMap(identity());
    }

    private int row(int block, int row) {
        return ((block / bSize) * bSize) + row;
    }

    private int col(int block, int col) {
        return ((block % bSize) * bSize) + col;
    }

    private boolean fillInBlock(int block, Integer value) {
        return fillInCell(range(0, bSize).mapToObj(row -> range(0, bSize)
                .filter(col -> possibles.get(row(block, row)).get(col(block, col)).contains(value))
                .mapToObj(col -> new Cell(row, col))).flatMap(identity()).collect(toList()), value);
    }

    private boolean fillInCol(int col, Integer value) {
        return fillInCell(range(0, length).filter(row -> possibles.get(row).get(col).contains(value))
                .mapToObj(row -> new Cell(row, col)).collect(toList()), value);
    }

    private boolean fillInRow(int row, Integer value) {
        return fillInCell(range(0, length).filter(col -> possibles.get(row).get(col).contains(value))
                .mapToObj(col -> new Cell(row, col)).collect(toList()), value);
    }

    private boolean fillInCell(List<Cell> cells, Integer value) {
        return cells.size() == 1 && setValueAtPosition(cells.get(0).row, cells.get(0).col, value);
    }

    private <T> Stream<T> subtract(Set<T> from, Collection<T> sub) {
        return from.stream().filter(val -> !sub.contains(val));
    }

    private Stream<Boolean> fillCellsWithSinglePossibility() {
        return range(0, length)
                .mapToObj(row -> range(0, length)
                        .filter(col -> matrix[row][col] == null && possibles.get(row).get(col).size() == 1)
                        .mapToObj(col -> setValueAtPosition(row, col, possibles.get(row).get(col).iterator().next())))
                .flatMap(identity());
    }

    private boolean setValueAtPosition(int row, int col, Integer value) {
        matrix[row][col] = value;
        possibles.get(row).get(col).clear();
        range(0, length).forEach(idx -> {
            possibles.get(row).get(idx).remove(value);
            possibles.get(idx).get(col).remove(value);
        });
        cells(((row / bSize) * bSize) + (col / bSize)).forEach(c -> possibles.get(c.row).get(c.col).remove(value));
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

    static class Cell {
        private final int row, col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
