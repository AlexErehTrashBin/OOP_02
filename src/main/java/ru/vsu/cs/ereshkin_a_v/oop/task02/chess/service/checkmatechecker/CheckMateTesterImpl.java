package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.checkmatechecker;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider.MoveProviderFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckMateTesterImpl implements CheckMateTester {
	private static CheckMateTesterImpl instance;
	private final TileFinder tileFinder;

	private CheckMateTesterImpl() {
		tileFinder = TileFinderImpl.getInstance();
	}

	public static CheckMateTesterImpl getInstance() {
		if (instance == null) {
			instance = new CheckMateTesterImpl();
		}
		return instance;
	}

	private boolean checks(Board board, List<Coordinate> selfPieces, Coordinate opponentKingCoordinate) {
		Set<Coordinate> coordinatesBeingChecked = new HashSet<>();
		selfPieces
				.forEach(selfPieceCoordinate -> MoveProviderFactory.getInstance()
						.create(board, tileFinder.getTile(board, selfPieceCoordinate)).getAvailableMoves()
						.forEach(it -> coordinatesBeingChecked.add(selfPieceCoordinate.getSum(it))));
		return coordinatesBeingChecked.contains(opponentKingCoordinate);
	}

	@Override
	public boolean isCheck(Board board, Team player) {
		PieceColor selfColor = player.getColor();
		PieceColor opponentColor = selfColor.getOpponent();
		Coordinate opponentKingCoordinate = tileFinder.getKingLocation(board, opponentColor);

		List<Coordinate> selfPieces = tileFinder.getPiecesCoordinates(board, selfColor);
		return checks(board, selfPieces, opponentKingCoordinate);
	}

	@Override
	public boolean isMate(Board board, Team player, Team underCheckPlayer) {
		if (!board.isUnderCheck(player)) return false;

		PieceColor selfColor = player.getColor();
		Coordinate opponentKingCoordinate = tileFinder.getKingLocation(board, player.getColor());

		List<Coordinate> selfPieces = tileFinder.getPiecesCoordinates(board, selfColor);

		Tile opponentKingTile = tileFinder.getTile(board, opponentKingCoordinate);

		List<Coordinate> afterMoveCoordinatesForKing = new ArrayList<>(MoveProviderFactory.getInstance()
				.create(board, opponentKingTile)
				.getAvailableMoves().stream()
				.map(opponentKingCoordinate::getSum)
				.toList());

		for (Coordinate pieceCoordinate : selfPieces) {
			MoveProviderFactory.getInstance()
					.create(board, tileFinder.getTile(board, pieceCoordinate)).getAvailableMoves()
					.stream()
					.map(pieceCoordinate::getSum)
					.forEach(afterMoveCoordinatesForKing::remove);
		}
		return afterMoveCoordinatesForKing.isEmpty();
	}
}
