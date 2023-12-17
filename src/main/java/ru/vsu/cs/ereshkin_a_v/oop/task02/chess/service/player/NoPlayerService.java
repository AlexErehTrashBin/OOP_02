package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.player;

public class NoPlayerService implements PlayerService {
	private static NoPlayerService instance;

	private NoPlayerService() {
	}

	public static NoPlayerService getInstance() {
		if (instance == null) {
			instance = new NoPlayerService();
		}
		return instance;
	}

	@Override
	public void makeMove() {
	}
}
