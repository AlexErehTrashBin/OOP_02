package ru.vsu.cs.ereshkin_a_v.oop.task02;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller.ChessGame;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.BotPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.RealPlayer;

import java.util.Objects;
import java.util.Scanner;

public class Main {
	private static GameConfig obtainConfig() {
		Player firstPlayer = getPlayer("Первый", PieceColor.WHITE);
		Player secondPlayer = getPlayer("Второй", PieceColor.BLACK);
		return new GameConfig(firstPlayer, secondPlayer, getStartPlayerColor());
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

		ChessGame game = new ChessGame(config.getFirstPlayer(),
				config.getSecondPlayer(), config.getFirstPlayerColor());
		game.printCurrentState();
		game.start();
		scanner.close();
		System.out.println("Игра окончена!");
	}
}