package de.htwg.xiangqi.entities;

public abstract class Piece {

	private static final int ZERO = 0;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SEVEN = 7;
	private static final int NINE = 9;
	private int row;
	private int column;
	private final char pieceType;
	private final Player player;

	public enum Player {
		RED, BLACK
	}

	protected Piece(int r, int c, char pt, Player p) {
		this.row = r;
		this.column = c;
		this.pieceType = pt;
		this.player = p;
	}

	public int getPosRow() {
		return this.row;
	}

	public int getPosColumn() {
		return this.column;
	}

	public void setPosition(int r, int c) {
		this.row = r;
		this.column = c;
	}

	public Player getPlayer() {
		return this.player;
	}

	public char getPieceType() {
		return this.pieceType;
	}

	public boolean inRedPalace(int row, int col) {
		return col >= THREE && col <= FIVE && row >= SEVEN && row <= NINE;
	}

	public boolean inBlackPalace(int row, int col) {
		return col >= THREE && col <= FIVE && row >= ZERO && row <= TWO;
	}

	public boolean inRedHalf(int row) {
		return row >= FIVE;
	}

	public boolean inBlackHalf(int row) {
		return row <= FOUR;
	}
	
	public abstract boolean validMove(Square[][] board, int targetRow,
			int targetCol);
}
