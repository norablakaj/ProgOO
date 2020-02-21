package model;

/**
 * Characterizes the chips, which will be added to a board.
 */
public class Chip {

    /**
     * Chips always belong to a player.
     */
    private final Player player;

    /**
     * Constructor for the chip.
     * @param player it belongs to.
     */
    public Chip(Player player){

        this.player = player;
    }

    /**
     * Getter.
     * @return the player.
     */
    public Player getPlayer(){
        return player;
    }
}
