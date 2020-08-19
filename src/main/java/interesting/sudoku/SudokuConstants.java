package interesting.sudoku;

interface SudokuConstants {
    Integer X = null;
    
    Integer[][] SUDOKU_2X2 = new Integer[][] { 
        new Integer[] { X, 4, X, 1 }, 
        new Integer[] { 3, X, 4, X },
        new Integer[] { 1, X, X, 4 }, 
        new Integer[] { X, 2, 1, X } 
    };
    
    Integer[][] SUDOKU_3X3_EASY = new Integer[][] {
        new Integer[] {X, 3, 7, 2, X, 4, X, 8, X},
        new Integer[] {2, 5, X, X, X, 1, 3, 4, X},
        new Integer[] {4, X, 6, 9, 3, X, 1, 2, X},
        new Integer[] {X, 7, X, X, 9, X, X, X, X},
        new Integer[] {X, 1, 4, X ,X ,X, 5, 9, 3},
        new Integer[] {9, X, X, X, X, 3, 8, 7, X},
        new Integer[] {X, 9, 8, X, X, X, 4, X, 2},
        new Integer[] {7, X, X, 6, 8, 2, 9, 3, X},
        new Integer[] {6, X, 1, X, X, X, X, 5, X}
    };
    
    Integer[][] SUDOKU_3X3_MEDIUM = new Integer[][] {
        new Integer[] {9, X, 1, X, 5, 4, X, 8, 7},
        new Integer[] {X, X, X, X, 6, 9, X, 5, 3},
        new Integer[] {X, X, X, X, X, X, 6, X, 9},
        new Integer[] {X, 5, 3, 4, X, X, X, 1, 2},
        new Integer[] {4, X, X, X, X, X, 3, X, X},
        new Integer[] {X, X, 2, X ,X, X, X, 6, 4},
        new Integer[] {7, X, 6, 2, 4, X, X, 3, X},
        new Integer[] {X, X, 5, X, 3, X, 4, X, X},
        new Integer[] {X, 4, 9, 7, 1, X, X, X, X}
    };
    
    Integer[][] SUDOKU_3X3_DIFF = new Integer[][] {
        new Integer[] {X, X, X, 2, X, X, X, 5, X},
        new Integer[] {X, 4, 6, 5, X, 8, X, X, 1},
        new Integer[] {X, X, X, X, 7, 1, 4, X, X},
        new Integer[] {X, 9, X, X, X, 5, X, X, X},
        new Integer[] {1, X, 5, X, 2, 3, 8, X, X},
        new Integer[] {2, 6, 8, X, X, X, X, 9, X},
        new Integer[] {6, X, X, 3, X, 2, 5, 4, X},
        new Integer[] {X, X, 1, X, 8, 6, X, X, 3},
        new Integer[] {4, X, X, X, 5, X, 1, 8, X}
    };
}
