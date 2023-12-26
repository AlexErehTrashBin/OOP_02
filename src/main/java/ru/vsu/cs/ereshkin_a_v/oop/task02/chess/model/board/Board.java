package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;

import java.util.Deque;

public interface Board {
	Deque<Move> getMoves();
	boolean isFinished();
	Team getCurrentTeam();
	Team getOpponentTeam();

	void setFinished(boolean finished);
	boolean isUnderCheck(Team player);
	void setUnderCheck(boolean underCheck, Team player);

	void setCurrentTeam(Team team);

	Tile getUpperLeftTile();

	Tile getLowerLeftTile();

	Tile getUpperRightTile();

	Tile getLowerRightTile();

	int getSize();
}