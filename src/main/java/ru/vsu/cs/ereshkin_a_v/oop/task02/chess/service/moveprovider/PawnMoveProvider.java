package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.TileDirections.*;

public class PawnMoveProvider extends AbstractMoveProvider {
	public PawnMoveProvider(Board board, Tile tile) {
		super(board, tile);
	}

	@Override
	public List<MoveVariant> getAvailableMoves() {
		List<MoveVariant> result = new ArrayList<>();
		PieceColor pieceColor = tile.getPiece().getPlayer().getColor();
		if (pieceColor == PieceColor.BLACK) {
			return makeMoveVariantsForBlack();
		} else if (pieceColor == PieceColor.WHITE) {
			return makeMoveVariantsForWhite();
		}
		return result;
	}

	private List<MoveVariant> makeMoveVariantsForBlack() {
		List<MoveVariant> result = new ArrayList<>();
		Tile downTile = tile.getNeighborsUnsafe().get(DOWN);
		boolean existsDownTile = downTile != null;
		if (existsDownTile && downTile.isEmpty()) {
			result.add(new MoveVariant(0, 1));
		}
		int defaultY = 1;
		boolean isInDefaultPosition = tile.getCoordinate().getY() == defaultY;
		if (isInDefaultPosition && downTile != null) {
			Tile doubleDownTile = downTile.getNeighborsUnsafe().get(DOWN);
			if (doubleDownTile != null && doubleDownTile.isEmpty()) {
				result.add(new MoveVariant(0, 2));
			}
		}

		Tile rightDownTile = tile.getNeighborsUnsafe().get(RIGHT_DOWN);
		boolean existsRightDownTile = rightDownTile != null;
		if (existsRightDownTile && !rightDownTile.isEmpty()) {
			boolean enemyInTile = rightDownTile.getPiece().getColor() != tile.getPiece().getColor();
			if (enemyInTile) {
				result.add(new MoveVariant(1,1));
			}
		}

		Tile leftDownTile = tile.getNeighborsUnsafe().get(LEFT_DOWN);
		boolean existsLeftDownTile = leftDownTile != null;
		if (existsLeftDownTile && !leftDownTile.isEmpty()) {
			boolean enemyInTile = leftDownTile.getPiece().getColor() != tile.getPiece().getColor();
			if (enemyInTile) {
				result.add(new MoveVariant(-1,1));
			}
		}
		return result;
	}


	private List<MoveVariant> makeMoveVariantsForWhite() {
		List<MoveVariant> result = new ArrayList<>();
		Tile upTile = tile.getNeighborsUnsafe().get(UP);
		boolean existsUpTile = upTile != null;
		if (existsUpTile && upTile.isEmpty()) {
			result.add(new MoveVariant(0, -1));
		}
		int defaultY = board.getSize() - 2;
		boolean isInDefaultPosition = tile.getCoordinate().getY() == defaultY;
		if (isInDefaultPosition && upTile != null) {
			Tile doubleUpTile = upTile.getNeighborsUnsafe().get(UP);
			if (doubleUpTile != null && doubleUpTile.isEmpty()) {
				result.add(new MoveVariant(0, -2));
			}
		}

		Tile rightUpTile = tile.getNeighborsUnsafe().get(RIGHT_UP);
		boolean existsRightUpTile = rightUpTile != null;
		if (existsRightUpTile && !rightUpTile.isEmpty()) {
			boolean enemyInTile = rightUpTile.getPiece().getColor() != tile.getPiece().getColor();
			if (enemyInTile) {
				result.add(new MoveVariant(1,-1));
			}
		}

		Tile leftUpTile = tile.getNeighborsUnsafe().get(LEFT_UP);
		boolean existsLeftUpTile = leftUpTile != null;
		if (existsLeftUpTile && !leftUpTile.isEmpty()) {
			boolean enemyInTile = leftUpTile.getPiece().getColor() != tile.getPiece().getColor();
			if (enemyInTile) {
				result.add(new MoveVariant(-1,-1));
			}
		}
		return result;
	}
}
