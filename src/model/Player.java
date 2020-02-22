package model;

import java.awt.*;

/**
 * Holds information about each player.
 */
public class Player {

    private final int id;
    private final Color color;
    private final String name; // the Name of the player.

    public Player(int id, Color color, String name){

        this.id = id;
        this.color = color;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
