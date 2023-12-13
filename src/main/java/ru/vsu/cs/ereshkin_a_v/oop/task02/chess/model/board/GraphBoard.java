package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.filler.StartBoardFiller;

import java.util.ArrayList;
import java.util.List;

public class GraphBoard implements Board {
	private static final int SIZE = 8;
	private Player currentPlayer;
	private final Player firstPlayer;
	private final Player secondPlayer;

	private boolean isFinished;
	private boolean isCheck;
	private Tile upperLeftTile;
	private Tile lowerLeftTile;
	private Tile upperRightTile;
	private Tile lowerRightTile;
	private int size;

	public GraphBoard(Player startPlayer, Player firstPlayer, Player secondPlayer) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.currentPlayer = startPlayer;
		this.isFinished = false;
		initializeBoard(SIZE);
		new StartBoardFiller(this, firstPlayer, secondPlayer, startPlayer.getColor()).fill();
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
	public PieceColor getCurrentPlayer() {
		return currentPlayer.getColor();
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public void setFinished(boolean finished) {
		isFinished = finished;
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
	public boolean isCheck() {
		return isCheck;
	}

	@Override
	public void setCheck(boolean check) {
		isCheck = check;
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
