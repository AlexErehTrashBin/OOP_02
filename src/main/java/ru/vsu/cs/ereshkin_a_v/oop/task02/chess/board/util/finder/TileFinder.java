package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.finder;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces.AbstractPiece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces.Piece;

import java.util.List;

public interface TileFinder {
	Tile getTile(Coordinate coordinate);
	Piece getPiece(Coordinate coordinate);
	void setPiece(Coordinate coordinate, AbstractPiece piece);
	Coordinate getKingLocation(PieceColor color);
	List<Coordinate> getPiecesCoordinatesForColor(PieceColor color);
}
