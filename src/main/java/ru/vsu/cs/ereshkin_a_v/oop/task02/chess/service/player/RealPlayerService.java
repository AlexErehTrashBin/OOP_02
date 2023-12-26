package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.board.Board;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.tile.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.finder.TileFinderImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.movemanager.MoveManagerImpl;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider.MoveProviderFactory;
import ru.vsu.cs.ereshkin_a_v.oop.task02.console.InputHandler;

import java.util.List;
import java.util.Scanner;

public class RealPlayerService extends AbstractPlayerService {
	private final Scanner scanner;
	private final InputHandler handler;
	public RealPlayerService(Player player) {
		super(player);
		scanner = new Scanner(System.in);
		handler = new InputHandler();
	}
	@Override
	public void makeMove(Board board) {
		while (true) {
			System.out.print("Введите ход (например. A2-A3): ");
			String input = scanner.nextLine();

			if (!handler.isValid(input)) {
				System.out.println("Неверный ввод!");
				System.out.println("Верный ввод в формате: A2-A3");
			} else {
				Coordinate from = handler.getFrom(input);
				Coordinate to = handler.getTo(input);

				Tile fromTile = TileFinderImpl.getInstance().getTile(board, from);
				List<MoveVariant> variants = MoveProviderFactory.getInstance().create(board, fromTile).getAvailableMoves();
				List<Coordinate> coordinates = variants.stream()
						.map(it -> {
							int fromX = fromTile.getCoordinate().getX();
							int fromY = fromTile.getCoordinate().getY();
							int moveX = it.getX();
							int moveY = it.getY();
							int toX = fromX + moveX;
							int toY = fromY + moveY;
							return new Coordinate(toX, toY);
						}).toList();

				if (!coordinates.contains(to)) {
					System.out.println("Недопустимый ход!");
				}
				MoveManagerImpl.getInstance().playMove(board, from, to);
				return;
			}
		}

	}
}
