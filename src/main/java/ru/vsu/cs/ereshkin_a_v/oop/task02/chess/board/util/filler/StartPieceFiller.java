package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.filler;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces.*;

public class StartPieceFiller implements PieceFiller {
	@Override
	public void fill(Board board) {
		// Добавляем пешек
		for(int i = 0; i < 8; i++){
			board.setPiece(new Coordinate(i, 1), new Pawn(PieceColor.Black));
			board.setPiece(new Coordinate(i, 6), new Pawn(PieceColor.White));
		}

		// Добавляем ладей
		board.setPiece(new Coordinate(0, 0), new Rook(PieceColor.Black));
		board.setPiece(new Coordinate(0, 7), new Rook(PieceColor.Black));
		board.setPiece(new Coordinate(7, 0), new Rook(PieceColor.White));
		board.setPiece(new Coordinate(7, 7), new Rook(PieceColor.White));

		// Добавляем коней
		board.setPiece(new Coordinate(1, 0), new Knight(PieceColor.Black));
		board.setPiece(new Coordinate(6, 0), new Knight(PieceColor.Black));
		board.setPiece(new Coordinate(1, 7), new Knight(PieceColor.White));
		board.setPiece(new Coordinate(6, 7), new Knight(PieceColor.White));

		// Добавляем слонов
		board.setPiece(new Coordinate(2, 0), new Bishop(PieceColor.Black));
		board.setPiece(new Coordinate(5, 0), new Bishop(PieceColor.Black));
		board.setPiece(new Coordinate(2, 7), new Bishop(PieceColor.White));
		board.setPiece(new Coordinate(5, 7), new Bishop(PieceColor.White));

		// Добавляем ферзей
		board.setPiece(new Coordinate(3, 0), new Queen(PieceColor.Black));
		board.setPiece(new Coordinate(3, 7), new Queen(PieceColor.White));

		//kings
		board.setPiece(new Coordinate(4, 0), new King(PieceColor.Black));
		board.setPiece(new Coordinate(4, 7), new King(PieceColor.White));
	}
}
