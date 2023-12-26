package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;

public interface MoveManager {
	void playMove(Board board, Coordinate from, Coordinate to);

	boolean isValidMove(Board board, Coordinate from, Coordinate to, boolean hypothetical);
}
