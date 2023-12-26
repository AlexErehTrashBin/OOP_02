package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile;

import lombok.Getter;
import lombok.Setter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Tile {
	@Setter
	private Piece piece;
	private final Coordinate coordinate;
	private final List<Tile> neighbors;

	public Tile(Coordinate coordinate) {
		neighbors = new ArrayList<>();
		this.coordinate = coordinate;
	}

	public void setNeighbors(List<Tile> neighbors) {
		this.neighbors.clear();
		this.neighbors.addAll(neighbors);
	}

	public String getValue() {
		if (piece != null) return "[" + piece.getCharValue() + "]";
		return "[　]";
	}

	public boolean isEmpty() {
		return piece == null;
	}

	public void empty() {
		piece = null;
	}
}
