package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.*;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;

public class MoveProviderFactory {
	private static MoveProviderFactory instance;

	public static MoveProviderFactory getInstance() {
		if (instance == null) {
			instance = new MoveProviderFactory();
		}
		return instance;
	}

	private MoveProviderFactory(){
	}

	public MoveProvider create(Board board, Tile tileWithPiece) {
		if (tileWithPiece.isEmpty()) return null;
		if (tileWithPiece.getPiece().getClass() == Bishop.class) {
			return new BishopMoveProvider(board, tileWithPiece);
		}
		if (tileWithPiece.getPiece().getClass() == King.class) {
			return new KingMoveProvider(board, tileWithPiece);
		}
		if (tileWithPiece.getPiece().getClass() == Knight.class) {
			return new KnightMoveProvider(board, tileWithPiece);
		}
		if (tileWithPiece.getPiece().getClass() == Pawn.class) {
			return new PawnMoveProvider(board, tileWithPiece);
		}
		if (tileWithPiece.getPiece().getClass() == Queen.class) {
			return new QueenMoveProvider(board, tileWithPiece);
		}
		if (tileWithPiece.getPiece().getClass() == Rook.class) {
			return new RookMoveProvider(board, tileWithPiece);
		}

		return new NoMoveProvider();
	}
}
