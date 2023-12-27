package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player;

import lombok.EqualsAndHashCode;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;

@EqualsAndHashCode(callSuper = true)
public class RealPlayer extends AbstractPlayer {
	public RealPlayer(String name, PieceColor color) {
		super(name, color, "Real");
	}
}
