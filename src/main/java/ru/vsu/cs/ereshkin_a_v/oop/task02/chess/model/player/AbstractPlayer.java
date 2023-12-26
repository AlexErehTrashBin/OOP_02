package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;

@Getter
@AllArgsConstructor
public abstract class AbstractPlayer implements Player {
	protected final String name;
	protected final PieceColor color;


	@Override
	public String toString() {
		return "Player(" + "\"" + name + "\"" + ')';
	}
}
