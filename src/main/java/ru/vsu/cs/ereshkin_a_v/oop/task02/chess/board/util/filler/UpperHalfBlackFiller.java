package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.filler;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces.*;

public class UpperHalfBlackFiller implements PieceFiller {
	@Override
	public void fill(Board board) {
		// Добавляем пешек
		for (int i = 0; i < 8; i++) {
			board.setPiece(new Coordinate(i, 1), new Pawn(PieceColor.BLACK));
			board.setPiece(new Coordinate(i, 6), new Pawn(PieceColor.WHITE));
		}

		// Добавляем ладей
		board.setPiece(new Coordinate(0, 0), new Rook(PieceColor.BLACK));
		board.setPiece(new Coordinate(0, 7), new Rook(PieceColor.BLACK));
		board.setPiece(new Coordinate(7, 0), new Rook(PieceColor.WHITE));
		board.setPiece(new Coordinate(7, 7), new Rook(PieceColor.WHITE));

		// Добавляем коней
		board.setPiece(new Coordinate(1, 0), new Knight(PieceColor.BLACK));
		board.setPiece(new Coordinate(6, 0), new Knight(PieceColor.BLACK));
		board.setPiece(new Coordinate(1, 7), new Knight(PieceColor.WHITE));
		board.setPiece(new Coordinate(6, 7), new Knight(PieceColor.WHITE));

		// Добавляем слонов
		board.setPiece(new Coordinate(2, 0), new Bishop(PieceColor.BLACK));
		board.setPiece(new Coordinate(5, 0), new Bishop(PieceColor.BLACK));
		board.setPiece(new Coordinate(2, 7), new Bishop(PieceColor.WHITE));
		board.setPiece(new Coordinate(5, 7), new Bishop(PieceColor.WHITE));

		// Добавляем ферзей
		board.setPiece(new Coordinate(3, 0), new Queen(PieceColor.BLACK));
		board.setPiece(new Coordinate(3, 7), new Queen(PieceColor.WHITE));

		//kings
		board.setPiece(new Coordinate(4, 0), new King(PieceColor.BLACK));
		board.setPiece(new Coordinate(4, 7), new King(PieceColor.WHITE));
	}
}
