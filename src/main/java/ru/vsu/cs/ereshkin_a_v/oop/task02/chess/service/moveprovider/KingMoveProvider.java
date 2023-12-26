package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.TileDirections;

import java.util.ArrayList;
import java.util.List;

public class KingMoveProvider extends AbstractMoveProvider {
	public KingMoveProvider(Board board, Tile tile) {
		super(board, tile);
	}

	private void addTileInDirection(List<MoveVariant> variants, int direction) {
		Tile sideTile = tile.getNeighbors().get(direction);
		if (sideTile == null) return;
		if (!sideTile.isEmpty() && sideTile.getPiece().getTeam() != board.getCurrentTeam()) return;
		Coordinate start = tile.getCoordinate();
		Coordinate end = sideTile.getCoordinate();
		MoveVariant variant = getMoveVariantByCoordinates(start, end);
		variants.add(variant);
	}

	@Override
	public List<MoveVariant> getAvailableMoves() {
		List<MoveVariant> variants = new ArrayList<>();
		addTileInDirection(variants, TileDirections.UP);
		addTileInDirection(variants, TileDirections.RIGHT_UP);
		addTileInDirection(variants, TileDirections.RIGHT);
		addTileInDirection(variants, TileDirections.RIGHT_DOWN);
		addTileInDirection(variants, TileDirections.DOWN);
		addTileInDirection(variants, TileDirections.LEFT_DOWN);
		addTileInDirection(variants, TileDirections.LEFT);
		addTileInDirection(variants, TileDirections.LEFT_UP);
		addTileInDirection(variants, TileDirections.UP);
		return variants;
	}
}
