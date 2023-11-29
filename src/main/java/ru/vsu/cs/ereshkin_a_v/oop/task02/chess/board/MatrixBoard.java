package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.filler.PieceFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.printer.BoardPrinter;

public class MatrixBoard extends AbstractBoard implements Board {
	private Tile[][] board;

	public MatrixBoard(PieceFiller filler, BoardPrinter printer, PieceColor startPlayer) {
		super(startPlayer, printer);
		initializeBoard();
		filler.fill(this);
	}

	private void initializeBoard() {
		board = new Tile[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new Tile(new Coordinate(j, i));
			}
		}
	}

	@Override
	public Tile getTile(Coordinate coordinate) {
		return board[coordinate.getY()][coordinate.getX()];
	}
}
