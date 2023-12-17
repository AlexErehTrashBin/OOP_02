package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;

import java.util.Deque;

public interface Board {
	Deque<Move> getMoves();
	boolean isFinished();
	Player getCurrentPlayer();
	Player getOpponentPlayer();

	void setFinished(boolean finished);
	boolean isUnderCheck(Player player);
	void setUnderCheck(boolean underCheck, Player player);

	void setCurrentPlayer(PieceColor newPlayer);

	Tile getUpperLeftTile();

	Tile getLowerLeftTile();

	Tile getUpperRightTile();

	Tile getLowerRightTile();

	int getSize();
}