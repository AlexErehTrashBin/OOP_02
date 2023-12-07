package ru.vsu.cs.ereshkin_a_v.oop.task02.chess;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.GraphBoard;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.finder.TileFinderImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.movemanager.MoveManager;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.movemanager.MoveManagerImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.printer.BoardPrinter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.printer.OutPrinter;

public class ChessGame {
	private final Board board;
	private final BoardPrinter printer;
	private boolean finished;
	private PieceColor currentPlayer;
	private final MoveManager moveManager;

	public ChessGame(PieceColor startColor) {
		board = new GraphBoard(startColor);
		printer = new OutPrinter(board);
		this.moveManager = new MoveManagerImpl(board, new TileFinderImpl(board));
		currentPlayer = startColor;
		finished = false;
	}

	/**
	 * @return returns true if move was played, false if move was illegal
	 */
	public boolean playMove(Coordinate from, Coordinate to) {

		return moveManager.playMove(from, to, this::endTurn);
	}

	private void endTurn() {
		currentPlayer = (currentPlayer == PieceColor.WHITE)
				? PieceColor.BLACK
				: PieceColor.WHITE;
		board.setCurrentPlayer(currentPlayer);
		finished = board.isFinished();
	}

	public boolean isFinished() {
		return finished;
	}

	public void printCurrentState() {
		printer.print(board);
	}
}
