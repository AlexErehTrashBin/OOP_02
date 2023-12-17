package ru.vsu.cs.ereshkin_a_v.oop.task02.console.command;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller.ChessGame;

public class MakeMoveCommand implements ConsoleCommand{
	private final ChessGame game;

	public MakeMoveCommand(ChessGame game) {
		this.game = game;
	}

	@Override
	public void execute() {
		game.makeMove();
	}
}
