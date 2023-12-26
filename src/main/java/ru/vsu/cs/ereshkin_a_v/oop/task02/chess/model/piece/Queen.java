package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

public class Queen extends AbstractPiece {
	public Queen(Team team) {
		super(team, "Queen");
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♛' : '♕';
	}
}
