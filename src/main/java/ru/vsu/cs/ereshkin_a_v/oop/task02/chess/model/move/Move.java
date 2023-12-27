package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;

import java.util.Optional;

public interface Move {
	Piece getPiece();
	Optional<Piece> getKilledPiece();
	Coordinate getStart();
	Coordinate getEnd();
	String getType();
}
