package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;

/**
 * Слон
 * */
public class Bishop extends AbstractPiece {
	public Bishop(Player player) {
		super("Bishop", player);
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♝' : '♗';
	}
}
