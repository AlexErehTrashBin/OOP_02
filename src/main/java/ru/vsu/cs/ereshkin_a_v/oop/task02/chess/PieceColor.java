package ru.vsu.cs.ereshkin_a_v.oop.task02.chess;

public enum PieceColor {
	BLACK, WHITE;
	public PieceColor getOpponent() {
		return (this == PieceColor.BLACK) ? PieceColor.WHITE : PieceColor.BLACK;
	}
}
