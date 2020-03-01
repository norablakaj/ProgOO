package view;

import lighthouse.LighthouseDisplay;
import model.Board;
import model.Player;

import java.awt.*;
import java.io.IOException;

/**
 * Manages the connection to the Lighthouse.
 */
public class LighthouseView {

    /**
     * Connection to the Lighthouse.
     */
    private static final String USERNAME = "stu214328";
    private static final String TOKEN = "API-TOK_JLA/-wLIv-uelL-MMqm-Wie7";

    /**
     * Dimensions.
     */
    private static final int HOCHHAUS_X = 14;
    private static final int HOCHHAUS_Y = 28;

    /**
     * Array representing the pixels of the Lighthouse (in order).
     */
    private Color[][] pixels;
    private LighthouseDisplay lighthouseDisplay;

    /**
     * Constructor.
     */
    public LighthouseView() {

        pixels = new Color[HOCHHAUS_X][HOCHHAUS_Y];
        lighthouseDisplay = getDisplay(USERNAME, TOKEN);
    }

    /**
     * iLearn.
     * @param username ->.
     * @param token for the connection.
     * @return the current display.
     */
    public LighthouseDisplay getDisplay(String username, String token) {

        LighthouseDisplay display = null;

        // Try connecting to the display
        try {
            display = LighthouseDisplay.getDisplay();
            display.setUsername(username);
            display.setToken(token);
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        return display;
    }

    /**
     * Sends data to the display.
     */
    public void sendData() {

        try {
            // This array contains for every window (14 rows, 28 columns) three
            // bytes that define the red, green, and blue component of the color
            // to be shown in that window. See documentation of LighthouseDisplay's
            // send(...) method.
            byte[] data = new byte[HOCHHAUS_X * HOCHHAUS_Y * 3];

            // Array converter
            for (int i = 0; i < HOCHHAUS_Y; i++) {
                for (int j = 0; j < HOCHHAUS_X; j++) {

                    int pos = (j * HOCHHAUS_Y + i) * 3;

                    if (pixels[j][i] != null) {
                        data[pos] = (byte) pixels[j][i].getRed();
                        data[pos + 1] = (byte) pixels[j][i].getGreen();
                        data[pos + 2] = (byte) pixels[j][i].getBlue();
                    } else {
                        data[pos] = (byte) ColorScheme.BOARD_COLOR.getRed();
                        data[pos + 1] = (byte) ColorScheme.BOARD_COLOR.getGreen();
                        data[pos + 2] = (byte) ColorScheme.BOARD_COLOR.getBlue();
                    }
                }
            }
            // System.out.println(lighthouseDisplay.isConnected());
            lighthouseDisplay.sendImage(data);

        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Draws the board onto the lighthouse.
     * @param board which is drawn.
     */
    public void drawBoard(Board board) {

        // dimensions of the chip
        int chipWidth = HOCHHAUS_X / board.getColumns();
        int chipHeight = HOCHHAUS_Y / board.getRows();

        //dimensions of the board
        int rows = board.getRows();
        int columns = board.getColumns();

        for (int boardRow = 0; boardRow < rows; boardRow++){
            for (int boardColumn = 0; boardColumn < columns; boardColumn++){

                Player player = board.getChipPlayer(boardRow, boardColumn);

                if (player != null){

                    drawChip(player, boardRow * chipWidth, boardColumn * chipHeight + 1, chipWidth, chipHeight);
                }
            }
        }

        sendData();
    }

    /**
     * Draws chips.
     * @param player     who owns the chip.
     * @param chipRow    the chip is placed at.
     * @param chipColumn the chip is placed at.
     */
    public void drawChip(Player player, int chipRow, int chipColumn, int chipWidth, int chipHeight) {

        assert (player != null);
        Color chipColor = player.getColor();

        for (int i = 0; i < chipWidth - 1; i++){
            for (int j = 0; j < chipHeight - 1; j++){
                pixels[chipRow + i + 2][chipColumn + j] = chipColor;
            }
        }

    }
}
