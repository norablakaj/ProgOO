package view;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import model.Board;
import model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Screen view.
 * Graphics program for a computer.
 */
public class ScreenView extends GraphicsProgram {

    /**
     * Dimensions of the screen.
     */
    private static final int SCREEN_HEIGHT = 650;
    private static final int SCREEN_WIDTH = 700;

    /**
     * Draws the board into the screen.
     * @param board to be drawn.
     */
    public void drawBoard(Board board) {

        // drawing the background
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        int screenWidth = getWidth();
        int screenHeight = getHeight();
        drawBackground();

        // dimensions of the board
        int boardRows = board.getRows();
        int boardColumns = board.getColumns();

        // dimension of the chip
        double chipRadius = 0.8 * screenWidth / boardColumns;

        // drawing the chips
        for (int row = 0; row < boardRows; row++) {
            for (int column = 0; column < boardColumns; column++) {

                // player a chip belongs to
                Player player = board.getChipPlayer(row, column);

                // position of the chip
                int chipY = row * (SCREEN_HEIGHT - 140) / boardRows + 60;
                int chipX = column * SCREEN_WIDTH / boardColumns + 10;

                drawChip(chipRadius, chipX, chipY, player);
            }
        }
    }

    /**
     * Drawing the board.
     */
    private void drawBackground() {

        setBackground(ColorScheme.BOARD_COLOR);
        setVisible(true);
    }

    /**
     * Drawing a chip.
     * @param radius of the chip.
     * @param chipX the chip is placed at.
     * @param chipY the chip is placed at.
     * @param player the chip belongs to or null if there is none.
     */
    private void drawChip(double radius, int chipX, int chipY, Player player) {

        Color chipColor;

        // empty spot
        if (player == null){

            chipColor = ColorScheme.EMPTY_CHIP_COLOR;
        }
        // player already set a chip at this spot
        else {

            chipColor = player.getColor();
        }

        GOval chipOval = new GOval(chipX, chipY, radius, radius);
        chipOval.setColor(chipColor);
        chipOval.setFilled(true);

        add(chipOval);
    }

    /**
     * JFrame popping up whenever a game ended.
     * @param winner which is shown on screen (if winner == null, it says, that the game ended).
     */
    public void showWinner(Player winner) {

        JFrame parent = new JFrame();

        if(winner == null){
            JOptionPane.showMessageDialog(parent, "Nobody has won.");
        } else {
            JOptionPane.showMessageDialog(parent, "The winner is " + winner.getName());
        }
    }

}