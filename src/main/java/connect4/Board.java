// SPDX-FileCopyrightText: 2026 Michael Dippel
//
// SPDX-License-Identifier: MIT

package connect4;

/**
 * The Connect Four game board: a grid of {@link Player} discs (or empty
 * cells) with the classic 7 columns x 6 rows dimensions.
 *
 * <p>The board owns all rules about where a disc may land and whether a
 * move produces a line of four. It has no knowledge of terminal I/O so it
 * can be tested in isolation.</p>
 */
public class Board {

    public static final int COLUMNS = 7;
    public static final int ROWS = 6;
    private static final int CONNECT = 4;

    private final Player[][] cells = new Player[ROWS][COLUMNS];

    /**
     * Drops a disc for {@code player} into {@code column}.
     *
     * @return the row the disc landed in
     * @throws IllegalArgumentException if the column is out of range or full
     */
    public int drop(int column, Player player) {
        if (column < 0 || column >= COLUMNS) {
            throw new IllegalArgumentException("Column out of range: " + column);
        }
        for (int row = ROWS - 1; row >= 0; row--) {
            if (cells[row][column] == null) {
                cells[row][column] = player;
                return row;
            }
        }
        throw new IllegalArgumentException("Column is full: " + column);
    }

    public boolean isColumnFull(int column) {
        return cells[0][column] != null;
    }

    public boolean isFull() {
        for (int column = 0; column < COLUMNS; column++) {
            if (!isColumnFull(column)) {
                return false;
            }
        }
        return true;
    }

    public Player get(int row, int column) {
        return cells[row][column];
    }

    /**
     * @return true if the last disc placed at {@code (row, column)} completes
     *     a horizontal, vertical or diagonal line of four for its owner
     */
    public boolean isWinningMove(int row, int column) {
        Player player = cells[row][column];
        if (player == null) {
            return false;
        }
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] direction : directions) {
            int count = 1;
            count += countDirection(row, column, direction[0], direction[1], player);
            count += countDirection(row, column, -direction[0], -direction[1], player);
            if (count >= CONNECT) {
                return true;
            }
        }
        return false;
    }

    private int countDirection(int row, int column, int rowStep, int colStep, Player player) {
        int count = 0;
        int r = row + rowStep;
        int c = column + colStep;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && cells[r][c] == player) {
            count++;
            r += rowStep;
            c += colStep;
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < ROWS; row++) {
            sb.append("|");
            for (int column = 0; column < COLUMNS; column++) {
                Player player = cells[row][column];
                sb.append(player == null ? ' ' : player.getSymbol()).append('|');
            }
            sb.append(System.lineSeparator());
        }
        sb.append(' ');
        for (int column = 1; column <= COLUMNS; column++) {
            sb.append(column).append(' ');
        }
        return sb.toString();
    }
}
