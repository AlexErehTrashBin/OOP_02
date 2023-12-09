package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.*;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;

public class UpperHalfBlackFiller implements PieceFiller {
	private final TileFinder finder;

	public UpperHalfBlackFiller(Board board) {
		finder = new TileFinderImpl(board);
	}

	@Override
	public void fill() {
		// Добавляем пешек
		for (int i = 0; i < 8; i++) {
			finder.setPiece(new Coordinate(i, 1), new Pawn(PieceColor.BLACK));
			finder.setPiece(new Coordinate(i, 6), new Pawn(PieceColor.WHITE));
		}

		// Добавляем ладей
		finder.setPiece(new Coordinate(0, 0), new Rook(PieceColor.BLACK));
		finder.setPiece(new Coordinate(0, 7), new Rook(PieceColor.WHITE));
		finder.setPiece(new Coordinate(7, 0), new Rook(PieceColor.BLACK));
		finder.setPiece(new Coordinate(7, 7), new Rook(PieceColor.WHITE));

		// Добавляем коней
		finder.setPiece(new Coordinate(1, 0), new Knight(PieceColor.BLACK));
		finder.setPiece(new Coordinate(6, 0), new Knight(PieceColor.BLACK));
		finder.setPiece(new Coordinate(1, 7), new Knight(PieceColor.WHITE));
		finder.setPiece(new Coordinate(6, 7), new Knight(PieceColor.WHITE));

		// Добавляем слонов
		finder.setPiece(new Coordinate(2, 0), new Bishop(PieceColor.BLACK));
		finder.setPiece(new Coordinate(5, 0), new Bishop(PieceColor.BLACK));
		finder.setPiece(new Coordinate(2, 7), new Bishop(PieceColor.WHITE));
		finder.setPiece(new Coordinate(5, 7), new Bishop(PieceColor.WHITE));

		// Добавляем ферзей
		finder.setPiece(new Coordinate(3, 0), new Queen(PieceColor.BLACK));
		finder.setPiece(new Coordinate(3, 7), new Queen(PieceColor.WHITE));

		//kings
		finder.setPiece(new Coordinate(4, 0), new King(PieceColor.BLACK));
		finder.setPiece(new Coordinate(4, 7), new King(PieceColor.WHITE));
	}
}
