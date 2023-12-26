package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.BotPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.RealPlayer;

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
	public PlayerService createPlayerService(Player player) {
		if (player.getClass() == BotPlayer.class) {
			return new BotPlayerService(player);
		}
		if (player.getClass() == RealPlayer.class) {
			return new RealPlayerService(player);
		}
		return NoPlayerService.getInstance();
	}
}
