package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;

import java.util.List;

public abstract class AbstractMoveProvider implements MoveProvider {
	protected final Tile tile;
	protected final Board board;
	public AbstractMoveProvider(Board board, Tile tile) {
		this.tile = tile;
		this.board = board;
	}
	public Coordinate getCoordinate() {
		return tile.getCoordinate().clone();
	}

	public Piece getPiece() {
		return tile.getPiece();
	}

	protected void propagateInDirection(List<MoveVariant> variants, int direction,
	                                  Coordinate startCoordinate, Tile currentTile) {
		if (currentTile == null || (!currentTile.isEmpty() && currentTile.getPiece().getPlayer() == board.getCurrentPlayer())) return;

		Coordinate currentCoordinate = currentTile.getCoordinate();
		int moveX = currentCoordinate.getX() - startCoordinate.getX();
		int moveY = currentCoordinate.getY() - startCoordinate.getY();
		variants.add(new MoveVariant(moveX, moveY));
		Tile nextTile = currentTile.getNeighborsUnsafe().get(direction);
		if (currentTile.isEmpty()) {
			propagateInDirection(variants, direction, startCoordinate, nextTile);
		}
	}

	protected MoveVariant getMoveVariantByCoordinates(Coordinate start, Coordinate end){
		int moveX = end.getX() - start.getX();
		int moveY = end.getY() - start.getY();
		return new MoveVariant(moveX, moveY);
	}
}
