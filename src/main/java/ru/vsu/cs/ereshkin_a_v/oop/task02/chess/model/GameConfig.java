package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;

@Getter
@AllArgsConstructor
@Builder
public class GameConfig {
	private final Team firstTeam;
	private final Team secondTeam;
	private final StartPosition startPosition;

	public enum StartPosition {
		START_POSITION, ALMOST_MATE
	}
}
