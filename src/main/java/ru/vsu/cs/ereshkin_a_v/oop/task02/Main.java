package ru.vsu.cs.ereshkin_a_v.oop.task02;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller.ChessGame;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.GameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.BotPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.RealPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.serial.SerialGameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;
import ru.vsu.cs.ereshkin_a_v.oop.task02.console.command.*;
import ru.vsu.cs.ereshkin_a_v.oop.task02.util.MoveSerializer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.util.PieceSerializer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.util.PlayerSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
		ChessGame game;
		var gson = new GsonBuilder()
				.registerTypeAdapter(Piece.class, new PieceSerializer())
				.registerTypeAdapter(Player.class, new PlayerSerializer())
				.registerTypeAdapter(Move.class, new MoveSerializer())
				.create();
		try {
			SerialGameConfig config = gson.fromJson(Files.readString(Path.of("board.json")), SerialGameConfig.class);
			game = new ChessGame(config);
		} catch (IOException e) {
			System.err.println("Произошла ошибка чтения из файла \"board.json\"");
			GameConfig config = obtainConfig();
			game = new ChessGame(config);
		}

		CommandInvoker invoker = new CommandInvoker(
				new MakeMoveCommand(game),
				new PrintBoardCommand(game),
				new RevertMoveCommand(game),
				new SaveCommand(game),
				new LoadCommand(game)
		);

		System.out.println("Справка по управлению");
		System.out.println("1 - напечатать поле");
		System.out.println("2 - сделать ход");
		System.out.println("3 - откатить ход (если ходов нет - ничего не делать)");
		System.out.println("4 - сохранить в файл");
		System.out.println("5 - загрузить из файла");


		while (!game.isFinished()) {
			System.out.print("Введите команду: ");
			int command = scanner.nextInt();
			switch (command) {
				case 1 -> invoker.printBoard();
				case 2 -> invoker.makeMove();
				case 3 -> invoker.revertMove();
				case 4 -> invoker.save();
				case 5 -> invoker.load();
			}
		}
		scanner.close();
		System.out.println("Игра окончена!");
	}
}