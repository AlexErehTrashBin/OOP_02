package ru.vsu.cs.ereshkin_a_v.oop.task02.console.command;

import lombok.RequiredArgsConstructor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller.ChessGame;

@RequiredArgsConstructor
public class PrintBoardCommand implements ConsoleCommand{
	private final ChessGame game;

	@Override
	public void execute() {
		game.printCurrentState();
	}
}
