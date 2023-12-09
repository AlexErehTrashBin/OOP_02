package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;

import java.util.List;

public abstract class AbstractPiece implements Piece, Cloneable {
	private PieceColor color;
	private List<MoveVariant> moves;
	private String name;
	// TODO(Мусор)
	private boolean repeatableMoves;

	protected AbstractPiece(PieceColor color, String name, List<MoveVariant> moves, boolean repeatableMoves) {
		this.color = color;
		this.moves = moves;
		this.repeatableMoves = repeatableMoves;
		this.name = name;
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

	public boolean hasRepeatableMoves() {
		return repeatableMoves;
	}

	@Override
	public AbstractPiece clone() {
		try {
			AbstractPiece clone = (AbstractPiece) super.clone();
			clone.color = this.color;
			clone.name = this.name;
			clone.moves = this.moves.stream().map(MoveVariant::clone).toList();
			clone.repeatableMoves = this.repeatableMoves;
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
