package connect4;

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

    public Player other() {
        return this == RED ? YELLOW : RED;
    }
}
