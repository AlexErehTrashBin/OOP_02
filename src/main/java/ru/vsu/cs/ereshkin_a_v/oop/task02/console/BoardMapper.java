package ru.vsu.cs.ereshkin_a_v.oop.task02.console;

public class BoardMapper {
	public BoardMapper(){

	}

	public int map(int val){
		return switch (val) {
			case 1 -> 7;
			case 2 -> 6;
			case 3 -> 5;
			case 4 -> 4;
			case 5 -> 3;
			case 6 -> 2;
			case 7 -> 1;
			case 8 -> 0;
			default -> -1;
		};
	}

	public int map(char val){
		return switch (Character.toLowerCase(val)) {
			case 'a' -> 0;
			case 'b' -> 1;
			case 'c' -> 2;
			case 'd' -> 3;
			case 'e' -> 4;
			case 'f' -> 5;
			case 'g' -> 6;
			case 'h' -> 7;
			default -> -1;
		};
	}
}
