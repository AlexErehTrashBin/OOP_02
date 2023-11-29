package ru.vsu.cs.ereshkin_a_v.oop.task02.chess;

// TODO Добавить ссылки на соседние тайлы (список)
// TODO Определить тайл с помощью строкового ключа

// TODO Получать необходимы фигуре тайлы с помощью методов получения списка, пробегаться по тайлам.

// TODO Имеет смысл сделать не список, а мапу, ключ -
// Enum где значение верхняя диагональная, верхняя левая диагональная,
// и тогда в Tile делать мапу где 8 пар ключ-значение,
// где ключи - названия енамов, значение - ссылка на экземпляр клетки.

// Буквально

public class Tile implements Comparable<Tile>{
	private ChessPiece piece;
	private final Coordinate coordinate;

	public Tile(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public ChessPiece getPiece() {
		return this.piece;
	}

	public void setPiece(ChessPiece piece) {
		this.piece = piece;
	}

	public String getValue() {
		if (piece != null) return "[" + piece.getCharValue() + "]";
		return "[ ]";
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

	@Override
	public int compareTo(Tile o) {
		if (o.coordinate.getY() < coordinate.getY()) return -1;
		if (o.coordinate.getY() > coordinate.getY()) return 1;
		if (o.coordinate.getX() < coordinate.getX()) return -1;
		if (o.coordinate.getX() > coordinate.getX()) return 1;
		return 0;
	}

	public enum TileColor {
		White, Black
	}
}
