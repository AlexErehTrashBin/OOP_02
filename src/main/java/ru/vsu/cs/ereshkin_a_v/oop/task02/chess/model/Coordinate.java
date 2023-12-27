package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;

import java.util.Objects;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Coordinate implements Cloneable {
	private final int x;
	private final int y;

	public Coordinate getSum(MoveVariant other) {
		int newX = this.x + other.getX();
		int newY = this.y + other.getY();
		return new Coordinate(newX, newY);
	}

	@Override
	public Coordinate clone() {
		try {
			return (Coordinate) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	@Override
	public String toString() {
		return "(" + x +
				", " + y +
				')';
	}
}
