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
    private boolean isValid(int placedRow, int placedColumn) {

        if (placedColumn > columns || placedRow > rows || placedColumn < 0 || placedRow < 0
                || placedRow != rows - 1 && board[placedRow + 1][placedColumn] == null) {

            return false;
        }

        return board[placedRow][placedColumn] == null;
    }

    /**
     * Finds the first empty row, a chip can be placed at.
     * @param testColumn is the selected column.
     * @return which row is empty and valid or -1 if there is none.
     */
    private int firstEmptyRow(int testColumn) {

        // checking all rows from the bottom up
        for (int testRow = rows - 1; testRow >= 0; testRow--) {
            if (isValid(testRow, testColumn)) {
                return testRow;
            }
        }

        // no empty row was found
        return -1;
    }

    /**
     * Sets a chip at [placedRow, placedColumn].
     *
     * @param placedColumn for the position.
     * @param chip         to be placed.
     * @return if the chip was actually placed.
     */
    public boolean setChip(int placedColumn, Chip chip) {

        int placedRow = firstEmptyRow(placedColumn);

        if (placedRow != -1) {
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

    /**
     * Helper for getWinner().
     * Just checks the columns.
     * @return the winner.
     */
    private Player isWonColumn() {

        for (int column = 0; column < columns; column++) {

            Player currentPlayer;
            if (board[rows - 1][column] == null) {
                continue;
            } else {
                currentPlayer = board[rows - 1][column].getPlayer();
            }

            int counter = 0;

            for (int row = rows - 1; row >= 0; row--) {

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

    /**
     * Helper for getWinner().
     * Just checks the rows.
     * @return the winner.
     */
    private Player isWonRow() {

        for (int row = rows - 1; row >= 0; row--) {

            Player currentPlayer;

            if (board[row][0] == null) {
                continue;
            } else {
                currentPlayer = board[row][0].getPlayer();
            }

            int counter = 0;

            for (int column = 0; column < columns; column++) {

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

    /**
     * Helper for getWinner().
     * Just checks the diagonals.
     * @return the winner.
     */
    private Player isWonDiagonal() {

        // look at every spot
        for (int row = rows - 1; row >= 3; row--) {
            for (int column = 0; column < columns - 3; column++) {

                if (board[row][column] == null) {
                    continue;
                }
                // there is no empty spot or other chip yet
                boolean isLineUp = true;

                Player currentPlayer = board[row][column].getPlayer();

                if (currentPlayer == null) {
                    continue;
                }

                for (int diagonal = 0; diagonal < 4; diagonal++) {

                    if (!currentPlayer.equals(getChipPlayer(row - diagonal, column + diagonal))) {
                        isLineUp = false;
                    }
                }

                if (isLineUp) {
                    return currentPlayer;
                }
            }
        }

        for (int row = rows - 4; row >= 0; row--) {
            for (int column = 0; column < columns - 3; column++) {

                if (board[row][column] == null) {
                    continue;
                }
                // there is no empty spot or other chip yet
                boolean isLineDown = true;

                Player currentPlayer = board[row][column].getPlayer();

                if (currentPlayer == null) {
                    continue;
                }

                for (int diagonal = 0; diagonal < 4; diagonal++) {

                    if (!currentPlayer.equals(getChipPlayer(row + diagonal, column + diagonal))) {
                        isLineDown = false;
                    }
                }

                if (isLineDown) {
                    return currentPlayer;
                }
            }
        }

        return null;
    }


    /**
     * Getter.
     * @return columns.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Getter.
     * @return rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Checks, which player placed a chip at a certain position.
     * @param row ->.
     * @param column the position the chip is placed at.
     * @return the player the chip belongs to or null if there is none.
     */
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

    /**
     * Checks if a game can still be won.
     * @return if this is possible.
     */
    public boolean winnerPossible(){

        for (int i = 0; i < columns; i++){
            // Is there an column left, that is not filled?
            if(isValid(rows - 1, i)){
                return true;
            }
        }
        return false;

    }
}


