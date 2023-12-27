package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;

public interface Player {
	String getName();
	PieceColor getColor();
	String getType();
}
