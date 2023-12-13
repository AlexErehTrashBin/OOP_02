package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.moveprovider;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;

import java.util.List;

public interface MoveProvider {
	List<MoveVariant> getAvailableMoves();
}
