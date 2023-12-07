package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Слон
 * */
public class Bishop extends AbstractPiece {
	public Bishop(PieceColor color) {
		super(PieceType.Bishop, color, validMoves(), true);
	}


	private static List<MoveVariant> validMoves() {
		List<MoveVariant> result = new ArrayList<>();
		result.add(new MoveVariant(1, 1, false));
		result.add(new MoveVariant(1, -1, false));
		result.add(new MoveVariant(-1, 1, false));
		result.add(new MoveVariant(-1, -1, false));
		return result;
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♝' : '♗';
	}
}
