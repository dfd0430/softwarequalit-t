package connect4;

public class Board {

    public static final int COLUMNS = 7;
    public static final int ROWS = 6;
    private static final int CONNECT = 4;

    private final Player[][] cells = new Player[ROWS][COLUMNS];

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
