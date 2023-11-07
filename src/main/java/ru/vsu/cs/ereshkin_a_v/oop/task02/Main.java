package ru.vsu.cs.ereshkin_a_v.oop.task02;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.ChessGame;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.filler.PieceFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.filler.StartPieceFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.printer.BoardPrinter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.printer.OutPrinter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.console.InputHandler;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		InputHandler handler = new InputHandler();
		Scanner scanner = new Scanner(System.in);

		PieceFiller filler = new StartPieceFiller();
		BoardPrinter printer = new OutPrinter();
		PieceColor startPlayer = PieceColor.Black;
		ChessGame game = new ChessGame(filler, printer, startPlayer);
		game.printCurrentState();
		while (!game.isFinished()) {
			System.out.println("Enter move (eg. A2-A3): ");
			String input = scanner.nextLine();

			if (!handler.isValid(input)) {
				System.out.println("Invalid input!");
				System.out.println("Valid input is in form: A2-A3");
			} else {
				Coordinate from = handler.getFrom(input);
				Coordinate to = handler.getTo(input);

				boolean movePlayed = game.playMove(from, to);
				if (!movePlayed)
					System.out.println("Illegal move!");
				else {
					game.printCurrentState();
				}
			}
		}
		scanner.close();
		System.out.println("Game has finished. Thanks for playing.");
	}
}