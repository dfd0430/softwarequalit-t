package connect4;

public class Connect4Game {

    private final Board board = new Board();
    private Player currentPlayer = Player.RED;
    private Player winner;
    private boolean draw;

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isDraw() {
        return draw;
    }

    public boolean isOver() {
        return winner != null || draw;
    }

    public void play(int column) {
        if (isOver()) {
            throw new IllegalStateException("Game is already over");
        }
        int row = board.drop(column, currentPlayer);
        if (board.isWinningMove(row, column)) {
            winner = currentPlayer;
        } else if (board.isFull()) {
            draw = true;
        } else {
            currentPlayer = currentPlayer.other();
        }
    }
}
