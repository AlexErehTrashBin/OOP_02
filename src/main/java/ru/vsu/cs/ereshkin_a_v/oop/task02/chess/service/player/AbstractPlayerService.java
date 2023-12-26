package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;

@Getter
@AllArgsConstructor
public abstract class AbstractPlayerService implements PlayerService {
	protected final Player player;

	protected Coordinate getEndCoordinate(Coordinate start, MoveVariant move) {
		return new Coordinate(start.getX() + move.getX(),
				start.getY() + move.getY());
	}

}
