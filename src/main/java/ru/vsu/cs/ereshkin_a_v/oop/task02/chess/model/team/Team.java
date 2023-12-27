package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Team {
	private final String name;
	private final PieceColor color;
	private final List<Player> players;
	public Team(String name, PieceColor color, Player... players) {
		this.players = new ArrayList<>(List.of(players));
		this.color = color;
		this.name = name;
	}

	public String getStringRepresentation() {
		StringBuilder sb = new StringBuilder();
		sb.append("команда ").append(name).append(": ");
		for (Player player : players) {
			sb.append(player.getName()).append(", ");
		}
		return sb.toString();
	}
}
