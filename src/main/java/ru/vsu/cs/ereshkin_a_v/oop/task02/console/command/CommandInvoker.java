package ru.vsu.cs.ereshkin_a_v.oop.task02.console.command;

public class CommandInvoker {
	private final ConsoleCommand makeMoveCommand;
	private final ConsoleCommand printBoardCommand;
	private final ConsoleCommand revertMoveCommand;

	public CommandInvoker(ConsoleCommand makeMoveCommand, ConsoleCommand printBoardCommand, ConsoleCommand revertMoveCommand) {
		this.makeMoveCommand = makeMoveCommand;
		this.printBoardCommand = printBoardCommand;
		this.revertMoveCommand = revertMoveCommand;
	}

	public void printBoard() {
		printBoardCommand.execute();
	}

	public void makeMove() {
		makeMoveCommand.execute();
	}

	public void revertMove() {
		revertMoveCommand.execute();
	}
}
