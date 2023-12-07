package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;

import java.util.List;

public abstract class AbstractPiece implements Piece, Cloneable {
	private PieceType type;
	private PieceColor color;
	private List<MoveVariant> moves;
	private String name;
	private final char charValue;
	// TODO(Мусор)
	private final boolean repeatableMoves;

	protected AbstractPiece(PieceType type, PieceColor color, List<MoveVariant> moves, boolean repeatableMoves) {
		this.type = type;
		this.color = color;
		this.moves = moves;
		this.repeatableMoves = repeatableMoves;
		name = type.name();
		charValue = type.name().trim().charAt(0);
	}

	public List<MoveVariant> getMoves() {
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

	@Override
	public AbstractPiece clone() {
		try {
			AbstractPiece clone = (AbstractPiece) super.clone();
			clone.type = this.type;
			clone.color = this.color;
			clone.name = this.name;
			clone.moves = this.moves.stream().map(MoveVariant::clone).toList();
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
