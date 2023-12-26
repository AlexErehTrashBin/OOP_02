package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

public interface Piece {

	char getCharValue();

	PieceColor getColor();

	String getName();

	Team getTeam();

	void setTeam(Team team);
}
