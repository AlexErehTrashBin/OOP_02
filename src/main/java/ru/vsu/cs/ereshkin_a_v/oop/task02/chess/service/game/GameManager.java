package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.game;

public interface GameManager {
	void makeMove();
	boolean isFinished();
	void revertMove();
	void saveToFile();
	void loadFromFile();
	void printCurrentState();
}
