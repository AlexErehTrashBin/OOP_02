package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.ChessPiece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {
	public Pawn(PieceColor color) {
		super(PieceType.Pawn, color, validMoves(color), false);
	}

	private static List<Move> validMoves(PieceColor color) {
		// TODO Добавить логику чтобы можно было походить и после первого хода кикать из списка доступных ходов вариант с похождением на две клетки
		if (color == PieceColor.BLACK) {
			List<Move> result = new ArrayList<>();
			result.add(new Move(0, 1, false, false));
			result.add(new Move(0, 2, true, false));
			result.add(new Move(1, 1, false, true));
			result.add(new Move(-1, 1, false, true));
			return result;
		} else {
			List<Move> result = new ArrayList<>();
			result.add(new Move(0, -1, false, false));
			result.add(new Move(0, -2, true, false));
			result.add(new Move(1, -1, false, true));
			result.add(new Move(-1, -1, false, true));
			return result;
		}
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♟' : '♙';
	}
}
