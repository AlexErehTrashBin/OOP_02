package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.finder;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.ChessPiece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Tile;

import java.util.List;

public interface TileFinder {
	Tile getTile(Coordinate coordinate);
	ChessPiece getPiece(Coordinate coordinate);
	void setPiece(Coordinate coordinate, ChessPiece piece);
	Coordinate getKingLocation(PieceColor color);
	List<Coordinate> getPiecesCoordinatesForColor(PieceColor color);
}
