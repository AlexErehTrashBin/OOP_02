package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.DefaultMove;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.King;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.checkmatechecker.CheckMateTesterImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider.MoveProviderFactory;

import java.util.List;

public class MoveManagerImpl implements MoveManager {
	private static MoveManagerImpl instance;

	public static MoveManagerImpl getInstance() {
		if (instance == null) {
			instance = new MoveManagerImpl();
		}
		return instance;
	}

	private MoveManagerImpl() {
	}

	@Override
	public void playMove(Board board, Coordinate from, Coordinate to) {
		if (!isValidMove(board, from, to, false)) return;

		Tile fromTile = TileFinderImpl.getInstance().getTile(board, from);
		Tile toTile = TileFinderImpl.getInstance().getTile(board, to);
		Piece toPiece = toTile.getPiece();

		Piece pieceToMove = fromTile.getPiece();
		Move move = new DefaultMove(fromTile.getCoordinate(), toTile.getCoordinate(), pieceToMove, toPiece);
		board.getMoves().offerLast(move);
		toTile.setPiece(pieceToMove);
		fromTile.empty();

		boolean isOpponentUnderCheck = CheckMateTesterImpl.getInstance().isCheck(board, board.getCurrentTeam());
		if (isOpponentUnderCheck && !board.isUnderCheck(board.getOpponentTeam())) {
			board.setUnderCheck(true, board.getOpponentTeam());
		} else if (!isOpponentUnderCheck && board.isUnderCheck(board.getOpponentTeam())) {
			board.setUnderCheck(false, board.getOpponentTeam());
		}

		boolean isCurrentUnderCheck = CheckMateTesterImpl.getInstance().isCheck(board, board.getOpponentTeam());
		if (isCurrentUnderCheck && !board.isUnderCheck(board.getCurrentTeam())) {
			board.setUnderCheck(true, board.getCurrentTeam());
		} else if (!isCurrentUnderCheck && board.isUnderCheck(board.getCurrentTeam())) {
			board.setUnderCheck(false, board.getCurrentTeam());
		}

		board.setFinished(CheckMateTesterImpl.getInstance().isMate(board, board.getCurrentTeam(), board.getOpponentTeam()));
		board.setFinished(CheckMateTesterImpl.getInstance().isMate(board, board.getOpponentTeam(), board.getCurrentTeam()));
	}

	@Override
	public boolean isValidMove(Board board, Coordinate from, Coordinate to, boolean hypothetical) {
		Tile fromTile = TileFinderImpl.getInstance().getTile(board, from);
		Tile toTile = TileFinderImpl.getInstance().getTile(board, to);
		Piece fromPiece = fromTile.getPiece();
		Piece toPiece = toTile.getPiece();

		if (fromPiece == null) return false;

		if (fromPiece.getTeam().getColor() != board.getCurrentTeam().getColor()) return false;

		if (toPiece != null && toPiece.getTeam().getColor() == board.getCurrentTeam().getColor()) return false;

		if (toPiece instanceof King && toPiece.getTeam() != board.getCurrentTeam()) return false;

		return isValidMoveForPiece(board, from, to);
	}

	private boolean isValidMoveForPiece(Board board, Coordinate from, Coordinate to) {
		Tile fromTile = TileFinderImpl.getInstance().getTile(board, from);
		Piece fromPiece = fromTile.getPiece();
		List<MoveVariant> validMoves = MoveProviderFactory.getInstance().create(board, fromTile).getAvailableMoves();
		Tile toTile = TileFinderImpl.getInstance().getTile(board, to);

		int xMove = to.getX() - from.getX();
		int yMove = to.getY() - from.getY();

		for (MoveVariant move : validMoves) {
			if (!(move.getX() == xMove && move.getY() == yMove)) continue;
			if (toTile.isEmpty()) return true;
			Piece toPiece = toTile.getPiece();
			return fromPiece.getColor() != toPiece.getColor();
		}
		return false;
	}

}
