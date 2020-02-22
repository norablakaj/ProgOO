package model;

/**
 * Characterizes the board, which is filled with chips from Chip.
 */
public class Board {

    /**
     * Number of columns and rows.
     */
    private int rows;
    private int columns;

    /**
     * The board is represented by a two-dimensional array of chips.
     */
    private Chip[][] board;

    /**
     * Constructor for the board.
     *
     * @param rows    ->.
     * @param columns both determine the size of the board.
     */
    public Board(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
        board = new Chip[rows][columns];
    }

    /**
     * Getter.
     *
     * @return a board.
     */
    public Chip[][] getBoard() {

        return board;
    }

    /**
     * Checks, whether a move is valid.
     *
     * @param placedRow    ->.
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
     *
     * @param placedRow    for the position.
     * @param placedColumn for the position.
     * @param chip         to be placed.
     * @return if the chip was actually placed.
     */
    public boolean setChip(int placedRow, int placedColumn, Chip chip) {

        if (isValid(placedRow, placedColumn)) {

            board[placedRow][placedColumn] = chip;
            return true;
        }

        return false;
    }

    /**
     * Checks, whether a game is already won by the current move.
     * This required the helper methods below
     *
     * @return the winner or null, if there is none.
     */
    public Player getWinner() {

        Player winnerRow = isWonRow();
        Player winnerColumn = isWonColumn();
        Player winnerDiagonal = isWonDiagonal();

        if (winnerRow != null) {
            return winnerRow;
        } else if (winnerColumn != null) {
            return winnerColumn;
        } else {
            return winnerDiagonal;
        }
    }

    private Player isWonRow() {

        for (int column = 0; column < columns; column++) {

            Player currentPlayer;
            if (board[0][column] == null) {
                currentPlayer = null;
            } else {
                currentPlayer = board[0][column].getPlayer();
            }

            int counter = 0;

            for (int row = 0; row < rows; row++) {

                if (board[row][column] != null) {

                    if (currentPlayer.equals(board[row][column].getPlayer())) {

                        // another chip was found next to the first one (or it is the first itself)
                        counter++;

                        if (counter == 4) {
                            return currentPlayer;
                        }
                    } else {

                        // chips next to each other belonged to different players
                        counter = 1;
                        currentPlayer = board[row][column].getPlayer();
                    }
                } else {

                    // no chips at this point
                    counter = 0;
                }
            }
        }

        return null;
    }

    private Player isWonColumn() {

        for (int row = 0; row < rows; row++) {

            Player currentPlayer;
            if (board[row][0] == null) {
                currentPlayer = null;
            } else {
                currentPlayer = board[row][0].getPlayer();
            }

            int counter = 0;

            for (int column = 0; column < rows; column++) {

                if (board[row][column] != null) {

                    if (currentPlayer.equals(board[row][column].getPlayer())) {

                        // another chip was found next to the first one (or it is the first itself)
                        counter++;

                        if (counter == 4) {
                            return currentPlayer;
                        }
                    } else {

                        // chips next to each other belonged to different players
                        counter = 1;
                        currentPlayer = board[row][column].getPlayer();
                    }
                } else {

                    // no chips at this point
                    counter = 0;
                }
            }
        }

        return null;
    }

    private Player isWonDiagonal() {

        // look at every spot
        for (int row = 0; row < rows - 3; row++) {
            for (int column = 0; column < columns - 3; column++) {

                if (board[row][column] == null) {
                    continue;
                }
                // there is no empty spot or other chip yet
                boolean isLineUp = true;
                boolean isLineDown = true;

                Player currentPlayer = board[row][column].getPlayer();

                for (int diagonal = 0; diagonal < 4; diagonal++) {

                    if (!currentPlayer.equals(board[row + diagonal][column + diagonal].getPlayer())) {
                        isLineUp = false;
                    }
                    if (!currentPlayer.equals(board[row + 3 - diagonal][column + diagonal].getPlayer())) {
                        isLineDown = false;
                    }
                }

                if (isLineUp || isLineDown) {
                    return currentPlayer;
                }
            }
        }

        return null;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Player getChipPlayer(int row, int column) {

        // empty spot
        if (board[row][column] == null) {
            return null;
        }
        // player
        else {
            return board[row][column].getPlayer();
        }
    }
}
