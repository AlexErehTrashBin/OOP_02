package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;

import java.util.ArrayList;
import java.util.List;

public class NoMoveProvider implements MoveProvider {
	@Override
	public List<MoveVariant> getAvailableMoves() {
		return new ArrayList<>();
	}
}
