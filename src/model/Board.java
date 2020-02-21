package model;

/**
 * Characterizes the board, which is filled with chips from Chip.
 */
public class Board {

    /**
     *  Number of columns and rows.
     */
    private int rows;
    private int columns;

    /**
     * The board is represented by a two-dimensional array of chips.
     */
    private Chip[][] board;

    /**
     * Constructor for the board.
     * @param rows ->.
     * @param columns both determine the size of the board.
     */
    public Board(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
        board = new Chip[rows][columns];
    }

    /**
     * Getter.
     * @return a board.
     */
    public Chip[][] getBoard() {

        return board;
    }

    /**
     * Checks, whether a move is valid.
     * @param placedRow ->.
     * @param placedColumn determine the position of the chip.
     * @return Is this move valid?
     */
    public boolean isValid(int placedRow, int placedColumn) {

        if (placedColumn > columns || placedRow > rows || placedColumn < 0 || placedRow < 0
                || placedRow != 0 && board[placedRow - 1][placedColumn] == null) {

            return false;
        }

        return board[placedRow][placedColumn] == null;
    }

    /**
     * Sets a chip at [placedRow, placedColumn].
     * @param placedRow for the position.
     * @param placedColumn for the position.
     * @param chip to be placed.
     */
    public void setChip(int placedRow, int placedColumn, Chip chip) {

        if (isValid(placedRow, placedColumn)) {

            board[placedRow][placedColumn] = chip;
        }
    }

    /**
     * Checks, whether a game is already won by the current move.
     * This required the helper methods below
     * @return Is the game won?
     */
    public boolean isWon(){
        return true;
    }

    private boolean isWonRow(){

    }

    private boolean isWonColumn(){

    }

    private boolean isWonDiagonal(){

    }
}
