package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager.MoveManager;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider.MoveProvider;

import java.util.List;
import java.util.Random;

public class BotPlayerService extends AbstractPlayerService {
	private final Random random;
	public BotPlayerService(MoveManager moveManager, Board board, Player player){
		super(moveManager, board, player);
		this.random = new Random();
	}
	@Override
	public void makeMove() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ignored) {
		}
		List<Coordinate> coordinates = tileFinder.getPiecesCoordinates(player.getColor());
		while (true) {
			Coordinate randomPieceCoordinates = coordinates.get(random.nextInt(0, coordinates.size()));
			Tile tile = tileFinder.getTile(randomPieceCoordinates);
			MoveProvider provider = moveServiceFactory.create(tile);
			List<MoveVariant> moves = provider.getAvailableMoves();
			if (moves.isEmpty()) {
				continue;
			}
			MoveVariant moveVariant = moves.get(random.nextInt(0, moves.size()));
			Coordinate start = tile.getCoordinate();
			Coordinate end = getEndCoordinate(start, moveVariant);
			boolean played = moveManager.playMove(start, end);
			if (played) return;
		}
	}
}
