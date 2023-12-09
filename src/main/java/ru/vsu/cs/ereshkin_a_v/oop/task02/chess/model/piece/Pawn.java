package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
	public Pawn(PieceColor color) {
		super(color, "Pawn", validMoves(color), false);
	}

	private static List<MoveVariant> validMoves(PieceColor color) {
		// TODO Добавить логику чтобы можно было походить и после первого хода кикать из списка доступных ходов вариант с похождением на две клетки
		List<MoveVariant> result = new ArrayList<>();
		if (color == PieceColor.BLACK) {
			result.add(new MoveVariant(0, 1, false));
			result.add(new MoveVariant(0, 2, false));
			result.add(new MoveVariant(1, 1, true));
			result.add(new MoveVariant(-1, 1, true));
		} else {
			result.add(new MoveVariant(0, -1, false));
			result.add(new MoveVariant(0, -2, false));
			result.add(new MoveVariant(1, -1, true));
			result.add(new MoveVariant(-1, -1, true));
		}
		return result;
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♟' : '♙';
	}
}
