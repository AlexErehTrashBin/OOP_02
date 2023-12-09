package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move;


public class MoveVariant implements Cloneable{
	public int x;
	public int y;

	// TODO Вытащить в пешку и там проверять на первый ход как-то
	public boolean onTakeOnly;

	// TODO Завязано на матрице по-сути
	// TODO x, y -> enum
	public MoveVariant(int x, int y, boolean onTakeOnly) {
		this.x = x;
		this.y = y;
		this.onTakeOnly = onTakeOnly;
	}


	@Override
	public MoveVariant clone() {
		try {
			MoveVariant clone = (MoveVariant) super.clone();
			clone.x = this.x;
			clone.y = this.y;
			clone.onTakeOnly = this.onTakeOnly;
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}