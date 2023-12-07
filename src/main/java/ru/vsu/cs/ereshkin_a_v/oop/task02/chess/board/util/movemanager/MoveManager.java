package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.movemanager;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;

public interface MoveManager {
	boolean playMove(Coordinate from, Coordinate to, Runnable endTurn);

	boolean isValidMove(Coordinate from, Coordinate to, boolean hypothetical);
}
