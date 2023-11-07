package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.ChessPiece;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.filler.PieceFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.printer.BoardPrinter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.pieces.King;

import java.util.ArrayList;
import java.util.List;

public class MatrixBoard extends AbstractBoard implements Board{
	private final Tile[][] board;

	public MatrixBoard(PieceFiller filler, BoardPrinter printer, PieceColor startPlayer) {
		super(startPlayer, printer);
		board = new Tile[8][8];
		initializeBoard();
		filler.fill(this);
	}
	private void initializeBoard(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++) {
				if (j % 2 + i == 0) board[i][j] = new Tile(Tile.TileColor.Black);
				else board[i][j] = new Tile(Tile.TileColor.White);
			}
		}
	}

	@Override
	public List<Coordinate> getPiecesCoordinatesForColor(PieceColor color) {
		List<Coordinate> locations = new ArrayList<>();
		for (int x = 0; x <= 7; x++){
			for (int y = 0; y <= 7; y++){
				if(!board[y][x].isEmpty() && board[y][x].getPiece().getColor() == color) {
					locations.add(new Coordinate(x,y));
				}
			}
		}
		return locations;//allocate new array automatically.
	}

	@Override
	public Coordinate getKingLocation(PieceColor color) {
		Coordinate location = new Coordinate(-1,-1);
		for (int x = 0; x <= 7; x++){
			for (int y = 0; y <= 7 ; y++){
				if (!board[y][x].isEmpty()) {
					Coordinate coordinate = new Coordinate(x, y);
					ChessPiece piece = getPiece(coordinate);
					if (piece.getColor() == color && piece instanceof King){
						location = new Coordinate(x, y);
					}
				}
			}
		}
		return location;
	}

	@Override
	public void setPiece(Coordinate coordinate, ChessPiece piece) {
		board[coordinate.getY()][coordinate.getX()].setPiece(piece);
	}

	@Override
	public ChessPiece getPiece(Coordinate coordinate) {
		return board[coordinate.getY()][coordinate.getX()].getPiece();
	}

	@Override
	public PieceColor getPieceColor(Coordinate coordinate) {
		return board[coordinate.getY()][coordinate.getX()].getPiece().getColor();
	}

	@Override
	public Tile getTile(Coordinate coordinate) {
		return board[coordinate.getY()][coordinate.getX()];
	}

	@Override
	public boolean playMove(Coordinate from, Coordinate to, Runnable endTurn) {
		if (!isValidMove(from, to, false)) return false;
		Tile fromTile = board[from.getY()][from.getX()];
		ChessPiece pieceToMove = fromTile.getPiece();

		Tile toTile = board[to.getY()][to.getX()];
		toTile.setPiece(pieceToMove);

		fromTile.empty();
		endTurn.run();
		return true;
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}
}
