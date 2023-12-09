package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Tile extends TileDirections {
	private Piece piece;
	private final Coordinate coordinate;
	private final List<Tile> neighbors;

	public List<Tile> getNeighborsUnsafe() {
		return neighbors;
	}

	public Tile(Coordinate coordinate) {
		neighbors = new ArrayList<>();
		this.coordinate = coordinate;
	}

	public void setNeighbors(List<Tile> neighbors) {
		this.neighbors.clear();
		this.neighbors.addAll(neighbors);
	}

	public Piece getPiece() {
		return this.piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public String getValue() {
		if (piece != null) return "[" + piece.getCharValue() + "]";
		return "[　]";
	}

	public Coordinate getCoordinate() {
		return coordinate.clone();
	}

	public boolean isEmpty() {
		return piece == null;
	}

	public void empty() {
		piece = null;
	}
}
