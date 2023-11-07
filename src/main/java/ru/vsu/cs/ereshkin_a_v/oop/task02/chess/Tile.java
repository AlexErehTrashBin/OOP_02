package ru.vsu.cs.ereshkin_a_v.oop.task02.chess;

public class Tile {
	private ChessPiece piece;
	private final TileColor color;

	public enum TileColor{
		White, Black
	}

	public Tile(TileColor color){
		this.color = color;
	}

	public Tile(TileColor color, ChessPiece piece){
		this.color = color;
		this.piece = piece;
	}

	public void setPiece(ChessPiece piece){
		this.piece = piece;
	}

	public ChessPiece getPiece(){
		return this.piece;
	}

	public String getValue(){
		if(piece != null){
			return "[" + piece.getCharValue() + "]";
		} else {
			return "[ ]";
		}
	}

	public boolean isEmpty(){
		return piece == null;
	}

	public void empty(){
		piece = null;
	}
}
