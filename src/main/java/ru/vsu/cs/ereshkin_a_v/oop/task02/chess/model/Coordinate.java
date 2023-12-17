package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.MoveVariant;

import java.util.Objects;

public class Coordinate implements Cloneable {
	private final int x;
	private final int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordinate that = (Coordinate) o;
		return getX() == that.getX() && getY() == that.getY();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY());
	}

	@Override
	public String toString() {
		return "(" + x +
				", " + y +
				')';
	}
}
