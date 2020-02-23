package view;

import lighthouse.LighthouseDisplay;

import java.io.IOException;

public class LighthouseView {

    public void connect(){

        LighthouseDisplay display = null;

        // Try connecting to the display
        try {
            display = LighthouseDisplay.getDisplay();
            display.setUsername("stu214328");
            display.setToken("API-TOK_X5km-kkLx-MCNp-903U-UoAX");
        } catch(Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }

        // Send data to the display
        try {
            // This array contains for every window (14 rows, 28 columns) three
            // bytes that define the red, green, and blue component of the color
            // to be shown in that window. See documentation of LighthouseDisplay's
            // send(...) method.
            byte[] data = new byte[14 * 28 * 3];

            // Fill array
            display.sendImage(data);

        }catch(IOException e){
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
