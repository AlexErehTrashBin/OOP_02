package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {
	public Knight(PieceColor color) {
		super(color, "Knight", validMoves(), false);
	}


	private static List<MoveVariant> validMoves() {
		List<MoveVariant> result = new ArrayList<>();

		result.add(new MoveVariant(2, 1, false));
		result.add(new MoveVariant(1, 2, false));
		result.add(new MoveVariant(2, -1, false));
		result.add(new MoveVariant(-1, 2, false));
		result.add(new MoveVariant(2, -1, false));
		result.add(new MoveVariant(-1, 2, false));
		result.add(new MoveVariant(-2, 1, false));
		result.add(new MoveVariant(1, -2, false));
		result.add(new MoveVariant(-2, -1, false));
		result.add(new MoveVariant(-1, -2, false));
		result.add(new MoveVariant(-2, -1, false));
		result.add(new MoveVariant(-1, -2, false));


		return result;
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♞' : '♘';
	}
}
