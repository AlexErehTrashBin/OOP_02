package ru.vsu.cs.ereshkin_a_v.oop.task02.chess;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.GraphBoard;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.filler.PieceFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.printer.BoardPrinter;

public class ChessGame {
	private final Board board;
	private boolean finished;
	private PieceColor currentPlayer;

	public ChessGame(PieceFiller filler, BoardPrinter printer, PieceColor startColor) {
		board = new GraphBoard(filler, printer, startColor);
		currentPlayer = startColor;
		finished = false;
	}

	/**
	 * @return returns true if move was played, false if move was illegal
	 */
	public boolean playMove(Coordinate from, Coordinate to) {
		return board.playMove(from, to, this::endTurn);
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
		board.print();
	}
}