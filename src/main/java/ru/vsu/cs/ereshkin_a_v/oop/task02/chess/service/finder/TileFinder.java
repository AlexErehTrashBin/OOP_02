package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;

import java.util.List;

public interface TileFinder {
	Tile getTile(Board board, Coordinate coordinate);
	Piece getPiece(Board board, Coordinate coordinate);
	void setPiece(Board board, Coordinate coordinate, Piece piece);
	Coordinate getKingLocation(Board board, PieceColor color);
	List<Coordinate> getPiecesCoordinates(Board board, PieceColor color);
}
