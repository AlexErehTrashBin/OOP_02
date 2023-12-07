package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.move;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.Coordinate;

import java.util.List;

public interface Move {
	void reverse();
	int getSize();
	boolean hasKilledInProcess();
	List<Coordinate> getCoordinates();
}
