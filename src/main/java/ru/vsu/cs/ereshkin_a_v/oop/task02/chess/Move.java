package ru.vsu.cs.ereshkin_a_v.oop.task02.chess;


public class Move {
	public final int x;
	public final int y;

	// TODO Вытащить в пешку и там проверять на первый ход как-то
	public final boolean firstMoveOnly;

	// TODO Вытащить в пешку и там проверять на первый ход как-то
	public final boolean onTakeOnly;

	// TODO Завязано на матрице по-сути
	// TODO x, y -> enum
	public Move(int x, int y, boolean firstMoveOnly, boolean onTakeOnly) {
		this.x = x;
		this.y = y;
		this.firstMoveOnly = firstMoveOnly;
		this.onTakeOnly = onTakeOnly;
	}
}

// TODO enum Direction
// TODO subenum AdditionalDirection : Direction
// TODO validMoves -> List<enum>
