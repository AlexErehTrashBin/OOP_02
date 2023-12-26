package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.TileDirections;

import java.util.ArrayList;
import java.util.List;

public class KnightMoveProvider extends AbstractMoveProvider {
	public KnightMoveProvider(Board board, Tile tile) {
		super(board, tile);
	}

	@Override
	public List<MoveVariant> getAvailableMoves() {
		List<MoveVariant> variants = new ArrayList<>();
		addMoveInVariant(variants, TileDirections.UP, TileDirections.LEFT);
		addMoveInVariant(variants, TileDirections.UP, TileDirections.RIGHT);
		addMoveInVariant(variants, TileDirections.DOWN, TileDirections.LEFT);
		addMoveInVariant(variants, TileDirections.DOWN, TileDirections.RIGHT);
		addMoveInVariant(variants, TileDirections.LEFT, TileDirections.UP);
		addMoveInVariant(variants, TileDirections.LEFT, TileDirections.DOWN);
		addMoveInVariant(variants, TileDirections.RIGHT, TileDirections.UP);
		addMoveInVariant(variants, TileDirections.RIGHT, TileDirections.DOWN);
		return variants;
	}

	private void addMoveInVariant(List<MoveVariant> variants, int doubleDirection,
	                              int sideDirection) {
		Tile firstNextTile = tile.getNeighbors().get(doubleDirection);
		if (firstNextTile == null) return;
		Tile secondNextTile = firstNextTile.getNeighbors().get(doubleDirection);
		if (secondNextTile == null) return;
		Tile finalTile = secondNextTile.getNeighbors().get(sideDirection);
		if (finalTile == null || !finalTile.isEmpty()) return;
		Coordinate start = tile.getCoordinate();
		Coordinate end = finalTile.getCoordinate();
		int moveX = end.getX() - start.getX();
		int moveY = end.getY() - start.getY();
		MoveVariant variant = new MoveVariant(moveX, moveY);
		variants.add(variant);
	}
}
