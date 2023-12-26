package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.allteams;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player.PlayerServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class AllTeamsServiceImpl implements AllTeamsService {
	private final Map<Team, Integer> currentPlayerIndices;
	private final Board board;

	public AllTeamsServiceImpl(Board board) {
		this.board = board;
		this.currentPlayerIndices = new HashMap<>();
		currentPlayerIndices.put(board.getCurrentTeam(), 0);
		currentPlayerIndices.put(board.getOpponentTeam(), 0);
	}

	private Player getAndIncrement(Team team) {
		Integer playerIndex = currentPlayerIndices.get(team);
		int newPlayerIndex = (playerIndex + 1) % team.getPlayers().size();
		Player player = team.getPlayers().get(playerIndex);
		currentPlayerIndices.put(team, newPlayerIndex);
		return player;
	}

	@Override
	public void makeMove() {
		Team currentTeam = board.getCurrentTeam();
		Player currentPlayer = getAndIncrement(currentTeam);
		PlayerServiceFactory.getInstance()
				.createPlayerService(currentPlayer).makeMove(board);
		board.setCurrentTeam(board.getOpponentTeam());
	}
}
