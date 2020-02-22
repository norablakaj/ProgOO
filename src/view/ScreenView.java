package view;

import acm.program.GraphicsProgram;
import model.Board;
import model.Player;

/**
 * Screen view.
 * Graphics program for a computer.
 */
public class ScreenView extends GraphicsProgram {

    private static final int SCREEN_HEIGHT = 400;
    private static final int SCREEN_WIDTH = 600;

    public void drawBoard(Board board) {

        // drawing the background
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        drawBackground();

        // dimensions of the board
        int boardRows = board.getRows();
        int boardColumns = board.getColumns();

        // drawing the chips
        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {

                // player a chip belongs to
                Player player = board.getChipPlayer(row, column);

                drawChip(boardRows, boardColumns, row, column, player);
            }
        }
    }

    private void drawBackground() {

        setBackground(ColorScheme.BOARD_COLOR);
    }

    private void drawChip(int rows, int columns, int placedRow, int placedColumn, Player player) {

        // dimensions of the chip
        

        // empty spot
        if (player == null){


        }
    }

}