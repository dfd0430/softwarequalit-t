package connect4;

/**
 * The two players of a Connect Four game, each represented by a disc symbol
 * used when the board is printed to the terminal.
 */
public enum Player {
    RED('X'),
    YELLOW('O');

    private final char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    /**
     * @return the player who takes the next turn
     */
    public Player other() {
        return this == RED ? YELLOW : RED;
    }
}
