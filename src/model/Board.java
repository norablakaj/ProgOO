package model;

public class Board {

    private int rows;
    private int columns;

    private Chip[][] board;

    public Board(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;

        board = new Chip[rows][columns];
    }

    public Chip[][] getBoard() {

        return board;
    }

    public boolean isValid(int placedRow, int placedColumn) {

        if (placedColumn > columns || placedRow > rows || placedColumn < 0 || placedRow < 0
                || placedRow != 0 && board[placedRow - 1][placedColumn] == null) {

            return false;
        }

        return board[placedRow][placedColumn] == null;
    }

    public void setChip(int placedRow, int placedColumn, Chip chip) {

        if (isValid(placedRow, placedColumn)) {

            board[placedRow][placedColumn] = chip;
        }
    }

    //TODO
    public boolean isWon(){
        return true;
    }
}
