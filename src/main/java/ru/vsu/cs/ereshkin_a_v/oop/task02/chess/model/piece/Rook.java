package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;


/**
 * Ладья
 * */
public class Rook extends AbstractPiece {
	public Rook(Player player) {
		super("Rook", player);
	}

	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♜' : '♖';
	}
}
