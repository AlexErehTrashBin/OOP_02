package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;

public interface Board {
	Tile getUpperLeftTile();

	Tile getLowerLeftTile();

	Tile getUpperRightTile();

	Tile getLowerRightTile();

	int getSize();

	boolean isFinished();

	void setFinished(boolean finished);

	void setCurrentPlayer(PieceColor newPlayer);
	PieceColor getCurrentPlayer();
}