package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.ChessPiece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {
	public Knight(PieceColor color) {
		super(PieceType.Knight, color, validMoves(), false);
	}


	private static List<Move> validMoves() {
		List<Move> result = new ArrayList<>();

		result.add(new Move(2, 1, false, false));
		result.add(new Move(1, 2, false, false));
		result.add(new Move(2, -1, false, false));
		result.add(new Move(-1, 2, false, false));
		result.add(new Move(2, -1, false, false));
		result.add(new Move(-1, 2, false, false));
		result.add(new Move(-2, 1, false, false));
		result.add(new Move(1, -2, false, false));
		result.add(new Move(-2, -1, false, false));
		result.add(new Move(-1, -2, false, false));
		result.add(new Move(-2, -1, false, false));
		result.add(new Move(-1, -2, false, false));


		return result;
	}
}
