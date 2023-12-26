package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

public class Pawn extends AbstractPiece {
	public Pawn(Team team) {
		super(team, "Pawn");
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♟' : '♙';
	}
}
