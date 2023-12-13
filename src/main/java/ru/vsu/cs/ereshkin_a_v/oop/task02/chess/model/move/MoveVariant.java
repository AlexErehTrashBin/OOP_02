package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move;


public class MoveVariant implements Cloneable{
	private int x;
	private int y;

	// TODO Завязано на матрице по-сути
	// TODO x, y -> enum
	public MoveVariant(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public MoveVariant clone() {
		try {
			MoveVariant clone = (MoveVariant) super.clone();
			clone.x = this.x;
			clone.y = this.y;
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}