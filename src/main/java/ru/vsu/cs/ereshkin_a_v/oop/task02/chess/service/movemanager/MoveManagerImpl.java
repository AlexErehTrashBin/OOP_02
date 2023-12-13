package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider.MoveProviderFactory;

import java.util.ArrayList;
import java.util.List;

public class MoveManagerImpl implements MoveManager {
	private final Board board;
	private final TileFinder tileFinder;
	public MoveManagerImpl(Board board, TileFinder tileFinder) {
		this.board = board;
		this.tileFinder = tileFinder;
	}

	@Override
	public boolean playMove(Coordinate from, Coordinate to) {
		if (!isValidMove(from, to, false)) return false;
		Tile fromTile = tileFinder.getTile(from);
		Piece pieceToMove = fromTile.getPiece();

		Tile toTile = tileFinder.getTile(to);
		toTile.setPiece(pieceToMove);

		fromTile.empty();
		return true;
	}

	@Override
	public boolean isValidMove(Coordinate from, Coordinate to, boolean hypothetical) {
		Tile fromTile = tileFinder.getTile(from);
		Tile toTile = tileFinder.getTile(to);
		Piece fromPiece = fromTile.getPiece();
		Piece toPiece = toTile.getPiece();

		if (fromPiece == null) return false;

		if (fromPiece.getColor() != board.getCurrentPlayer()) return false;

		if (toPiece != null && toPiece.getColor() == board.getCurrentPlayer()) return false;

		if (!isValidMoveForPiece(from, to)) return false;

		//if hypothetical and valid, return true
		if (hypothetical) return true;

		//temporarily play the move to see if it makes us check
		toTile.setPiece(fromPiece);
		fromTile.empty();
		if (isKingCheck(board.getCurrentPlayer())) {//check that move doesn't put oneself in check
			toTile.setPiece(toPiece);
			fromTile.setPiece(fromPiece);

			return false;
		}


		//if mate, finish game
		if (isColorCheckMate(board.getCurrentPlayer().getOpponent()))
			board.setFinished(true);

		//revert temporary move
		toTile.setPiece(toPiece);
		fromTile.setPiece(fromPiece);

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

	protected boolean isKingCheck(PieceColor kingColor) {
		// TODO здесь проверять ход на шах
		Coordinate kingLocation = tileFinder.getKingLocation(kingColor);
		PieceColor opponentColor = kingColor.getOpponent();
		List<Coordinate> piecesLocation = tileFinder.getPiecesCoordinates(opponentColor);

		for (Coordinate fromCoordinate : piecesLocation) {
			if (isValidMove(fromCoordinate, kingLocation, true)) return true;
		}
		return false;
	}

	protected boolean isColorCheckMate(PieceColor color) {

		if (!isKingCheck(color)) return false;
		// If not check, then we're not mate
		return !isCheckPreventable(color);
	}

	protected boolean isCheckPreventable(PieceColor color) {
		boolean canPreventCheck = false;
		List<Coordinate> locations = tileFinder.getPiecesCoordinates(color);

		for (Coordinate location : locations) {
			Tile fromTile = tileFinder.getTile(location);
			Piece piece = fromTile.getPiece();
			List<Coordinate> possibleMoves = validMovesForPiece(fromTile, location);

			for (Coordinate newLocation : possibleMoves) {
				Tile toTile = tileFinder.getTile(newLocation);
				Piece toPiece = toTile.getPiece();

				//temporarily play the move to see if it makes us check
				toTile.setPiece(piece);
				fromTile.empty();

				//if we're no longer check
				if (!isKingCheck(color)) {
					canPreventCheck = true;
				}

				//revert temporary move
				toTile.setPiece(toPiece);
				fromTile.setPiece(piece);
				if (canPreventCheck) { // early out
					System.out.printf("Prevented with from:" + fromTile + ", to: " + toTile);
					return true;
				}
			}
		}
		return false;
	}

	protected List<Coordinate> validMovesForPiece(Tile tile, Coordinate currentLocation) {
		return validMovesNonRepeatable(tile, currentLocation);
	}

	protected List<Coordinate> validMovesNonRepeatable(Tile tile, Coordinate currentLocation) {
		List<MoveVariant> moves = new MoveProviderFactory(board).create(tile).getAvailableMoves();
		List<Coordinate> possibleMoves = new ArrayList<>();

		for (MoveVariant move : moves) {
			int currentX = currentLocation.getX();
			int currentY = currentLocation.getY();
			int newX = currentX + move.getX();
			int newY = currentY + move.getY();
			if (newX < 0 || newX > 7 || newY < 0 || newY > 7) continue;
			Coordinate newLocation = new Coordinate(newX, newY);
			if (isValidMoveForPiece(currentLocation, newLocation)) possibleMoves.add(newLocation);
		}
		return possibleMoves;
	}

}
