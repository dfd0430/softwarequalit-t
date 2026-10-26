package connect4;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Terminal entry point: reads column choices from standard input and prints
 * the board after every move until someone wins or the board is full.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Connect4Game game = new Connect4Game();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connect Four! Player " + Player.RED.getSymbol()
                    + " goes first. Columns are numbered 1-" + Board.COLUMNS + ".");
            System.out.println(game.getBoard());

            while (!game.isOver()) {
                System.out.print("Player " + game.getCurrentPlayer().getSymbol()
                        + ", choose a column (1-" + Board.COLUMNS + "): ");
                int column = readColumn(scanner);
                if (column < 0) {
                    continue;
                }
                try {
                    game.play(column);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                System.out.println(game.getBoard());
            }

            if (game.getWinner() != null) {
                System.out.println("Player " + game.getWinner().getSymbol() + " wins!");
            } else {
                System.out.println("It's a draw!");
            }
        }
    }

    /**
     * @return a zero-based column index, or -1 if the input was invalid
     */
    private static int readColumn(Scanner scanner) {
        try {
            int column = scanner.nextInt() - 1;
            if (column < 0 || column >= Board.COLUMNS) {
                System.out.println("Please enter a number between 1 and " + Board.COLUMNS + ".");
                return -1;
            }
            return column;
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Please enter a valid number.");
            return -1;
        }
    }
}
