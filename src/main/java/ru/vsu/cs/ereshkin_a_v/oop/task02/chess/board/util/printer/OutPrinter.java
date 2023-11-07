package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.printer;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.Board;

public class OutPrinter implements BoardPrinter {
	@Override
	public void print(Board board) {
		clearConsole();
		System.out.println();
		System.out.println("      [A][B][C][D][E][F][G][H] \n");
		for(int i = 0; i < 8; i++) {
			System.out.print("[" + (8 - i) + "]   ");

			for (int j = 0; j < 8; j++){
				System.out.print(board.getTile(new Coordinate(j, i)).getValue());
			}

			System.out.println("   [" + (8 - i) + "]");
		}

		System.out.println("\n      [A][B][C][D][E][F][G][H]\n");
	}

	/**
	 * Universal console clear for both Windows and Unix machines.
	 */
	private static void clearConsole(){
		try
		{
			final String os = System.getProperty("os.name");

			if (os.contains("Windows"))
			{
				//ASCII escape code
				System.out.print("\033[H\033[2J");
			}
			else
			{
				Runtime.getRuntime().exec("clear");
			}
		}
		catch (final Exception e){
			System.out.println("Error while trying to clear console");
		}
	}
}
