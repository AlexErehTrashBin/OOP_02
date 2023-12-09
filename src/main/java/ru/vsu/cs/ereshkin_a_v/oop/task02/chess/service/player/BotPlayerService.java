package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller.ChessGame;

public class BotPlayerService implements PlayerService {
	@Override
	public void makeMove(ChessGame game) {
		try {

			Thread.sleep(2_000);
		} catch (InterruptedException ignored) {
		}
	}
}
