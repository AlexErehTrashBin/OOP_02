package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager.MoveManager;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider.MoveProvider;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider.MoveProviderFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class BotPlayerService extends AbstractPlayerService {
	private final Random random;
	public BotPlayerService(MoveManager moveManager, Board board, Player player){
		super(moveManager, board, player);
		this.random = new Random();
	}
	@Override
	public void makeMove() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException ignored) {
		}

		Tile tile = getTileWithPieceWithMoves();
		MoveProvider provider = MoveProviderFactory.getInstance().create(board, tile);
		List<MoveVariant> moves = provider.getAvailableMoves();
		MoveVariant moveVariant = moves.get(random.nextInt(0, moves.size()));
		Coordinate start = tile.getCoordinate();
		Coordinate end = getEndCoordinate(start, moveVariant);
		moveManager.playMove(start, end);
	}

	private Tile getTileWithPieceWithMoves() {
		List<Coordinate> coordinates = tileFinder.getPiecesCoordinates(player.getColor());
		AtomicReference<Tile> result = new AtomicReference<>();
		coordinates.forEach(coordinate -> {
			Tile tile = tileFinder.getTile(coordinate);

			if (!MoveProviderFactory.getInstance().create(board, tile).getAvailableMoves().isEmpty()) {
				result.set(tile);
			}
		});
		return result.get();
	}
}
