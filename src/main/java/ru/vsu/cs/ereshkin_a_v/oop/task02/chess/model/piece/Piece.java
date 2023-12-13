package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;

public interface Piece {

	char getCharValue();

	PieceColor getColor();

	String getName();

	Player getPlayer();

	void setPlayer(Player player);
}
