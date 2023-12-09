package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Pawn;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;

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
	public boolean playMove(Coordinate from, Coordinate to, Runnable endTurn) {
		if (!isValidMove(from, to, false)) return false;
		Tile fromTile = tileFinder.getTile(from);
		Piece pieceToMove = fromTile.getPiece();

		Tile toTile = tileFinder.getTile(to);
		toTile.setPiece(pieceToMove);

		fromTile.empty();
		endTurn.run();
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

	private boolean isValidMoveForPieceNonRepeatable(Coordinate from, Coordinate to) {
		Piece fromPiece = tileFinder.getPiece(from);
		List<MoveVariant> validMoves = fromPiece.getMoves();
		Tile toTile = tileFinder.getTile(to);

		int xMove = to.getX() - from.getX();
		int yMove = to.getY() - from.getY();

		for (MoveVariant move : validMoves) {
			if (!(move.x == xMove && move.y == yMove)) continue;
			// Если пешка
			if (fromPiece.getClass() == Pawn.class) {
				// Если ход на 2 клетки у пешки
				if ((Math.abs(move.x) == 0 && Math.abs(move.y) == 2)) {
					return toTile.isEmpty() && isFirstMoveForPawn(from);
				} else {
					/// Ход на 1 клетку
					if (!move.onTakeOnly) return toTile.isEmpty();
					if (toTile.isEmpty()) return false;
					Piece toPiece = toTile.getPiece();
					return fromPiece.getColor() != toPiece.getColor();
				}
			}
			if (!move.onTakeOnly) return toTile.isEmpty();
			if (toTile.isEmpty()) return false;
			Piece toPiece = toTile.getPiece();
			return fromPiece.getColor() != toPiece.getColor();
		}
		return false;
	}

	// Determine wheter the Pawn at 'from' on 'board' has moved yet.

	/**
	 * Функция для проверки
	 */
	protected boolean isFirstMoveForPawn(Coordinate from) {
		Tile tile = tileFinder.getTile(from);
		if (tile.isEmpty() || tile.getPiece().getClass() != Pawn.class) {
			return false;
		}
		PieceColor color = tile.getPiece().getColor();
		return (color == PieceColor.WHITE) ? from.getY() == 6 : from.getY() == 1;
	}

	protected boolean isKingCheck(PieceColor kingColor) {
		Coordinate kingLocation = tileFinder.getKingLocation(kingColor);
		return canOpponentTakeLocation(kingLocation, kingColor);
	}

	private boolean canOpponentTakeLocation(Coordinate location, PieceColor color) {
		PieceColor opponentColor = color.getOpponent();
		List<Coordinate> piecesLocation = tileFinder.getPiecesCoordinatesForColor(opponentColor);

		for (Coordinate fromCoordinate : piecesLocation) {
			if (isValidMove(fromCoordinate, location, true)) return true;
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
		List<Coordinate> locations = tileFinder.getPiecesCoordinatesForColor(color);

		for (Coordinate location : locations) {
			Tile fromTile = tileFinder.getTile(location);
			Piece piece = fromTile.getPiece();
			List<Coordinate> possibleMoves = validMovesForPiece(piece, location);

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
					return canPreventCheck;
				}
			}
		}
		return canPreventCheck;
	}

	protected List<Coordinate> validMovesForPiece(Piece piece, Coordinate currentLocation) {
		return piece.hasRepeatableMoves()
				? validMovesRepeatable(piece, currentLocation)
				: validMovesNonRepeatable(piece, currentLocation);
	}

	protected List<Coordinate> validMovesRepeatable(Piece piece, Coordinate currentLocation) {
		List<MoveVariant> moves = piece.getMoves();
		List<Coordinate> possibleMoves = new ArrayList<>();

		for (MoveVariant move : moves) {
			for (int i = 1; i < 7; i++) {
				int newX = currentLocation.getX() + move.x * i;
				int newY = currentLocation.getY() + move.y * i;
				if (newX < 0 || newX > 7 || newY < 0 || newY > 7) break;

				Coordinate toLocation = new Coordinate(newX, newY);
				Tile tile = tileFinder.getTile(toLocation);
				if (tile.isEmpty()) {
					possibleMoves.add(toLocation);
					continue;
				}
				if (tile.getPiece().getColor() != piece.getColor())
					possibleMoves.add(toLocation);
				break;
			}
		}
		return possibleMoves;
	}

	protected List<Coordinate> validMovesNonRepeatable(Piece piece, Coordinate currentLocation) {
		List<MoveVariant> moves = piece.getMoves();
		List<Coordinate> possibleMoves = new ArrayList<>();

		for (MoveVariant move : moves) {
			int currentX = currentLocation.getX();
			int currentY = currentLocation.getY();
			int newX = currentX + move.x;
			int newY = currentY + move.y;
			if (newX < 0 || newX > 7 || newY < 0 || newY > 7) continue;
			Coordinate newLocation = new Coordinate(newX, newY);
			if (isValidMoveForPiece(currentLocation, newLocation)) possibleMoves.add(newLocation);
		}
		return possibleMoves;
	}


	protected boolean isValidMoveForPiece(Coordinate from, Coordinate to) {
		Piece fromPiece = tileFinder.getPiece(from);
		boolean repeatableMoves = fromPiece.hasRepeatableMoves();

		return repeatableMoves
				? isValidMoveForPieceRepeatable(from, to)
				: isValidMoveForPieceNonRepeatable(from, to);
	}

	protected boolean isValidMoveForPieceRepeatable(Coordinate from, Coordinate to) {
		Piece fromPiece = tileFinder.getPiece(from);
		List<MoveVariant> validMoves = fromPiece.getMoves();

		int xMove = to.getX() - from.getX();
		int yMove = to.getY() - from.getY();

		for (int i = 1; i <= 7; i++) {
			for (MoveVariant move : validMoves) {

				//generally check for possible move
				if (!(move.x * i == xMove && move.y * i == yMove)) continue;

				//if move is generally valid - check if path is valid up till i
				for (int j = 1; j <= i; j++) {
					Tile tile = tileFinder.getTile(
							new Coordinate(from.getX() + move.x * j, from.getY() + move.y * j));

					boolean passesThroughNonEmptyTile = j != i && !tile.isEmpty();
					if (passesThroughNonEmptyTile)
						return false;

					//if last move and toTile is empty or holds opponents piece, return true
					if (j == i && (tile.isEmpty() || tile.getPiece().getColor() != board.getCurrentPlayer()))
						return true;
				}
			}
		}
		return false;
	}

	// Determine wheter the Pawn at 'from' on 'board' has moved yet.
}
