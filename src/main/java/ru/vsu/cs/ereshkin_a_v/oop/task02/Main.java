package ru.vsu.cs.ereshkin_a_v.oop.task02;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller.ChessGame;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.BotPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.RealPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.console.command.*;

import java.util.Objects;
import java.util.Scanner;

public class Main {
	private static GameConfig obtainConfig() {
		Player firstPlayer = getPlayer("Первый", PieceColor.WHITE);
		Player secondPlayer = getPlayer("Второй", PieceColor.BLACK);
		GameConfig.StartPosition startPosition = obtainPosition();
		return new GameConfig(firstPlayer, secondPlayer, getStartPlayerColor(), startPosition);
	}

	private static GameConfig.StartPosition obtainPosition() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("С какой позиции начать игру (1 - начальная, 2 - почти мат): ");
		int result = scanner.nextInt();
		switch (result) {
			case 1 -> {
				return GameConfig.StartPosition.START_POSITION;
			}
			case 2 -> {
				return GameConfig.StartPosition.ALMOST_MATE;
			}
		}
		return null;
	}

	private static Player getPlayer(String playerNumber, PieceColor color) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(playerNumber + " игрок будет (1 - болванчик, 2 - человек): ");
		int playerType = scanner.nextInt();
		System.out.print("Имя игрока: ");
		scanner.nextLine();
		String name = scanner.nextLine();
		if (playerType == 1) {
			return new BotPlayer(name, color);
		}
		return new RealPlayer(name, color);
	}
	private static PieceColor getStartPlayerColor() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Какой игрок должен начать? (Б/Ч): ");
		String colorString = scanner.nextLine();
		if (Objects.equals(colorString, "Ч")) {
			return PieceColor.BLACK;
		}
		return PieceColor.WHITE;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		GameConfig config = obtainConfig();

		ChessGame game = new ChessGame(config);

		ConsoleCommand makeMoveCommand = new MakeMoveCommand(game);
		ConsoleCommand printBoardCommand = new PrintBoardCommand(game);
		ConsoleCommand revertMoveCommand = new RevertMoveCommand(game);
		CommandInvoker invoker = new CommandInvoker(makeMoveCommand, printBoardCommand, revertMoveCommand);

		System.out.println("Справка по управлению");
		System.out.println("1 - напечатать поле");
		System.out.println("2 - сделать ход");
		System.out.println("3 - откатить ход (если ходов нет - ничего не делать)");


		while (!game.isFinished()) {
			System.out.print("Введите команду: ");
			int command = scanner.nextInt();
			switch (command) {
				case 1 -> invoker.printBoard();
				case 2 -> invoker.makeMove();
				case 3 -> invoker.revertMove();
			}
		}
		scanner.close();
		System.out.println("Игра окончена!");
	}
}