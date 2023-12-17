package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.DefaultMove;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.King;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.checkmatechecker.CheckMateTester;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider.MoveProviderFactory;

import java.util.List;

public class MoveManagerImpl implements MoveManager {
	private final Board board;
	private final TileFinder tileFinder;
	private final CheckMateTester checkMateTester;
	public MoveManagerImpl(Board board, TileFinder tileFinder, CheckMateTester checkMateTester) {
		this.board = board;
		this.tileFinder = tileFinder;
		this.checkMateTester = checkMateTester;
	}

	@Override
	public void playMove(Coordinate from, Coordinate to) {
		if (!isValidMove(from, to, false)) return;

		Tile fromTile = tileFinder.getTile(from);
		Tile toTile = tileFinder.getTile(to);
		Piece toPiece = toTile.getPiece();

		Piece pieceToMove = fromTile.getPiece();
		Move move = new DefaultMove(fromTile.getCoordinate(), toTile.getCoordinate(), pieceToMove, toPiece);
		board.getMoves().offerLast(move);
		toTile.setPiece(pieceToMove);
		fromTile.empty();

		boolean isOpponentUnderCheck = checkMateTester.isCheck(board.getCurrentPlayer());
		if (isOpponentUnderCheck && !board.isUnderCheck(board.getOpponentPlayer())) {
			board.setUnderCheck(true, board.getOpponentPlayer());
		} else if (!isOpponentUnderCheck && board.isUnderCheck(board.getOpponentPlayer())) {
			board.setUnderCheck(false, board.getOpponentPlayer());
		}

		boolean isCurrentUnderCheck = checkMateTester.isCheck(board.getOpponentPlayer());
		if (isCurrentUnderCheck && !board.isUnderCheck(board.getCurrentPlayer())) {
			board.setUnderCheck(true, board.getCurrentPlayer());
		} else if (!isCurrentUnderCheck && board.isUnderCheck(board.getCurrentPlayer())) {
			board.setUnderCheck(false, board.getCurrentPlayer());
		}

		board.setFinished(checkMateTester.isMate(board.getCurrentPlayer(), board.getOpponentPlayer()));
		board.setFinished(checkMateTester.isMate(board.getOpponentPlayer(), board.getCurrentPlayer()));
	}

	@Override
	public boolean isValidMove(Coordinate from, Coordinate to, boolean hypothetical) {
		Tile fromTile = tileFinder.getTile(from);
		Tile toTile = tileFinder.getTile(to);
		Piece fromPiece = fromTile.getPiece();
		Piece toPiece = toTile.getPiece();

		if (fromPiece == null) return false;

		if (fromPiece.getPlayer() != board.getCurrentPlayer()) return false;

		if (toPiece != null && toPiece.getPlayer() == board.getCurrentPlayer()) return false;

		if (toPiece instanceof King && toPiece.getPlayer() != board.getCurrentPlayer()) return false;

		if (!isValidMoveForPiece(from, to)) return false;

		//if hypothetical and valid, return true
		if (hypothetical) return true;

		Piece pieceToMove = fromTile.getPiece();
		Move move = new DefaultMove(fromTile.getCoordinate(), toTile.getCoordinate(), pieceToMove, toTile.getPiece());
		board.getMoves().offerLast(move);
		toTile.setPiece(pieceToMove);
		fromTile.empty();

		boolean isOpponentUnderCheck = checkMateTester.isCheck(board.getCurrentPlayer());
		if (isOpponentUnderCheck && !board.isUnderCheck(board.getOpponentPlayer())) {
			board.setUnderCheck(true, board.getOpponentPlayer());
		} else if (!isOpponentUnderCheck && board.isUnderCheck(board.getOpponentPlayer())) {
			board.setUnderCheck(false, board.getOpponentPlayer());
		}

		boolean isCurrentUnderCheck = checkMateTester.isCheck(board.getOpponentPlayer());
		if (isCurrentUnderCheck && !board.isUnderCheck(board.getCurrentPlayer())) {
			board.setUnderCheck(true, board.getCurrentPlayer());
		} else if (!isCurrentUnderCheck && board.isUnderCheck(board.getCurrentPlayer())) {
			board.setUnderCheck(false, board.getCurrentPlayer());
		}

		board.setFinished(checkMateTester.isMate(board.getCurrentPlayer(), board.getOpponentPlayer()));
		board.setFinished(checkMateTester.isMate(board.getOpponentPlayer(), board.getCurrentPlayer()));

		return true;
	}

	private boolean isValidMoveForPiece(Coordinate from, Coordinate to) {
		Tile fromTile = tileFinder.getTile(from);
		Piece fromPiece = fromTile.getPiece();
		List<MoveVariant> validMoves = new MoveProviderFactory(board).create(fromTile).getAvailableMoves();
		Tile toTile = tileFinder.getTile(to);

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
