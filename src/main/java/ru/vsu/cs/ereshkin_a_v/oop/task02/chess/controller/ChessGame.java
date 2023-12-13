package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.GraphBoard;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
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
	private final PlayerServiceFactory playerServiceFactory;
	private Player currentPlayer;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final PlayerService firstPlayerService;
	private final PlayerService secondPlayerService;

	public ChessGame(Player firstPlayer, Player secondPlayer, PieceColor firstPlayerColor) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.currentPlayer = getPlayerByColor(firstPlayerColor);
		this.board = new GraphBoard(currentPlayer, firstPlayer, secondPlayer);
		this.printer = new OutPrinter(board);
		this.moveManager = new MoveManagerImpl(board, new TileFinderImpl(board));
		this.playerServiceFactory = new PlayerServiceFactory(board, moveManager);
		this.firstPlayerService = playerServiceFactory.createPlayerService(firstPlayer);
		this.secondPlayerService = playerServiceFactory.createPlayerService(secondPlayer);
		this.finished = false;
	}

	public void start() {
		while (!isFinished()) {
			if (currentPlayer == firstPlayer) {
				firstPlayerService.makeMove();
			} else {
				secondPlayerService.makeMove();
			}
			endTurn();
		}
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
		printCurrentState();
	}

	public boolean isFinished() {
		return finished;
	}

	public void printCurrentState() {
		printer.print(board);
	}
}
