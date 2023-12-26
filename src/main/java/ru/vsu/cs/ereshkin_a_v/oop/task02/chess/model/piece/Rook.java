package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;


/**
 * Ладья
 * */
public class Rook extends AbstractPiece {
	public Rook(Team team) {
		super(team, "Rook");
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♜' : '♖';
	}
}
