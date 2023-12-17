package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.*;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;

public class StartBoardFiller implements PieceFiller {
	private static StartBoardFiller instance;

	public static StartBoardFiller getInstance() {
		if (instance == null) {
			instance = new StartBoardFiller();
		}
		return instance;
	}
	private StartBoardFiller(){
	}

	@Override
	public void fill(Board board, Player firstPlayer,
	                 Player secondPlayer, PieceColor firstPlayerColor) {
		TileFinder finder = new TileFinderImpl(board);
		Player white;
		Player black;
		if (firstPlayerColor == PieceColor.WHITE) {
			white = firstPlayer;
			black = secondPlayer;
		} else {
			white = secondPlayer;
			black = firstPlayer;
		}
		// Добавляем пешек
		for (int i = 0; i < 8; i++) {
			finder.setPiece(new Coordinate(i, 1), new Pawn(black));
			finder.setPiece(new Coordinate(i, 6), new Pawn(white));
		}

		// Добавляем ладей
		finder.setPiece(new Coordinate(0, 0), new Rook(black));
		finder.setPiece(new Coordinate(0, 7), new Rook(white));
		finder.setPiece(new Coordinate(7, 0), new Rook(black));
		finder.setPiece(new Coordinate(7, 7), new Rook(white));

		// Добавляем коней
		finder.setPiece(new Coordinate(1, 0), new Knight(black));
		finder.setPiece(new Coordinate(6, 0), new Knight(black));
		finder.setPiece(new Coordinate(1, 7), new Knight(white));
		finder.setPiece(new Coordinate(6, 7), new Knight(white));

		// Добавляем слонов
		finder.setPiece(new Coordinate(2, 0), new Bishop(black));
		finder.setPiece(new Coordinate(5, 0), new Bishop(black));
		finder.setPiece(new Coordinate(2, 7), new Bishop(white));
		finder.setPiece(new Coordinate(5, 7), new Bishop(white));

		// Добавляем ферзей
		finder.setPiece(new Coordinate(3, 0), new Queen(black));
		finder.setPiece(new Coordinate(3, 7), new Queen(white));

		//kings
		finder.setPiece(new Coordinate(4, 0), new King(black));
		finder.setPiece(new Coordinate(4, 7), new King(white));
	}
}
