package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.controller;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.GameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.serial.SerialGameConfig;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.game.GameManager;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.game.GameManagerImpl;

public class GameControllerImpl implements GameController{
	private GameManager manager;
	@Override
	public void makeMove() {
		manager.makeMove();
	}

	@Override
	public boolean isFinished() {
		return manager.isFinished();
	}

	@Override
	public void revertMove() {
		manager.revertMove();
	}

	@Override
	public void saveToFile() {
		manager.saveToFile();
	}

	@Override
	public void loadFromFile() {
		manager.loadFromFile();
	}

	@Override
	public void printCurrentState() {
		manager.printCurrentState();
	}

	@Override
	public void loadConfig(GameConfig config) {
		manager = new GameManagerImpl(config);
	}

	@Override
	public void loadConfig(SerialGameConfig config) {
		manager = new GameManagerImpl(config);
	}
}
