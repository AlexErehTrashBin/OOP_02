package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.checkmatechecker;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider.MoveProviderFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckMateTesterImpl implements CheckMateTester {
	private final Board board;
	private final TileFinder tileFinder;
	public CheckMateTesterImpl(Board board, TileFinder tileFinder) {
		this.board = board;
		this.tileFinder = tileFinder;
	}

	private boolean checks(List<Coordinate> selfPieces, Coordinate opponentKingCoordinate) {
		Set<Coordinate> coordinatesBeingChecked = new HashSet<>();
		selfPieces
				.forEach(selfPieceCoordinate -> new MoveProviderFactory(board)
						.create(tileFinder.getTile(selfPieceCoordinate)).getAvailableMoves()
						.forEach(it -> coordinatesBeingChecked.add(selfPieceCoordinate.getSum(it))));
		return coordinatesBeingChecked.contains(opponentKingCoordinate);
	}
	@Override
	public boolean isCheck(Player player) {
		PieceColor selfColor = player.getColor();
		PieceColor opponentColor = selfColor.getOpponent();
		Coordinate opponentKingCoordinate = tileFinder.getKingLocation(opponentColor);

		List<Coordinate> selfPieces = tileFinder.getPiecesCoordinates(selfColor);
		return checks(selfPieces, opponentKingCoordinate);
	}

	@Override
	public boolean isMate(Player player, Player underCheckPlayer) {
		if (!board.isUnderCheck(player)) return false;

		PieceColor selfColor = player.getColor();
		Coordinate opponentKingCoordinate = tileFinder.getKingLocation(player.getColor());

		List<Coordinate> selfPieces = tileFinder.getPiecesCoordinates(selfColor);

		Tile opponentKingTile = tileFinder.getTile(opponentKingCoordinate);

		List<Coordinate> afterMoveCoordinatesForKing = new ArrayList<>(new MoveProviderFactory(board)
				.create(opponentKingTile)
				.getAvailableMoves().stream()
				.map(opponentKingCoordinate::getSum)
				.toList());

		for (Coordinate pieceCoordinate : selfPieces) {
			new MoveProviderFactory(board)
					.create(tileFinder.getTile(pieceCoordinate)).getAvailableMoves()
					.stream()
					.map(pieceCoordinate::getSum)
					.forEach(afterMoveCoordinatesForKing::remove);
		}
		return afterMoveCoordinatesForKing.isEmpty();
	}
}
