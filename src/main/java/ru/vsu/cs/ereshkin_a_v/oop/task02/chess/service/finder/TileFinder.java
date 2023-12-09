package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.AbstractPiece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;

import java.util.List;

public interface TileFinder {
	Tile getTile(Coordinate coordinate);
	Piece getPiece(Coordinate coordinate);
	void setPiece(Coordinate coordinate, AbstractPiece piece);
	Coordinate getKingLocation(PieceColor color);
	List<Coordinate> getPiecesCoordinatesForColor(PieceColor color);
}
