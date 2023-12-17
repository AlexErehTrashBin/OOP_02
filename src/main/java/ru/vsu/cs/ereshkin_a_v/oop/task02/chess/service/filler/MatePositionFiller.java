package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler;


import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.King;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Pawn;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Queen;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;

public class MatePositionFiller implements PieceFiller {
	private static MatePositionFiller instance;

	public static MatePositionFiller getInstance() {
		if (instance == null) {
			instance = new MatePositionFiller();
		}
		return instance;
	}

	private MatePositionFiller(){
	}

	@Override
	public void fill(Board board, Player firstPlayer, Player secondPlayer, PieceColor firstPlayerColor) {
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

		finder.setPiece(new Coordinate(0, 7), new Queen(white));
		finder.setPiece(new Coordinate(4, 5), new King(black));
		finder.setPiece(new Coordinate(4, 6), new Pawn(white));
		finder.setPiece(new Coordinate(4, 7), new King(white));
	}
}
