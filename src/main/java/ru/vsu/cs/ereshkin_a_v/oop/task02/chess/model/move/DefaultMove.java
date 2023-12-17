package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;

import java.util.Optional;

public class DefaultMove implements Move{
	private final Coordinate start;
	private final Coordinate end;
	private final Piece killedPiece;
	private final Piece piece;
	public DefaultMove(@NotNull Coordinate start, @NotNull Coordinate end, Piece piece, @Nullable Piece killedPiece) {
		this.start = start.clone();
		this.end = end.clone();
		this.piece = piece;
		this.killedPiece = killedPiece;
	}

	@Override
	public Piece getPiece() {
		return piece;
	}

	@Override
	public Optional<Piece> getKilledPiece() {
		if (killedPiece != null) return Optional.of(killedPiece);
		return Optional.empty();
	}

	@Override
	public Coordinate getStart() {
		return start.clone();
	}

	@Override
	public Coordinate getEnd() {
		return end.clone();
	}
}
