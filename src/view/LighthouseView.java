package view;

import lighthouse.LighthouseDisplay;
import model.Board;

import java.awt.*;
import java.io.IOException;

public class LighthouseView {

    private static final String USERNAME = "stu214328";
    private static final String TOKEN = "API-TOK_3S5X-ET/D-wnZS-qF1j-i4ew";

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

            for (int i = 0; i < HOCHHAUS_X; i++){
                for(int j = 0; j < HOCHHAUS_Y; j++){

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
            System.out.println(lighthouseDisplay.isConnected());
            lighthouseDisplay.sendImage(data);

        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void drawBoard(Board board){
        pixels[6][9] = Color.PINK;
        sendData();
    }
}
