// Yi-Chieh Chu(Carl Chu)
// CS 143
// HW #1: Sudoku Board Setup (testing class)

public class PlaySudoku {

    // pre: data1.sdk exists in the project directory
    // post: Sudoku board is printed to the console
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard("data1-1-1.sdk");
        System.out.println(board);
    }
}

/*
Sample Output (from JGrasp):

2 . . | 1 . 5 | . . 3
. 5 4 | . . . | 7 1 .
. 1 . | 2 . 3 | . 8 .
------+-------+------
6 . 2 | 8 . 7 | 3 . 4
. . . | . . . | . . .
1 . 5 | 3 . 9 | 8 . 6
------+-------+------
. 2 . | 7 . 1 | . 6 .
. 8 1 | . . . | 2 4 .
7 . . | 4 . 2 | . . 1
*/
