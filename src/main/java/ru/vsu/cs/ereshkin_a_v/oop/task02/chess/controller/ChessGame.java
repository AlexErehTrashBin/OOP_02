package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller;

import lombok.Getter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.GameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.GraphBoard;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.allteams.AllTeamsService;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.allteams.AllTeamsServiceImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.printer.BoardPrinter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.printer.OutPrinter;

public class ChessGame {
	private final Board board;
	private final BoardPrinter printer;
	@Getter
	private boolean finished;
	private final Team firstPlayer;
	private final Team secondPlayer;
	private final AllTeamsService allTeamsService;
	private final TileFinder tileFinder;

	public ChessGame(GameConfig config) {
		this.firstPlayer = config.getFirstTeam();
		this.secondPlayer = config.getSecondTeam();
		this.board = new GraphBoard(firstPlayer, secondPlayer);
		this.printer = OutPrinter.getInstance();
		this.tileFinder = TileFinderImpl.getInstance();
		this.allTeamsService = new AllTeamsServiceImpl(board);
		this.finished = false;
	}

	public void makeMove() {
		if (finished) return;
		allTeamsService.makeMove();
	}

	public void revertMove() {
		if (board.getMoves().isEmpty()) {
			return;
		}
		Move lastMove = board.getMoves().peekLast();
		assert lastMove != null;
		tileFinder.setPiece(board, lastMove.getStart(), lastMove.getPiece());
		tileFinder.setPiece(board, lastMove.getEnd(), lastMove.getKilledPiece().orElse(null));
		board.getMoves().pollLast();
	}

	public void printCurrentState() {
		printer.print(board);
	}
}
