package ru.vsu.cs.ereshkin_a_v.oop.task02.chess;

import java.util.List;

public abstract class ChessPiece {
	private final PieceType type;
	private final PieceColor color;
	private final List<Move> moves;
	private final String name;
	private final char charValue;
	// TODO(Мусор)
	private final boolean repeatableMoves;

	protected ChessPiece(PieceType type, PieceColor color, List<Move> moves, boolean repeatableMoves) {
		this.type = type;
		this.color = color;
		this.moves = moves;
		this.repeatableMoves = repeatableMoves;
		name = type.name();
		charValue = type.name().trim().charAt(0);
	}

	public static PieceColor opponent(PieceColor color) {
		return (color == PieceColor.BLACK) ? PieceColor.WHITE : PieceColor.BLACK;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public String getName() {
		return name;
	}

	public PieceColor getColor() {
		return color;
	}

	public char getCharValue() {
		return charValue;
	}

	public boolean hasRepeatableMoves() {
		return repeatableMoves;
	}

	public PieceType getPieceType() {
		return type;
	}

	public enum PieceType {
		Pawn, Rook, Knight, Bishop, Queen, King
	}
}
