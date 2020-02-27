package view;

import lighthouse.LighthouseDisplay;
import model.Board;
import model.Player;

import java.awt.*;
import java.io.IOException;

public class LighthouseView {

    private static final String USERNAME = "stu214328";
    private static final String TOKEN = "API-TOK_JLA/-wLIv-uelL-MMqm-Wie7";

    private static final int HOCHHAUS_X = 14;
    private static final int HOCHHAUS_Y = 28;

    private Color[][] pixels;
    private LighthouseDisplay lighthouseDisplay;

    public LighthouseView() {

        pixels = new Color[HOCHHAUS_X][HOCHHAUS_Y];
        lighthouseDisplay = getDisplay(USERNAME, TOKEN);
    }

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

    public void sendData() {

        // Send data to the display
        try {
            // This array contains for every window (14 rows, 28 columns) three
            // bytes that define the red, green, and blue component of the color
            // to be shown in that window. See documentation of LighthouseDisplay's
            // send(...) method.
            byte[] data = new byte[HOCHHAUS_X * HOCHHAUS_Y * 3];

            // Array converter
            for (int i = 0; i < HOCHHAUS_X; i++) {
                for (int j = 0; j < HOCHHAUS_Y; j++) {

                    int pos = (j * HOCHHAUS_X + i) * 3;

                    if (pixels[i][j] != null) {
                        data[pos] = (byte) pixels[i][j].getRed();
                        data[pos + 1] = (byte) pixels[i][j].getGreen();
                        data[pos + 2] = (byte) pixels[i][j].getBlue();
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

    public void drawBoard(Board board) {

        // Example to test the view
        //pixels[6][7] = Color.PINK;

        // number of borders for the game
        int rowBorder = board.getRows() + 1;
        int columnBorders = board.getColumns() + 1;

        // test value
        int indicatorHeight = 1;

        // dimensions of the chip without the borders and indicator
        int chipWidth = (HOCHHAUS_X - columnBorders) / board.getColumns();
        int chipHeight = (HOCHHAUS_Y - rowBorder - indicatorHeight) / board.getRows();

        //dimensions of the board
        int rows = board.getRows();
        int columns = board.getColumns();

        // drawing the chips
        // skipping windows of the same color and borders
        for (int cRow = 1; cRow < rows; rows++) {
            for (int cColumn = 1; cColumn < columns; cColumn++) {

                Player player = board.getChipPlayer(cRow, cColumn);

                int chipRow = cRow + chipHeight + rowBorder;
                int chipColumn = cColumn + chipWidth + columnBorders;

                drawChip(player, chipRow, chipColumn, chipWidth, chipHeight);
            }
        }

        pixels[2][4]=ColorScheme.PLAYER_0_COLOR;

        sendData();
    }

    /**
     * Draws chips
     *
     * @param player     who owns the chip.
     * @param chipRow    the chip is placed at.
     * @param chipColumn the chip is placed at.
     */
    public void drawChip(Player player, int chipRow, int chipColumn, int chipWidth, int chipHeight) {

        Color chipColor;
        if (player == null) {
            chipColor = ColorScheme.EMPTY_CHIP_COLOR;
        } else {
            chipColor = player.getColor();
        }

        // drawing one chip
        for (int i = chipRow; i < chipWidth; i++) {
            for (int j = chipColumn; j < chipHeight; j++) {
                pixels[i][j] = chipColor;
            }
        }

    }
}
