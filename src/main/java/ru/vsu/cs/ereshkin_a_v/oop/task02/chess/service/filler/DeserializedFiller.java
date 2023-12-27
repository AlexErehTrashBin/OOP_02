package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler;


import lombok.RequiredArgsConstructor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;

import java.util.Map;

@RequiredArgsConstructor
public class DeserializedFiller implements PieceFiller {
	private final Map<Coordinate, Piece> boardState;
	@Override
	public void fill(Board board, Team firstPlayer, Team secondPlayer) {
		TileFinder tileFinder = TileFinderImpl.getInstance();
		for (Coordinate coordinate : boardState.keySet()) {
			Tile tileToPutPiece = tileFinder.getTile(board, coordinate);
			tileToPutPiece.setPiece(boardState.get(coordinate));
		}
	}
}
