package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;

public abstract class AbstractPlayer implements Player {
	protected final String name;
	protected final PieceColor color;

	public AbstractPlayer(String name, PieceColor color) {
		this.name = name;
		this.color = color;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public PieceColor getColor() {
		return color;
	}
}
