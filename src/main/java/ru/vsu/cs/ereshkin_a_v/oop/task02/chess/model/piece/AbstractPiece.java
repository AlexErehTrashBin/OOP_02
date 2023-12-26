package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

@Getter
@AllArgsConstructor
public abstract class AbstractPiece implements Piece, Cloneable {
	@Setter
	private Team team;
	private String name;

	public PieceColor getColor() {
		return team.getColor();
	}

	@Override
	public AbstractPiece clone() {
		try {
			AbstractPiece clone = (AbstractPiece) super.clone();
			clone.team = this.team;
			clone.name = this.name;
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
