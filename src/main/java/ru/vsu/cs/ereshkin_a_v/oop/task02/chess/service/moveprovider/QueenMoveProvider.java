package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.TileDirections.*;

public class QueenMoveProvider extends AbstractMoveProvider {
	public QueenMoveProvider(Board board, Tile tile) {
		super(board, tile);
	}

	@Override
	public List<MoveVariant> getAvailableMoves() {
		List<MoveVariant> result = new ArrayList<>();
		Tile startTile = tile;
		Coordinate startCoordinate = tile.getCoordinate();

		propagateInDirection(result, UP, startCoordinate, startTile.getNeighbors().get(UP));
		propagateInDirection(result, RIGHT_UP, startCoordinate, startTile.getNeighbors().get(RIGHT_UP));
		propagateInDirection(result, RIGHT, startCoordinate, startTile.getNeighbors().get(RIGHT));
		propagateInDirection(result, RIGHT_DOWN, startCoordinate, startTile.getNeighbors().get(RIGHT_DOWN));
		propagateInDirection(result, DOWN, startCoordinate, startTile.getNeighbors().get(DOWN));
		propagateInDirection(result, LEFT_DOWN, startCoordinate, startTile.getNeighbors().get(LEFT_DOWN));
		propagateInDirection(result, LEFT, startCoordinate, startTile.getNeighbors().get(LEFT));
		propagateInDirection(result, LEFT_UP, startCoordinate, startTile.getNeighbors().get(LEFT_UP));

		return result;
	}
}
