package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.King;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.TileDirections;

import java.util.ArrayList;
import java.util.List;

public class TileFinderImpl implements TileFinder {
	private final Board board;
	public TileFinderImpl(Board board) {
		this.board = board;
	}

	@Override
	public Tile getTile(Coordinate coordinate) {
		int size = board.getSize();
		Tile fromTile = board.getUpperLeftTile();
		if (coordinate.getX() > size/2) {
			if (coordinate.getY() > size / 2) {
				fromTile = board.getLowerRightTile();
			}
		}

		if (coordinate.getX() > size/2) {
			if (coordinate.getY() < size / 2) {
				fromTile = board.getUpperRightTile();
			}
		}

		if (coordinate.getX() < size/2) {
			if (coordinate.getY() < size / 2) {
				fromTile = board.getUpperLeftTile();
			}
		}

		if (coordinate.getX() < size/2) {
			if (coordinate.getY() > size / 2) {
				fromTile = board.getLowerLeftTile();
			}
		}
		if (fromTile.getCoordinate().equals(coordinate)) return fromTile;

		int direction = getMostAppropriateDirection(fromTile.getCoordinate(), coordinate);
		if (direction == -1) return null;
		Tile neighbourTile = getTile(coordinate, fromTile.getNeighborsUnsafe().get(direction));
		if (neighbourTile == null) return null;
		if (neighbourTile.getCoordinate().equals(coordinate)) return neighbourTile;
		return null;
	}


	private Tile getTile(Coordinate coordinateToFind, Tile currentTile) {
		if (currentTile == null) return null;
		if (currentTile.getCoordinate().equals(coordinateToFind)) return currentTile;
		int direction = getMostAppropriateDirection(currentTile.getCoordinate(), coordinateToFind);
		if (direction == -1) return null;
		Tile neighbourTile = getTile(coordinateToFind, currentTile.getNeighborsUnsafe().get(direction));
		if (neighbourTile == null) return null;
		if (neighbourTile.getCoordinate().equals(coordinateToFind)) return neighbourTile;
		return null;
	}

	private int getMostAppropriateDirection(Coordinate currentCoordinate, Coordinate targetCoordinate) {

		if (currentCoordinate.getX() < targetCoordinate.getX()) {
			//Лучше двигать вправо
			if (currentCoordinate.getY() < targetCoordinate.getY()) {
				// Лучше двигать вниз
				return TileDirections.RIGHT_DOWN;
			} else if (currentCoordinate.getY() > targetCoordinate.getY()) {
				// Лучше двигать вверх
				return TileDirections.RIGHT_UP;
			} else {
				return TileDirections.RIGHT;
			}
		}

		if (currentCoordinate.getX() > targetCoordinate.getX()) {
			//Лучше двигать влево
			if (currentCoordinate.getY() < targetCoordinate.getY()) {
				// Лучше двигать вниз
				return TileDirections.LEFT_DOWN;
			} else if (currentCoordinate.getY() > targetCoordinate.getY()) {
				// Лучше двигать вверх
				return TileDirections.LEFT_UP;
			} else {
				return TileDirections.LEFT;
			}
		}

		if (currentCoordinate.getY() < targetCoordinate.getY()) {
			//Лучше двигать вниз
			return TileDirections.DOWN;
		}

		if (currentCoordinate.getY() > targetCoordinate.getY()) {
			//Лучше двигать вверх
			return TileDirections.UP;
		}

		return -1;
	}

	@Override
	public Piece getPiece(Coordinate coordinate) {
		Tile tile = getTile(coordinate);
		if (tile == null) return null;
		if (tile.getPiece() == null) return null;
		return tile.getPiece();
	}

	@Override
	public void setPiece(Coordinate coordinate, Piece piece) {
		getTile(coordinate).setPiece(piece);
	}

	@Override
	public Coordinate getKingLocation(PieceColor color) {
		Coordinate location = new Coordinate(-1, -1);
		for (int x = 0; x <= 7; x++) {
			for (int y = 0; y <= 7; y++) {
				if (getTile(new Coordinate(x, y)).isEmpty()) continue;

				Coordinate coordinate = new Coordinate(x, y);
				Piece piece = getPiece(coordinate);
				if (piece.getColor() == color && piece instanceof King) {
					location = coordinate;
				}
			}
		}
		return location;
	}

	@Override
	public List<Coordinate> getPiecesCoordinates(PieceColor color) {
		List<Coordinate> locations = new ArrayList<>();
		for (int x = 0; x <= 7; x++) {
			for (int y = 0; y <= 7; y++) {
				Tile tile = getTile(new Coordinate(x, y));
				if (!tile.isEmpty() && tile.getPiece().getColor() == color) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		return locations;
	}
}
