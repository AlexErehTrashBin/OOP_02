package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.ChessPiece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Слон
 * */
public class Bishop extends ChessPiece {
	public Bishop(PieceColor color) {
		super(PieceType.Bishop, color, validMoves(), true);
	}


	private static List<Move> validMoves() {
		List<Move> result = new ArrayList<>();
		result.add(new Move(1, 1, false, false));
		result.add(new Move(1, -1, false, false));
		result.add(new Move(-1, 1, false, false));
		result.add(new Move(-1, -1, false, false));
		return result;
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♝' : '♗';
	}
}
