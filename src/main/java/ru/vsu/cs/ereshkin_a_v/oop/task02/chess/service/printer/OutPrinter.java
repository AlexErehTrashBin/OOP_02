package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.printer;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;

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
		System.out.println();
		System.out.println("      [  A  ][ B ][  C  ][  D  ][  E  ][  F  ][  G  ][  H  ] \n");
		for (int i = 0; i < 8; i++) {
			System.out.print("[" + (8 - i) + "]   ");

			for (int j = 0; j < 8; j++) {
				Tile tile = tileFinder.getTile(new Coordinate(j, i));
				System.out.print(tile.getValue());
			}

			System.out.println("   [" + (8 - i) + "]");
		}

		System.out.println("\n      [  A  ][  B  ][  C  ][  D  ][  E  ][  F  ][  G  ][  H  ]\n");
	}
}
