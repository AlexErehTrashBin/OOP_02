package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;

public class Knight extends AbstractPiece {
	public Knight(Player player) {
		super("Knight", player);
	}


	@Override
	public char getCharValue() {
		return getColor() == PieceColor.BLACK ? '♞' : '♘';
	}
}
