package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

/**
 * Слон
 * */
public class Bishop extends AbstractPiece {
	public Bishop(Team team) {
		super(team, "Bishop");
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♝' : '♗';
	}
}
