package ru.vsu.cs.ereshkin_a_v.oop.task02.console.command;

import lombok.RequiredArgsConstructor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller.GameController;

@RequiredArgsConstructor
public class MakeMoveCommand implements ConsoleCommand{
	private final GameController game;

	@Override
	public void execute() {
		game.makeMove();
		game.printCurrentState();
	}
}
