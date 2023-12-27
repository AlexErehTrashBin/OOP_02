package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.serial.SerialGameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.team.Team;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.TileDirections;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler.DeserializedFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler.PieceFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler.StartBoardFiller;

import java.util.*;

public class GraphBoard implements Board {
	private static final int SIZE = 8;
	private Team currentTeam;
	private final Team firstTeam;
	private final Team secondTeam;
	private Map<Team, Boolean> checkMap;
	private boolean isFinished;
	private Tile upperLeftTile;
	private Tile lowerLeftTile;
	private Tile upperRightTile;
	private Tile lowerRightTile;
	private int size;
	private final Deque<Move> moves;

	public GraphBoard(Team firstTeam, Team secondTeam) {
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.currentTeam = firstTeam;
		this.moves = new LinkedList<>();
		this.isFinished = false;
		this.checkMap = new HashMap<>();
		initializeBoard(SIZE);
		PieceFiller filler = StartBoardFiller.getInstance();
		filler.fill(this, firstTeam, secondTeam);
	}

	public GraphBoard(Team firstTeam, Team secondTeam, SerialGameConfig config) {
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.currentTeam = firstTeam;
		this.moves = config.getMoves();
		this.isFinished = config.isFinished();
		this.checkMap = config.getCheckMap();
		initializeBoard(config.getSize());
		PieceFiller filler = new DeserializedFiller(config.getBoardState());
		filler.fill(this, firstTeam, secondTeam);
	}

	@Override
	public void setCurrentTeam(Team team) {
		currentTeam = team;
	}

	private Team getTeamByColor(PieceColor color) {
		if (firstTeam.getColor().equals(color)) return firstTeam;
		return secondTeam;
	}

	@Override
	public Deque<Move> getMoves() {
		return moves;
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public Team getCurrentTeam() {
		return currentTeam;
	}

	@Override
	public Team getOpponentTeam() {
		return currentTeam == firstTeam ? secondTeam : firstTeam;
	}

	@Override
	public Map<Team, Boolean> getCheckMap() {
		return checkMap;
	}

	@Override
	public void setCheckMap(Map<Team, Boolean> map) {
		checkMap = map;
	}

	@Override
	public void setFinished(boolean finished) {
		isFinished = finished;
	}

	@Override
	public boolean isUnderCheck(Team player) {
		return checkMap.getOrDefault(player, false);
	}

	@Override
	public void setUnderCheck(boolean underCheck, Team player) {
		checkMap.put(player, underCheck);
	}

	private void initializeBoard(int size) {
		Tile[][] board = new Tile[size][size];

		// Создание тайлов и установка координат
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				Coordinate coordinate = new Coordinate(col, row);
				board[row][col] = new Tile(coordinate);
			}
		}

		// Установка ссылок между тайлами
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				Tile tile = board[row][col];
				List<Tile> neighbors = new ArrayList<>(TileDirections.DIRECTIONS_COUNT);

				// Установка ссылки вверх
				if (row > 0) {
					neighbors.add(board[row - 1][col]);
				} else {
					neighbors.add(null);
				}

				// Установка ссылки вверх-вправо
				if (row > 0 && col < size - 1) {
					neighbors.add(board[row - 1][col + 1]);
				} else {
					neighbors.add(null);
				}

				// Установка ссылки вправо
				if (col < size - 1) {
					neighbors.add(board[row][col + 1]);
				} else {
					neighbors.add(null);
				}

				// Установка ссылки вниз-вправо
				if (row < size - 1 && col < size - 1) {
					neighbors.add(board[row + 1][col + 1]);
				} else {
					neighbors.add(null);
				}

				// Установка ссылки вниз
				if (row < size - 1) {
					neighbors.add(board[row + 1][col]);
				} else {
					neighbors.add(null);
				}

				// Установка ссылки вниз-влево
				if (row < size - 1 && col > 0) {
					neighbors.add(board[row + 1][col - 1]);
				} else {
					neighbors.add(null);
				}

				// Установка ссылки налево
				if (col > 0) {
					neighbors.add(board[row][col - 1]);
				} else {
					neighbors.add(null);
				}

				// Установка ссылки вверх-влево
				if (row > 0 && col > 0) {
					neighbors.add(board[row - 1][col - 1]);
				} else {
					neighbors.add(null);
				}

				// Установка списка соседей для текущего тайла
				tile.setNeighbors(neighbors);
			}
		}

		this.size = size;
		upperLeftTile = board[0][0];
		lowerLeftTile = board[size - 1][0];
		upperRightTile = board[0][size - 1];
		lowerRightTile = board[size - 1][size - 1];
	}

	@Override
	public Tile getUpperLeftTile() {
		return upperLeftTile;
	}

	@Override
	public Tile getLowerLeftTile() {
		return lowerLeftTile;
	}

	@Override
	public Tile getUpperRightTile() {
		return upperRightTile;
	}

	@Override
	public Tile getLowerRightTile() {
		return lowerRightTile;
	}

	@Override
	public int getSize() {
		return size;
	}
}
