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
	private static TileFinderImpl instance;

	private TileFinderImpl() {
	}

	public static TileFinderImpl getInstance() {
		if (instance == null) {
			instance = new TileFinderImpl();
		}
		return instance;
	}

	@Override
	public Tile getTile(Board board, Coordinate coordinate) {
		int size = board.getSize();
		Tile fromTile = board.getUpperLeftTile();
		if (coordinate.getX() > size / 2) {
			if (coordinate.getY() > size / 2) {
				fromTile = board.getLowerRightTile();
			}
		}

		if (coordinate.getX() > size / 2) {
			if (coordinate.getY() < size / 2) {
				fromTile = board.getUpperRightTile();
			}
		}

		if (coordinate.getX() < size / 2) {
			if (coordinate.getY() < size / 2) {
				fromTile = board.getUpperLeftTile();
			}
		}

		if (coordinate.getX() < size / 2) {
			if (coordinate.getY() > size / 2) {
				fromTile = board.getLowerLeftTile();
			}
		}
		if (fromTile.getCoordinate().equals(coordinate)) return fromTile;

		int direction = getMostAppropriateDirection(fromTile.getCoordinate(), coordinate);
		if (direction == -1) return null;
		Tile neighbourTile = getTile(board, coordinate, fromTile.getNeighbors().get(direction));
		if (neighbourTile == null) return null;
		if (neighbourTile.getCoordinate().equals(coordinate)) return neighbourTile;
		return null;
	}


	private Tile getTile(Board board, Coordinate coordinateToFind, Tile currentTile) {
		if (currentTile == null) return null;
		if (currentTile.getCoordinate().equals(coordinateToFind)) return currentTile;
		int direction = getMostAppropriateDirection(currentTile.getCoordinate(), coordinateToFind);
		if (direction == -1) return null;
		Tile neighbourTile = getTile(board, coordinateToFind, currentTile.getNeighbors().get(direction));
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
	public Piece getPiece(Board board, Coordinate coordinate) {
		Tile tile = getTile(board, coordinate);
		if (tile == null) return null;
		if (tile.getPiece() == null) return null;
		return tile.getPiece();
	}

	@Override
	public void setPiece(Board board, Coordinate coordinate, Piece piece) {
		getTile(board, coordinate).setPiece(piece);
	}

	@Override
	public Coordinate getKingLocation(Board board, PieceColor color) {
		Coordinate location = new Coordinate(-1, -1);
		for (int x = 0; x <= 7; x++) {
			for (int y = 0; y <= 7; y++) {
				if (getTile(board, new Coordinate(x, y)).isEmpty()) continue;

				Coordinate coordinate = new Coordinate(x, y);
				Piece piece = getPiece(board, coordinate);
				if (piece.getColor() == color && piece instanceof King) {
					location = coordinate;
				}
			}
		}
		return location;
	}

	@Override
	public List<Coordinate> getPiecesCoordinates(Board board, PieceColor color) {
		List<Coordinate> locations = new ArrayList<>();
		for (int x = 0; x <= 7; x++) {
			for (int y = 0; y <= 7; y++) {
				Tile tile = getTile(board, new Coordinate(x, y));
				if (!tile.isEmpty() && tile.getPiece().getTeam().getColor() == color) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		return locations;
	}
}
