package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.TileDirections.*;

public class RookMoveProvider extends AbstractMoveProvider {
	public RookMoveProvider(Board board, Tile tile) {
		super(board, tile);
	}

	@Override
	public List<MoveVariant> getAvailableMoves() {
		List<MoveVariant> result = new ArrayList<>();
		Tile startTile = tile;
		Coordinate startCoordinate = tile.getCoordinate();

		propagateInDirection(result, UP, startCoordinate, startTile);
		propagateInDirection(result, DOWN, startCoordinate, startTile);
		propagateInDirection(result, LEFT, startCoordinate, startTile);
		propagateInDirection(result, RIGHT, startCoordinate, startTile);

		return result;
	}
}
