package model;

/**
 * Characterizes the chips, which will be added to a board.
 */
public class Chip {

    /**
     * Chips always belong to a player.
     */
    private final int player;

    /**
     * Constructor for the chip.
     * @param player it belongs to.
     */
    public Chip(int player){

        // There should only be two players (can't be played otherwise)
        assert(player == 1 || player == 0);
        this.player = player;
    }

    /**
     * Getter.
     * @return the player.
     */
    public int getPlayer(){
        return player;
    }
}
