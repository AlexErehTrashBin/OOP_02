package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;

public abstract class AbstractPiece implements Piece, Cloneable {
	private Player player;
	private String name;

	protected AbstractPiece(String name, Player player) {
		this.player = player;
		this.name = name;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getName() {
		return name;
	}

	public PieceColor getColor() {
		return player.getColor();
	}

	@Override
	public AbstractPiece clone() {
		try {
			AbstractPiece clone = (AbstractPiece) super.clone();
			clone.player = this.player;
			clone.name = this.name;
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
