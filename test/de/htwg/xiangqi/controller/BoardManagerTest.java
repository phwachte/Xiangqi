package de.htwg.xiangqi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Square;
import de.htwg.xiangqi.persistence.IDataAccessObject;

public class BoardManagerTest {

	BoardManager bm;
	Square[][] board;

	@Before
	public void setUp() {
		bm = new BoardManager();
	}

	@Test
	public void testInputMove() {
		bm.setStartBoard();
		assertFalse(bm.inputMove("8 0 7 0"));
		assertFalse(bm.inputMove("8 9 8 8"));
		assertFalse(bm.inputMove("0 0 1 0"));
		assertFalse(bm.inputMove("9 0 8 1"));
		assertFalse(bm.inputMove("9 0 6 0"));
		assertFalse(bm.inputMove("9 0 8 0"));
		assertFalse(bm.inputMove("8 0 8 1"));
		assertFalse(bm.inputMove("0 0 0 9"));
		assertFalse(bm.inputMove("2 1 9 1"));
		assertFalse(bm.inputMove("2 1 g 1"));
		bm.getBoard().getSquareMatrix()[0][4].getPiece().setIsCaptured(true);
		assertTrue(bm.inputMove("8 0 8 1"));
		bm.getBoard().getSquareMatrix()[0][4].getPiece().setIsCaptured(false);
		bm.getBoard().getSquareMatrix()[9][4].getPiece().setIsCaptured(true);
		assertTrue(bm.inputMove("0 0 1 0"));
	}

	@Test
	public void testGetMessage() {
		assertNull(bm.getMessage());
	}

	@Test
	public void testGetBoard() {
		assertNotNull(bm.getBoard().getSquareMatrix());
		board = new Square[10][9];
		assertSame(board.length, bm.getBoard().getSquareMatrix().length);
	}

	@Test
	public void testMoveCounter() {
		assertEquals(1, bm.getBoard().getMoveCounter());
		assertEquals(1, bm.getPlayersTurn());
		bm.getBoard().increaseMoveCounter();
		assertEquals(2, bm.getBoard().getMoveCounter());
		assertEquals(0, bm.getPlayersTurn());
		bm.getBoard().increaseMoveCounter();
		assertEquals(3, bm.getBoard().getMoveCounter());
		assertEquals(1, bm.getPlayersTurn());
		bm.getBoard().increaseMoveCounter();
		assertEquals(4, bm.getBoard().getMoveCounter());
		assertEquals(0, bm.getPlayersTurn());
	}

	@Test
	public void testSetStartBoard() {
		bm.setStartBoard();
		assertEquals('H', bm.getBoard().getSquareMatrix()[9][7].getPiece()
				.getPieceType());
		assertEquals('S', bm.getBoard().getSquareMatrix()[3][6].getPiece()
				.getPieceType());
	}

	@Test
	public void testWinnerMessage() {
		bm.setStartBoard();
		bm.getBoard().getSquareMatrix()[9][4].getPiece().setIsCaptured(true);
		assertEquals("Congratulation player black, you are the winner!",
				bm.winnerMessage());
		bm.getBoard().getSquareMatrix()[9][4].getPiece().setIsCaptured(false);
		bm.getBoard().getSquareMatrix()[0][4].getPiece().setIsCaptured(true);
		assertEquals("Congratulation player red, you are the winner!",
				bm.winnerMessage());
	}

	@Test
	public void testGetTUIOutput() {
		bm.setStartBoard();
		assertEquals("BR", bm.getTUIOutput(0, 0));
		assertEquals("RR", bm.getTUIOutput(9, 0));
		assertEquals("  ", bm.getTUIOutput(1, 0));
	}

	@Test
	public void testPieceAtPoint() {
		bm.setStartBoard();
		assertNull(bm.pieceAtPoint(1, 0));
		assertNotNull(bm.pieceAtPoint(0, 0));
	}
	
	@Test
	public void testSaveLoadDeleteGame() {
		bm.saveGame();
		List<Board> list = bm.loadSaveGames();
		int size = list.size();
		assertTrue(size > 0);
		bm.deleteGame(bm.getBoard().getSessionName());
		list = bm.loadSaveGames();
		assertTrue(list.size() == size - 1);
	}
	
	@Test
	public void testGetDAO() {
		
	}

//	@After
	public void tearDown() {
		bm.close();
	}

}
