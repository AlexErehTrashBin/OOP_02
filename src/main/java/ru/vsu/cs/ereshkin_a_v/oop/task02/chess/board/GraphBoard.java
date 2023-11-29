package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board;

import com.google.common.graph.*;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Tile;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.filler.PieceFiller;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.board.util.printer.BoardPrinter;

@SuppressWarnings("UnstableApiUsage")
public class GraphBoard extends AbstractBoard implements Board {
	private Graph<Tile> board;
	public static final int GRAPH_NODES = 64;
	public static final int GRAPH_EDGES = 112;
	public GraphBoard(PieceFiller filler, BoardPrinter printer, PieceColor startPlayer) {
		super(startPlayer, printer);
		initializeBoard();
		filler.fill(this);
	}

	private void initializeBoard() {
		ImmutableGraph.Builder<Tile> graph = GraphBuilder
				.undirected()
				.expectedNodeCount(GRAPH_NODES)
				.allowsSelfLoops(false)
				.nodeOrder(ElementOrder.sorted(Tile::compareTo))
				.immutable();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				graph.addNode(new Tile(new Coordinate(j, i)));
			}
		}

		board = graph.build();
	}

	@Override
	public Tile getTile(Coordinate coordinate) {
		for (Tile tile : board.nodes()) {
			if (tile.getCoordinate().equals(coordinate)) return tile;
		}
		return null;
	}
}
