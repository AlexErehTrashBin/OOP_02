package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;

import java.util.List;

public interface Piece {
	boolean hasRepeatableMoves();
	char getCharValue();
	PieceColor getColor();
	String getName();
	List<MoveVariant> getMoves();
}
