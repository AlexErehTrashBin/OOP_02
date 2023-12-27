package ru.vsu.cs.ereshkin_a_v.oop.task02.console.command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandInvoker {
	private final ConsoleCommand makeMoveCommand;
	private final ConsoleCommand printBoardCommand;
	private final ConsoleCommand revertMoveCommand;
	private final ConsoleCommand saveCommand;
	private final ConsoleCommand loadCommand;

	public void printBoard() {
		printBoardCommand.execute();
	}

	public void makeMove() {
		makeMoveCommand.execute();
	}

	public void revertMove() {
		revertMoveCommand.execute();
	}

	public void save() {
		saveCommand.execute();
	}

	public void load() {
		loadCommand.execute();
	}
}
