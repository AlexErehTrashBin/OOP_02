package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

import java.util.Deque;
import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class SerialGameConfig {
	private final Team firstTeam;
	private final Team secondTeam;
	private final int currentTeam;
	private final int size;
	private final boolean isFinished;
	private final Map<Team, Boolean> checkMap;
	private final Map<Coordinate, Piece> boardState; // <Coordinate, Piece>
	private final Deque<Move> moves;
}
