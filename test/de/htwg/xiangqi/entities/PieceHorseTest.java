package de.htwg.xiangqi.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Piece.Player;

public class PieceHorseTest {

	Board b;
	Square[][] board;
	
	@Before
	public void setUp() {
		b = new Board();
		board = b.getBoard();
		b.fillBoard();
	}
	
	@Test
	public void testValidMove() {
		board[7][2] = new Square(new PieceHorse(7, 2, Player.RED));
		board[7][6] = new Square(new PieceHorse(7, 6, Player.RED));
		board[7][5] = new Square(new PieceCannon(7, 5, Player.RED));
		board[7][7] = new Square(new PieceCannon(7, 7, Player.RED));
		board[6][6] = new Square(new PieceCannon(6, 6, Player.RED));
		board[8][6] = new Square(new PieceCannon(8, 6, Player.RED));
		assertEquals(true, board[7][2].getPiece().validMove(board, 5, 3));
		assertEquals(true, board[7][2].getPiece().validMove(board, 5, 1));
		assertEquals(true, board[7][2].getPiece().validMove(board, 6, 4));
		assertEquals(true, board[7][2].getPiece().validMove(board, 8, 4));
		assertEquals(true, board[7][2].getPiece().validMove(board, 9, 1));
		assertEquals(true, board[7][2].getPiece().validMove(board, 9, 3));
		assertEquals(true, board[7][2].getPiece().validMove(board, 6, 0));
		assertEquals(true, board[7][2].getPiece().validMove(board, 8, 0));
		
		assertEquals(false, board[7][6].getPiece().validMove(board, 6, 8));
		assertEquals(false, board[7][6].getPiece().validMove(board, 9, 5));
		assertEquals(false, board[7][6].getPiece().validMove(board, 6, 4));
		assertEquals(false, board[7][6].getPiece().validMove(board, 5, 7));
		assertEquals(false, board[7][2].getPiece().validMove(board, 5, 2));
		assertEquals(false, board[7][2].getPiece().validMove(board, 7, 2));
		assertEquals(false, board[7][2].getPiece().validMove(board, 9, 2));
		assertEquals(false, board[7][2].getPiece().validMove(board, 7, 0));
		assertEquals(false, board[7][2].getPiece().validMove(board, 7, 4));
	}

}
