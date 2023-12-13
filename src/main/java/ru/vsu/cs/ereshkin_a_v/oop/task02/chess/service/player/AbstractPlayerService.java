package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider.MoveProviderFactory;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager.MoveManager;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.printer.BoardPrinter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.printer.OutPrinter;

public abstract class AbstractPlayerService implements PlayerService {
	protected final Player player;
	protected final Board board;
	protected final MoveProviderFactory moveServiceFactory;
	protected final TileFinder tileFinder;
	protected final BoardPrinter printer;
	protected final MoveManager moveManager;

	public AbstractPlayerService(MoveManager moveManager, Board board, Player player) {
		this.moveManager = moveManager;
		this.player = player;
		this.board = board;
		this.moveServiceFactory = new MoveProviderFactory(board);
		this.tileFinder = new TileFinderImpl(board);
		this.printer = new OutPrinter(board);
	}

	protected Coordinate getEndCoordinate(Coordinate start, MoveVariant move) {
		return new Coordinate(start.getX() + move.getX(),
				start.getY() + move.getY());
	}

	protected void printBoard() {
		printer.print(board);
	}
}
