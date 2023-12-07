package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.player;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.ChessGame;

public class BotPlayer implements Player {
	@Override
	public void makeMove(ChessGame game) {
		try {

			Thread.sleep(2_000);
		} catch (InterruptedException ignored) {
		}
	}
}
