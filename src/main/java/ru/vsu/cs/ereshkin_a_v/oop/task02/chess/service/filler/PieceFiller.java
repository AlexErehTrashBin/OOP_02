package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

public interface PieceFiller {
	/**
	 * Метод, который заполняет поле значениями
	 */
	void fill(Board board, Team firstPlayer,
	          Team secondPlayer);
}
