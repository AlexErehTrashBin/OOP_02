package ru.vsu.cs.ereshkin_a_v.oop.task02.console.command;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller.ChessGame;

public class RevertMoveCommand implements ConsoleCommand {
	private final ChessGame game;

	public RevertMoveCommand(ChessGame game) {
		this.game = game;
	}

	@Override
	public void execute() {
		game.revertMove();
	}
}
