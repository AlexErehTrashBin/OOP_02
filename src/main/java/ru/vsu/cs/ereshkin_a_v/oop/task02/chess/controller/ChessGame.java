package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller;

import ru.vsu.cs.ereshkin_a_v.oop.task02.GameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.GraphBoard;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.checkmatechecker.CheckMateTester;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.checkmatechecker.CheckMateTesterImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager.MoveManager;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager.MoveManagerImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player.PlayerService;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player.PlayerServiceFactory;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.printer.BoardPrinter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.printer.OutPrinter;

public class ChessGame {
	private final Board board;
	private final BoardPrinter printer;
	private boolean finished;
	private final MoveManager moveManager;
	private Player currentPlayer;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final PlayerService firstPlayerService;
	private final PlayerService secondPlayerService;
	private final TileFinder tileFinder;
	private final CheckMateTester checkMateTester;

	public ChessGame(GameConfig config) {
		this.firstPlayer = config.getFirstPlayer();
		this.secondPlayer = config.getSecondPlayer();
		this.currentPlayer = getPlayerByColor(config.getFirstPlayerColor());
		this.board = new GraphBoard(currentPlayer, firstPlayer, secondPlayer, config.getStartPosition());
		this.tileFinder = new TileFinderImpl(board);
		this.checkMateTester = new CheckMateTesterImpl(board, tileFinder);
		this.printer = new OutPrinter(board);
		this.moveManager = new MoveManagerImpl(board, tileFinder, checkMateTester);
		this.firstPlayerService = PlayerServiceFactory.getInstance()
				.createPlayerService(board, moveManager, firstPlayer);
		this.secondPlayerService = PlayerServiceFactory.getInstance()
				.createPlayerService(board, moveManager, secondPlayer);
		this.finished = false;
	}

	public void makeMove() {
		if (finished) return;
		if (currentPlayer == firstPlayer) {
			firstPlayerService.makeMove();
		} else {
			secondPlayerService.makeMove();
		}
		endTurn();
	}

	public void revertMove() {
		if (board.getMoves().isEmpty()) {
			return;
		}
		Move lastMove = board.getMoves().peekLast();
		assert lastMove != null;
		tileFinder.setPiece(lastMove.getStart(), lastMove.getPiece());
		tileFinder.setPiece(lastMove.getEnd(), lastMove.getKilledPiece().orElse(null));
		board.getMoves().pollLast();
	}

	private Player getPlayerByColor(PieceColor color) {
		if (firstPlayer.getColor().equals(color)) return firstPlayer;
		return secondPlayer;
	}

	private void endTurn() {
		currentPlayer = (currentPlayer == firstPlayer)
				? secondPlayer
				: firstPlayer;
		board.setCurrentPlayer(currentPlayer.getColor());
		finished = board.isFinished();
	}

	public boolean isFinished() {
		return finished;
	}

	public void printCurrentState() {
		printer.print(board);
	}
}
