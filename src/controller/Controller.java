package controller;

import model.Board;
import model.Chip;
import model.Direction;
import model.Player;
import view.ColorScheme;
import view.LighthouseView;
import view.ScreenView;

/**
 * Controller.
 * Managing the game loop and the setup.
 */
public class Controller implements Runnable{

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
     * Current game state.
     */
    private boolean player1turn = false;
    private int playerColumn = 0;

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

        KeyboardInput keyboardInput = new KeyboardInput(this);
        screenView.addKeyListener(keyboardInput);
        loop();
    }

    /**
     * game loop.
     */
    public void loop() {

        boolean isRunning = true;

        while (isRunning) {

            screenView.drawBoard(board);
            lighthouseView.drawBoard(board);

            // Checking, if the game is still not won
            if (board.getWinner() != null || !board.winnerPossible()) {

                isRunning = false;

                if (!board.winnerPossible()){
                    // Nobody has won
                    screenView.showWinner(null);
                } else {
                    // Announcing the winner
                    // ! Switching the players !
                    Player winner = player1turn
                            ? player0
                            : player1;
                    screenView.showWinner(winner);
                    //lighthouseView.showWinner(winner);
                    System.out.println("Spieler " + winner.getName() + " hat gewonnen.");
                }
            }

            // Waiting some time until the board is drawn again
            // (lower the frame rate)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void placeChip() {

        Chip chip;

        if (player1turn) {
            chip = new Chip(player1);
        } else {
            chip = new Chip(player0);
        }

        boolean placingSuccessful = board.setChip(playerColumn, chip);

        if(placingSuccessful){
            playerColumn = 0;
            player1turn = !player1turn;
        } else {
            System.err.println("Spalte ist bereits voll.");
        }

    }

    public void moveRight() {

        playerColumn = (playerColumn + 1) % COLUMNS;
        System.out.println("Move to the right.");
    }

    public void moveLeft() {

        playerColumn = (playerColumn - 1 + COLUMNS) % COLUMNS;
        System.out.println("Move to the left.");
    }

    @Override
    public void run() {

    }
}