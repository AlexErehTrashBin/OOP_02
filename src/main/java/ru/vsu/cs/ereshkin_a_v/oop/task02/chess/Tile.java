package ru.vsu.cs.ereshkin_a_v.oop.task02.chess;

import java.util.ArrayList;
import java.util.List;

public class Tile {
	public static final int UP = 0;
	public static final int RIGHT_UP = 1;
	public static final int RIGHT = 2;
	public static final int RIGHT_DOWN = 3;
	public static final int DOWN = 4;
	public static final int LEFT_DOWN = 5;
	public static final int LEFT = 6;
	public static final int LEFT_UP = 7;
	public static final int DIRECTIONS_COUNT = 8;
	private ChessPiece piece;
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

	public ChessPiece getPiece() {
		return this.piece;
	}

	public void setPiece(ChessPiece piece) {
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
