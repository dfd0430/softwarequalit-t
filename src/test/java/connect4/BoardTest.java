// SPDX-FileCopyrightText: 2026 Michael Dippel
//
// SPDX-License-Identifier: MIT

package connect4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit 5 tests for {@link Board}.
 */
class BoardTest {

    @Test
    void dropStacksFromBottom() {
        Board board = new Board();
        int firstRow = board.drop(0, Player.RED);
        int secondRow = board.drop(0, Player.YELLOW);
        assertEquals(Board.ROWS - 1, firstRow);
        assertEquals(Board.ROWS - 2, secondRow);
    }

    @Test
    void droppingIntoFullColumnThrows() {
        Board board = new Board();
        for (int i = 0; i < Board.ROWS; i++) {
            board.drop(0, Player.RED);
        }
        assertThrows(IllegalArgumentException.class, () -> board.drop(0, Player.YELLOW));
    }

    @Test
    void horizontalFourInARowIsAWin() {
        Board board = new Board();
        int row = -1;
        for (int column = 0; column < 4; column++) {
            row = board.drop(column, Player.RED);
        }
        assertTrue(board.isWinningMove(row, 3));
    }

    @Test
    void verticalFourInARowIsAWin() {
        Board board = new Board();
        int row = -1;
        for (int i = 0; i < 4; i++) {
            row = board.drop(0, Player.YELLOW);
        }
        assertTrue(board.isWinningMove(row, 0));
    }

    @Test
    void diagonalFourInARowIsAWin() {
        Board board = new Board();
        board.drop(0, Player.RED);

        board.drop(1, Player.YELLOW);
        board.drop(1, Player.RED);

        board.drop(2, Player.YELLOW);
        board.drop(2, Player.YELLOW);
        board.drop(2, Player.RED);

        board.drop(3, Player.YELLOW);
        board.drop(3, Player.YELLOW);
        board.drop(3, Player.YELLOW);
        int row = board.drop(3, Player.RED);

        assertTrue(board.isWinningMove(row, 3));
    }

    @Test
    void boardReportsFullOnceEveryColumnIsFull() {
        Board board = new Board();
        for (int column = 0; column < Board.COLUMNS; column++) {
            for (int i = 0; i < Board.ROWS; i++) {
                board.drop(column, (i + column) % 2 == 0 ? Player.RED : Player.YELLOW);
            }
        }
        assertTrue(board.isFull());
    }
}
