package ru.vsu.cs.ereshkin_a_v.oop.task02;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.ChessGame;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.console.InputHandler;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		InputHandler handler = new InputHandler();
		Scanner scanner = new Scanner(System.in);

		PieceColor startPlayer = PieceColor.BLACK;
		ChessGame game = new ChessGame(startPlayer);
		game.printCurrentState();
		while (!game.isFinished()) {
			System.out.print("Введите ход (например. A2-A3): ");
			String input = scanner.nextLine();

			if (!handler.isValid(input)) {
				System.out.println("Неверный ввод!");
				System.out.println("Верный ввод в формате: A2-A3");
			} else {
				Coordinate from = handler.getFrom(input);
				Coordinate to = handler.getTo(input);

				boolean movePlayed = game.playMove(from, to);
				if (!movePlayed)
					System.out.println("Недопустимый ход!");
				else {
					game.printCurrentState();
				}
			}
		}
		scanner.close();
		System.out.println("Игра окончена!");
	}
}