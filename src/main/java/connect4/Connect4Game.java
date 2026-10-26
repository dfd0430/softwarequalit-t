// SPDX-FileCopyrightText: 2026 Michael Dippel
//
// SPDX-License-Identifier: MIT

package connect4;

/**
 * Orchestrates a single Connect Four match: whose turn it is, applying a
 * move to the {@link Board} and deciding whether the game has ended.
 *
 * <p>Kept free of any console I/O so the game rules can be unit tested
 * without simulating user input.</p>
 */
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

    /**
     * Applies a move for the current player and updates game state.
     *
     * @param column zero-based column index
     * @throws IllegalArgumentException if the move is illegal
     * @throws IllegalStateException if the game is already over
     */
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
