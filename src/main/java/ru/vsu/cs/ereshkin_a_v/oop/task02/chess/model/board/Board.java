package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;

public interface Board {
	boolean isCheck();

	void setCheck(boolean check);

	boolean isFinished();

	void setFinished(boolean finished);

	PieceColor getCurrentPlayer();

	void setCurrentPlayer(PieceColor newPlayer);

	Tile getUpperLeftTile();

	Tile getLowerLeftTile();

	Tile getUpperRightTile();

	Tile getLowerRightTile();

	int getSize();
}