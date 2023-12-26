package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

public class King extends AbstractPiece {
	public King(Team team) {
		super(team, "King");
	}



	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♚' : '♔';
	}
}
