package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

public class Knight extends AbstractPiece {
	public Knight(Team team) {
		super(team, "Knight");
	}


	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♞' : '♘';
	}
}
