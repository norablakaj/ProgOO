package model;

import java.awt.*;

/**
 * Holds information about each player.
 */
public class Player {

    /**
     * The id is used to identify the player.
     */
    private final int id;

    /**
     * The color of the chip.
     */
    private final Color color;

    /**
     * The name of the player (this is not unique!)
     */
    private final String name;

    /**
     * Constructor
     * @param id s.o.
     * @param color s.o.
     * @param name s.o.
     */
    public Player(int id, Color color, String name){

        this.id = id;
        this.color = color;
        this.name = name;
    }

    /**
     * Getter.
     * @return the id.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter.
     * @return the color of the chip.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter.
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }
}
