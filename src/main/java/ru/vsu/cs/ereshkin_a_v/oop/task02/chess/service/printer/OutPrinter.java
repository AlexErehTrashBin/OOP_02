package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.printer;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;

import java.util.ArrayList;
import java.util.List;

public class OutPrinter implements BoardPrinter {
	private final TileFinder tileFinder;
	public OutPrinter(Board board) {
		this.tileFinder = new TileFinderImpl(board);
	}
	/**
	 * Universal console clear for both Windows and Unix machines.
	 */
	private static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				//ASCII escape code
				System.out.print("\033[H\033[2J");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			System.out.println("Error while trying to clear console");
		}
	}

	@Override
	public void print(Board board) {
		clearConsole();
		List<String> infoList = new ArrayList<>();
		for (int i = 0; i < board.getSize(); i++) {
			infoList.add("");
		}
		infoList.set(0, "Текущий игрок: " + board.getCurrentPlayer().toString());
		infoList.set(1, "Текущий игрок под шахом: " + board.isUnderCheck(board.getCurrentPlayer()));
		infoList.set(2, "Оппонент: " + board.getOpponentPlayer().toString());
		infoList.set(3, "Оппонент под шахом: " + board.isUnderCheck(board.getOpponentPlayer()));
		System.out.println("\n      [ A ][ B ][ C ][ D ][ E ][ F ][ G ][ H ]\n");
		for (int i = 0; i < 8; i++) {
			System.out.print("[" + (8 - i) + "]   ");

			for (int j = 0; j < 8; j++) {
				Tile tile = tileFinder.getTile(new Coordinate(j, i));
				System.out.print(tile.getValue());
			}

			System.out.print("   [" + (8 - i) + "]");
			System.out.print("        " + infoList.get(i));
			System.out.println();
		}

		System.out.println("\n      [ A ][ B ][ C ][ D ][ E ][ F ][ G ][ H ]\n");
	}
}
