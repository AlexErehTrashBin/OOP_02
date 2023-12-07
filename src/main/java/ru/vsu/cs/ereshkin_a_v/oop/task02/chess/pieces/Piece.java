package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;

import java.util.List;

public interface Piece {
	PieceType getPieceType();
	boolean hasRepeatableMoves();
	char getCharValue();
	PieceColor getColor();
	String getName();
	List<MoveVariant> getMoves();
}
