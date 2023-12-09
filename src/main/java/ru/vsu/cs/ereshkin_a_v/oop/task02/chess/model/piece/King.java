package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
	public King(PieceColor color) {
		super(color, "King", validMoves(), false);
	}

	private static List<MoveVariant> validMoves() {
		List<MoveVariant> result = new ArrayList<>();
		result.add(new MoveVariant(1, 0, false));
		result.add(new MoveVariant(0, 1, false));
		result.add(new MoveVariant(-1, 0, false));
		result.add(new MoveVariant(0, -1, false));
		result.add(new MoveVariant(1, 1, false));
		result.add(new MoveVariant(1, -1, false));
		result.add(new MoveVariant(-1, 1, false));
		result.add(new MoveVariant(-1, -1, false));
		return result;
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♚' : '♔';
	}
}
