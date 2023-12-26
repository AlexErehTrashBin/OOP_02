package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.checkmatechecker;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

public interface CheckMateTester {

	boolean isCheck(Board board, Team player);

	boolean isMate(Board board, Team player, Team underCheckPlayer);
}
