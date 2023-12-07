package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.filler.UpperHalfBlackFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.finder.TileFinder;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.finder.TileFinderImpl;

import java.util.ArrayList;
import java.util.List;

public class GraphBoard implements Board {
	private static final int SIZE = 8;
	private final TileFinder tileFinder;
	protected PieceColor currentPlayer;
	protected boolean isFinished;
	private Tile upperLeftTile;
	private int size;
	public static final int GRAPH_NODES = 64;
	public static final int GRAPH_EDGES = 112;
	public GraphBoard(PieceColor startPlayer) {
		this.currentPlayer = startPlayer;
		this.isFinished = false;
		this.tileFinder = new TileFinderImpl(this);
		initializeBoard(SIZE);
		new UpperHalfBlackFiller(this).fill();
	}

	public GraphBoard(PieceColor startPlayer, int size) {
		this.size = size;
		this.currentPlayer = startPlayer;
		this.isFinished = false;
		this.tileFinder = new TileFinderImpl(this);
		initializeBoard(size);
		new UpperHalfBlackFiller(this).fill();
	}

	@Override
	public void setCurrentPlayer(PieceColor newPlayer) {
		currentPlayer = newPlayer;
	}

	@Override
	public PieceColor getCurrentPlayer() {
		return currentPlayer;
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

		upperLeftTile = board[0][0];
	}

	@Override
	public Tile getUpperLeftTile() {
		return upperLeftTile;
	}

	@Override
	public int getSize() {
		return size;
	}
}
