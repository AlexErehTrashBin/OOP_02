package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.*;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.printer.BoardPrinter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBoard implements Board {
	protected PieceColor currentPlayer;
	protected boolean isFinished;
	private final BoardPrinter printer;

	public AbstractBoard(PieceColor currentPlayer, BoardPrinter printer) {
		this.printer = printer;
		this.currentPlayer = currentPlayer;
	}

	@Override
	public boolean isValidMove(Coordinate from, Coordinate to, boolean hypothetical){
		Tile fromTile = getTile(from);
		Tile toTile = getTile(to);
		ChessPiece fromPiece = fromTile.getPiece();
		ChessPiece toPiece = toTile.getPiece();

		if (fromPiece == null){
			return false;
		} else if (fromPiece.getColor() != this.currentPlayer) {
			return false;
		} else if (toPiece != null && toPiece.getColor() == this.currentPlayer) {
			return false;
		} else if (isValidMoveForPiece(from, to)){
			//if hypothetical and valid, return true
			if(hypothetical) return true;

			//temporarily play the move to see if it makes us check
			toTile.setPiece(fromPiece);
			fromTile.empty();
			if (isKingCheck(this.currentPlayer)){//check that move doesn't put oneself in check
				toTile.setPiece(toPiece);
				fromTile.setPiece(fromPiece);
				return false;
			}

			//if mate, finish game
			if (isColorCheckMate(ChessPiece.opponent(this.currentPlayer)))
				isFinished = true;

			//revert temporary move
			toTile.setPiece(toPiece);
			fromTile.setPiece(fromPiece);

			return true;
		}
		return false;
	}

	@Override
	public void setCurrentPlayer(PieceColor newPlayer) {
		currentPlayer = newPlayer;
	}

	protected boolean isColorCheckMate(PieceColor color){
		if(!isKingCheck(color)) return false;//if not check, then we're not mate
		return !isCheckPreventable(color);
	}
	protected boolean isCheckPreventable(PieceColor color){
		boolean canPreventCheck = false;
		List<Coordinate> locations = getPiecesCoordinatesForColor(color);

		for(Coordinate location : locations){
			Tile fromTile = getTile(location);
			ChessPiece piece = fromTile.getPiece();
			List<Coordinate> possibleMoves = validMovesForPiece(piece, location);

			for(Coordinate newLocation : possibleMoves){
				Tile toTile = getTile(newLocation);
				ChessPiece toPiece = toTile.getPiece();

				//temporarily play the move to see if it makes us check
				toTile.setPiece(piece);
				fromTile.empty();

				//if we're no longer check
				if (!isKingCheck(color)){
					canPreventCheck = true;
				}

				//revert temporary move
				toTile.setPiece(toPiece);
				fromTile.setPiece(piece);
				if(canPreventCheck){ // early out
					System.out.printf("Prevented with from:" + fromTile + ", to: " + toTile);
					return canPreventCheck;
				}
			}
		}
		return canPreventCheck;
	}

	protected List<Coordinate> validMovesForPiece(ChessPiece piece, Coordinate currentLocation){
		return piece.hasRepeatableMoves()
				? validMovesRepeatable(piece, currentLocation)
				: validMovesNonRepeatable(piece, currentLocation);
	}

	protected List<Coordinate> validMovesRepeatable(ChessPiece piece, Coordinate currentLocation) {
		List<Move> moves = piece.getMoves();
		List<Coordinate> possibleMoves = new ArrayList<>();

		for(Move move: moves){
			for(int i = 1; i < 7; i++){
				int newX = currentLocation.getX() + move.x * i;
				int newY = currentLocation.getY() + move.y * i;
				if (newX < 0 || newX > 7 || newY < 0 || newY > 7) break;

				Coordinate toLocation = new Coordinate(newX, newY);
				Tile tile = getTile(toLocation);
				if (tile.isEmpty()) {
					possibleMoves.add(toLocation);
				} else {
					if (tile.getPiece().getColor() != piece.getColor())
						possibleMoves.add(toLocation);
					break;
				}
			}
		}
		return possibleMoves;
	}

	protected List<Coordinate> validMovesNonRepeatable(ChessPiece piece, Coordinate currentLocation) {
		List<Move> moves = piece.getMoves();
		List<Coordinate> possibleMoves = new ArrayList<>();

		for(Move move: moves){
			int currentX = currentLocation.getX();
			int currentY = currentLocation.getY();
			int newX = currentX + move.x;
			int newY = currentY + move.y;
			if (newX < 0 || newX > 7 || newY < 0 || newY > 7) continue;
			Coordinate newLocation = new Coordinate(newX,newY);
			if (isValidMoveForPiece(currentLocation, newLocation)) possibleMoves.add(newLocation);
		}
		return possibleMoves;
	}



	protected boolean isValidMoveForPiece(Coordinate from, Coordinate to){
		ChessPiece fromPiece = getPiece(from);
		boolean repeatableMoves = fromPiece.hasRepeatableMoves();

		return repeatableMoves
				? isValidMoveForPieceRepeatable(from, to)
				: isValidMoveForPieceNonRepeatable(from, to);
	}

	protected boolean isValidMoveForPieceRepeatable(Coordinate from, Coordinate to) {
		ChessPiece fromPiece = getPiece(from);
		List<Move> validMoves = fromPiece.getMoves();

		int xMove = to.getX() - from.getX();
		int yMove = to.getY() - from.getY();

		for(int i = 1; i <= 7; i++){
			for(Move move : validMoves) {

				//generally check for possible move
				if (move.x * i == xMove && move.y * i == yMove) {

					//if move is generally valid - check if path is valid up till i
					for (int j = 1; j <= i; j++){
						Tile tile = getTile(
								new Coordinate(from.getX() + move.x * j, from.getY() +move.y * j));

						boolean passesThroughNonEmptyTile = j != i && !tile.isEmpty();
						if (passesThroughNonEmptyTile)
							return false;

						//if last move and toTile is empty or holds opponents piece, return true
						if (j == i && (tile.isEmpty() || tile.getPiece().getColor() != currentPlayer))
							return true;
					}
				}
			}
		}
		return false;
	}

	private boolean isValidMoveForPieceNonRepeatable(Coordinate from, Coordinate to){
		ChessPiece fromPiece = getPiece(from);
		List<Move> validMoves = fromPiece.getMoves();
		Tile toTile = getTile(to);

		int xMove = to.getX() - from.getX();
		int yMove = to.getY() - from.getY();

		for (Move move : validMoves) {
			if (move.x == xMove && move.y == yMove) {
				if (move.onTakeOnly){//if move is only legal on take (pawns)
					if (toTile.isEmpty()) return false;

					ChessPiece toPiece = toTile.getPiece();
					return fromPiece.getColor() != toPiece.getColor();//if different color, valid move

					//handling first move only for pawns - they should not have moved yet
				} else if (move.firstMoveOnly) {
					return toTile.isEmpty() && isFirstMoveForPawn(from);
				} else {
					return toTile.isEmpty();
				}
			}
		}
		return false;
	}

	// Determine wheter the Pawn at 'from' on 'board' has moved yet.
	/**
	 * Функция для проверки
	 * */
	protected boolean isFirstMoveForPawn(Coordinate from){
		Tile tile = getTile(from);
		if (tile.isEmpty() || tile.getPiece().getPieceType() != ChessPiece.PieceType.Pawn) {
			return false;
		} else {
			PieceColor color = tile.getPiece().getColor();
			return (color == PieceColor.White)
					? from.getY() == 6
					: from.getY() == 1;
		}
	}

	protected boolean isKingCheck(PieceColor kingColor){
		Coordinate kingLocation = getKingLocation(kingColor);
		return canOpponentTakeLocation(kingLocation, kingColor);
	}

	private boolean canOpponentTakeLocation(Coordinate location, PieceColor color){
		PieceColor opponentColor = ChessPiece.opponent(color);
		List<Coordinate> piecesLocation = getPiecesCoordinatesForColor(opponentColor);

		for(Coordinate fromCoordinate : piecesLocation) {
			if (isValidMove(fromCoordinate, location, true))
				return true;
		}
		return false;
	}

	@Override
	public void print() {
		printer.print(this);
	}
}
