// temporary change for git diff test


// Yi-Chieh Chu(Carl Chu)
// CS 143
// HW #2: Sudoku #2 (isValid, isSolved)

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class MySudokuBoard {

    // field to store the board
    private char[][] board;

    // pre: fileName refers to a valid .sdk file with 9 lines of 9 characters
    // post: board is filled with characters from the file
    public MySudokuBoard(String fileName) throws FileNotFoundException {
        board = new char[9][9];

        Scanner input = new Scanner(new File(fileName));

        for (int row = 0; row < 9; row++) {
            String line = input.nextLine();
            for (int col = 0; col < 9; col++) {
                board[row][col] = line.charAt(col);
            }
        }

        input.close();
    }

// pre: board has been initialized with 9 rows and 9 columns
// post: returns true if the board contains only valid data and follows all Sudoku rules for rows, columns, and mini-squares;
// returns false otherwise
    public boolean isValid() {
        return validData()
            && validRows()
            && validCols()
            && validMiniSquares();
    }

    private boolean validData() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch != '.' && (ch < '1' || ch > '9')) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validRows() {
        for (int r = 0; r < 9; r++) {
            Set<Character> seen = new HashSet<>();
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch != '.') {
                    if (!seen.add(ch)) return false;
                }
            }
        }
        return true;
    }

    private boolean validCols() {
        for (int c = 0; c < 9; c++) {
            Set<Character> seen = new HashSet<>();
            for (int r = 0; r < 9; r++) {
                char ch = board[r][c];
                if (ch != '.') {
                    if (!seen.add(ch)) return false;
                }
            }
        }
        return true;
    }

    private boolean validMiniSquares() {
        for (int spot = 1; spot <= 9; spot++) {
            Set<Character> seen = new HashSet<>();
            char[][] mini = miniSquare(spot);

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    char ch = mini[r][c];
                    if (ch != '.') {
                        if (!seen.add(ch)) return false;
                    }
                }
            }
        }
        return true;
    }

    private char[][] miniSquare(int spot) {
        char[][] mini = new char[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                mini[r][c] =
                    board[(spot - 1) / 3 * 3 + r]
                         [(spot - 1) % 3 * 3 + c];
            }
        }
        return mini;
    }

// pre: board has been initialized with 9 rows and 9 columns
// post: returns true if the board is valid, contains no empty cells, and each value from '1' to '9' appears exactly nine times;
// returns false otherwise
    public boolean isSolved() {
        if (!isValid()) return false;

        Map<Character, Integer> count = new HashMap<>();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') return false;
                count.put(ch, count.getOrDefault(ch, 0) + 1);
            }
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            if (count.getOrDefault(ch, 0) != 9) {
                return false;
            }
        }

        return true;
    }
    
    
// Solve the Sudoku puzzle using recursive backtracking.
    // PRE: none
    // POST: returns true if the puzzle is solved; false if it cannot be solved. If true, board will contain the solved configuration.
    public boolean solve() {
        if (!isValid()) { 
            return false;
        }
        if (isSolved()) { 
            return true;
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char val = '1'; val <= '9'; val++) {
                        board[row][col] = val; 

                        if (isValid()) { 
                            if (solve()) { 
                                return true; 
                            }
                        }

                        board[row][col] = '.'; 
                    }
                    return false; 
                }
            }
        }
        return isSolved();
    }

    // pre: none
    // post: returns a formatted String representing the Sudoku board
    public String toString() {
        String result = "";

        for (int row = 0; row < 9; row++) {
            if (row == 3 || row == 6) {
                result += "------+-------+------\n";
            }

            for (int col = 0; col < 9; col++) {
                if (col == 3 || col == 6) {
                    result += "| ";
                }
                result += board[row][col] + " ";
            }
            result += "\n";
        }

        return result;
    }
}


/*
  ----jGRASP exec: java SudokuCheckerEngineV2
 Checking empty board...passed.
 Checking incomplete, valid board...passed.
 Checking complete, valid board...passed.
 Checking dirty data board...passed.
 Checking row violating board...passed.
 Checking col violating board...passed.
 Checking row&col violating board...passed.
 Checking mini-square violating board...passed.
 **** HORRAY: ALL TESTS PASSED ****
 
  ----jGRASP: Operation complete.
 
*/


