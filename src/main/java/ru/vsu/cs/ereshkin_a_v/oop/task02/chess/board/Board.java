package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.ChessPiece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Tile;

import java.util.List;

public interface Board {
	List<Coordinate> getPiecesCoordinatesForColor(PieceColor color);
	Coordinate getKingLocation(PieceColor color);
	void setPiece(Coordinate coordinate, ChessPiece piece);
	ChessPiece getPiece(Coordinate coordinate);
	PieceColor getPieceColor(Coordinate coordinate);
	Tile getTile(Coordinate coordinate);
	boolean playMove(Coordinate from, Coordinate to, Runnable endTurn);
	boolean isValidMove(Coordinate from, Coordinate to, boolean hypothetical);
	boolean isFinished();
	void setCurrentPlayer(PieceColor newPlayer);
	void print();
}