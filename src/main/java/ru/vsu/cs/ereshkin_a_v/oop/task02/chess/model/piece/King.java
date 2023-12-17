package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;

public class King extends AbstractPiece {
	public King(Player player) {
		super("King", player);
	}



	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♚' : '♔';
	}
}
