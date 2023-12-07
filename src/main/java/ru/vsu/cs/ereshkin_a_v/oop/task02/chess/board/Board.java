package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Tile;

public interface Board {
	Tile getUpperLeftTile();


	boolean isFinished();

	void setFinished(boolean finished);

	void setCurrentPlayer(PieceColor newPlayer);
	PieceColor getCurrentPlayer();
}