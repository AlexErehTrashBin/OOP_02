package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.BotPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.RealPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager.MoveManager;

public class PlayerServiceFactory {
	private static PlayerServiceFactory instance;

	public static PlayerServiceFactory getInstance() {
		if (instance == null) {
			instance = new PlayerServiceFactory();
		}
		return instance;
	}
	private PlayerServiceFactory() {
	}
	public PlayerService createPlayerService(Board board, MoveManager moveManager, Player player) {
		if (player.getClass() == BotPlayer.class) {
			return new BotPlayerService(moveManager, board, player);
		}
		if (player.getClass() == RealPlayer.class) {
			return new RealPlayerService(moveManager, board, player);
		}
		return NoPlayerService.getInstance();
	}
}
