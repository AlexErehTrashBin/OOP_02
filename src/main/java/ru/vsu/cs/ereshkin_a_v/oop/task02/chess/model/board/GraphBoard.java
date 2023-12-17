package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.GameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler.MatePositionFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler.PieceFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler.StartBoardFiller;

import java.util.*;

public class GraphBoard implements Board {
	private static final int SIZE = 8;
	private Player currentPlayer;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final Map<Player, Boolean> checkMap;
	private boolean isFinished;
	private Tile upperLeftTile;
	private Tile lowerLeftTile;
	private Tile upperRightTile;
	private Tile lowerRightTile;
	private int size;
	private final Deque<Move> moves;

	public GraphBoard(Player startPlayer, Player firstPlayer, Player secondPlayer, GameConfig.StartPosition startPosition) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.currentPlayer = startPlayer;
		this.moves = new LinkedList<>();
		this.isFinished = false;
		this.checkMap = new HashMap<>();
		initializeBoard(SIZE);
		PieceFiller filler = StartBoardFiller.getInstance();
		if (startPosition == GameConfig.StartPosition.ALMOST_MATE) {
			filler = MatePositionFiller.getInstance();
		}
		filler.fill(this, firstPlayer, secondPlayer, startPlayer.getColor());
	}

	@Override
	public void setCurrentPlayer(PieceColor newPlayer) {
		currentPlayer = getPlayerByColor(newPlayer);
	}

	private Player getPlayerByColor(PieceColor color) {
		if (firstPlayer.getColor().equals(color)) return firstPlayer;
		return secondPlayer;
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
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public Player getOpponentPlayer() {
		return currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
	}

	@Override
	public void setFinished(boolean finished) {
		isFinished = finished;
	}

	@Override
	public boolean isUnderCheck(Player player) {
		return checkMap.getOrDefault(player, false);
	}

	@Override
	public void setUnderCheck(boolean underCheck, Player player) {
		if (underCheck) {
			System.out.println("Игрок " + player.getName() + " попал под шах!");
		}
		if (!underCheck) {
			System.out.println("Игрок " + player.getName() + " вышел из под шаха!");
		}
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
				List<Tile> neighbors = new ArrayList<>(Tile.DIRECTIONS_COUNT);

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
