package ru.vsu.cs.ereshkin_a_v.oop.task02.chess;

public class Coordinate {
	private final int x;
	private final int y;

	public Coordinate(int x, int y){
		this.x = x;
		this.y =y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}
