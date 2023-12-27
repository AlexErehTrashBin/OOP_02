package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.Piece;

import java.util.Optional;

@EqualsAndHashCode(callSuper = false)
public class DefaultMove extends AbstractMove{
	@Getter
	private final Coordinate start;
	@Getter
	private final Coordinate end;
	private final Piece killedPiece;
	@Getter
	private final Piece piece;
	public DefaultMove(@NotNull Coordinate start,
	                   @NotNull Coordinate end,
	                   @NotNull Piece piece,
	                   @Nullable Piece killedPiece) {
		super("DefaultMove");
		this.start = start.clone();
		this.end = end.clone();
		this.piece = piece;
		this.killedPiece = killedPiece;
	}

	@Override
	@NotNull
	public Optional<Piece> getKilledPiece() {
		if (killedPiece != null) return Optional.of(killedPiece);
		return Optional.empty();
	}
}
