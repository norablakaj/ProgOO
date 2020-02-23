package controller;

import model.Board;
import model.Direction;
import model.Player;
import view.ColorScheme;
import view.LighthouseView;
import view.ScreenView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *  Controller.
 *  Managing the game loop and the setup.
 */
public class Controller implements KeyListener {

    /**
     * board size.
     */
    private static final int COLUMNS = 7; // number of Columns.
    private static final int ROWS = 6; // number of Rows.

    /**
     * players.
     */
    private final Player player0;
    private final Player player1;

    /**
     * board.
     */
    private Board board;

    /**
     * both views (computer screen and lighthouse).
     */
    private ScreenView screenView;
    private LighthouseView lighthouseView;

    private Direction direction;

    /**
     * Constructor.
     * Holds most of the setup.
     */
    public Controller() {

        //Initialising the players
        player0 = new Player(0, ColorScheme.PLAYER_0_COLOR, "Ava");
        player1 = new Player(1, ColorScheme.PLAYER_1_COLOR, "Björn");

        //Initialising the board
        board = new Board(ROWS, COLUMNS);

        screenView = new ScreenView();
        lighthouseView = new LighthouseView();

        loop();
    }

    public void setup(){

    }

    /**
     * game loop.
     */
    public void loop(){

        boolean isRunning = true;
        boolean player1turn = false;

        while (isRunning){

            screenView.drawBoard(board);
            lighthouseView.connect();

            if (player1turn){

            }

            // Checking, whether the game is still not won
            if (board.getWinner() != null){

                isRunning = false;
            }

            // Waiting some time until the board is drawn again
            // (lower the frame rate)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    private int getPlayerRow(KeyEvent keyEvent){

        int row = 0;

        if (direction.getDirection(keyEvent) == Direction.RIGHT){
            row = (row + 1) % ROWS;
        }
        if (direction.getDirection(keyEvent) == Direction.LEFT){
            row = (row + 1) % ROWS;
        }

        return row;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}