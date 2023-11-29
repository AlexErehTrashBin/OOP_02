package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.ChessPiece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;

import java.util.ArrayList;
import java.util.List;


/**
 * Ладья
 * */
public class Rook extends ChessPiece {
	public Rook(PieceColor color) {
		super(PieceType.Rook, color, validMoves(), true);
	}


	private static List<Move> validMoves() {
		List<Move> result = new ArrayList<>();
		result.add(new Move(1, 0, false, false));
		result.add(new Move(0, 1, false, false));
		result.add(new Move(-1, 0, false, false));
		result.add(new Move(0, -1, false, false));
		return result;
	}
}
