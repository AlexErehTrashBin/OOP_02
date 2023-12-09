package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;

import java.util.List;

public interface Move {
	void reverse();
	int getSize();
	boolean hasKilledInProcess();
	List<Coordinate> getCoordinates();
}
