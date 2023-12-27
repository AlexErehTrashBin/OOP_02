package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.GameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.GraphBoard;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.serial.SerialGameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.allteams.AllTeamsService;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.allteams.AllTeamsServiceImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.printer.BoardPrinter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.printer.OutPrinter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.util.MoveSerializer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.util.PieceSerializer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.util.PlayerSerializer;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGame {
	private Board board;
	private BoardPrinter printer;
	private Team firstPlayer;
	private Team secondPlayer;
	private AllTeamsService allTeamsService;
	private TileFinder tileFinder;
	private Gson gson;

	public ChessGame(GameConfig config) {
		this.firstPlayer = config.getFirstTeam();
		this.secondPlayer = config.getSecondTeam();
		this.board = new GraphBoard(firstPlayer, secondPlayer);
		this.printer = OutPrinter.getInstance();
		this.tileFinder = TileFinderImpl.getInstance();
		this.allTeamsService = new AllTeamsServiceImpl(board);
		setupGson();
	}

	public ChessGame(SerialGameConfig config) {
		this.firstPlayer = config.getFirstTeam();
		this.secondPlayer = config.getSecondTeam();
		this.board = new GraphBoard(firstPlayer, secondPlayer, config);
		this.printer = OutPrinter.getInstance();
		this.tileFinder = TileFinderImpl.getInstance();
		this.allTeamsService = new AllTeamsServiceImpl(board);
		setupGson();
	}

	private void setupGson() {
		gson = new GsonBuilder()
				.registerTypeAdapter(Piece.class, new PieceSerializer())
				.registerTypeAdapter(Player.class, new PlayerSerializer())
				.registerTypeAdapter(Move.class, new MoveSerializer())
				.setPrettyPrinting()
				.enableComplexMapKeySerialization()
				.create();
	}

	public void makeMove() {
		if (board.isFinished()) return;
		allTeamsService.makeMove();
	}

	public boolean isFinished() {
		return board.isFinished();
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

	@SneakyThrows
	public void saveToFile() {
		int currentTeam = board.getCurrentTeam() == firstPlayer ? 1 : 2;
		TileFinder tileFinder = TileFinderImpl.getInstance();
		List<Coordinate> whitePiecesCoordinates = tileFinder.getPiecesCoordinates(board, PieceColor.WHITE);
		List<Coordinate> blackPiecesCoordinates = tileFinder.getPiecesCoordinates(board, PieceColor.BLACK);
		List<Coordinate> pieceCoordinates = new ArrayList<>();
		pieceCoordinates.addAll(whitePiecesCoordinates);
		pieceCoordinates.addAll(blackPiecesCoordinates);
		Map<Coordinate, Piece> state = new HashMap<>();
		for (Coordinate coordinate : pieceCoordinates) {
			state.put(coordinate, tileFinder.getPiece(board, coordinate));
		}
		var config = SerialGameConfig.builder()
				.firstTeam(firstPlayer)
				.secondTeam(secondPlayer)
				.currentTeam(currentTeam)
				.isFinished(board.isFinished())
				.checkMap(board.getCheckMap())
				.boardState(state)
				.moves(board.getMoves())
				.size(board.getSize())
				.build();
		var writer = new FileWriter("board.json");
		var gson = new GsonBuilder()
				.registerTypeAdapter(Piece.class, new PieceSerializer())
				.registerTypeAdapter(Player.class, new PlayerSerializer())
				.registerTypeAdapter(Move.class, new MoveSerializer())
				.setPrettyPrinting()
				.enableComplexMapKeySerialization()
				.create();
		writer.write(gson.toJson(config));
		writer.close();
	}

	@SneakyThrows
	public void loadFromFile() {
		SerialGameConfig config = gson.fromJson(Files.readString(Path.of("board.json")), SerialGameConfig.class);
		this.firstPlayer = config.getFirstTeam();
		this.secondPlayer = config.getSecondTeam();
		this.board = new GraphBoard(firstPlayer, secondPlayer, config);
		this.printer = OutPrinter.getInstance();
		this.tileFinder = TileFinderImpl.getInstance();
		this.allTeamsService = new AllTeamsServiceImpl(board);
	}
}
