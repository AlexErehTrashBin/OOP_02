package ru.vsu.cs.ereshkin_a_v.oop.task02;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller.ChessGame;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.BotPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.RealPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;
import ru.vsu.cs.ereshkin_a_v.oop.task02.console.command.*;

import java.util.*;

public class Main {
	private static GameConfig obtainTestConfig() {
		Player firstPlayer = new BotPlayer("1", PieceColor.WHITE);
		Player thirdPlayer = new BotPlayer("3", PieceColor.WHITE);
		Player secondPlayer = new BotPlayer("2", PieceColor.BLACK);
		Player fourthPlayer = new BotPlayer("4", PieceColor.BLACK);
		return GameConfig.builder()
				.firstTeam(new Team("1", PieceColor.WHITE, firstPlayer, thirdPlayer))
				.secondTeam(new Team("2", PieceColor.BLACK, secondPlayer, fourthPlayer))
				.startPosition(GameConfig.StartPosition.ALMOST_MATE)
				.build();
	}
	private static Team obtainTeam(PieceColor color) {
		System.out.println("Сейчас будет создана команда.");
		System.out.print("Название команды: ");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		List<Player> players = new ArrayList<>();
		while (true) {
			System.out.print("Добавить игрока? (1 - да, 0 - нет): ");
			int result = scanner.nextInt();
			if (result == 0) {
				break;
			}
			Player player = getPlayer(color);
			players.add(player);
		}
		return new Team(name, color, players);
	}
	private static GameConfig obtainConfig() {
		Team firstTeam = obtainTeam(PieceColor.BLACK);
		Team secondTeam = obtainTeam(PieceColor.WHITE);
		GameConfig.StartPosition startPosition = obtainPosition();
		return new GameConfig(firstTeam, secondTeam, startPosition);
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

	private static Player getPlayer(PieceColor color) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Игрок будет (1 - болванчик, 2 - человек): ");
		int playerType = scanner.nextInt();
		System.out.print("Имя игрока: ");
		scanner.nextLine();
		String name = scanner.nextLine();
		if (playerType == 1) {
			return new BotPlayer(name, color);
		}
		return new RealPlayer(name, color);
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