package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.GameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.serial.SerialGameConfig;

public interface GameController {
	void makeMove();
	boolean isFinished();
	void revertMove();
	void saveToFile();
	void loadFromFile();
	void printCurrentState();
	void loadConfig(GameConfig config);
	void loadConfig(SerialGameConfig config);
}
