package ru.vsu.cs.ereshkin_a_v.oop.task02;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;

public class GameConfig {
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final PieceColor firstPlayerColor;
	private final StartPosition startPosition;

	public GameConfig(Player firstPlayer, Player secondPlayer, PieceColor firstPlayerColor, StartPosition startPosition) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.firstPlayerColor = firstPlayerColor;
		this.startPosition = startPosition;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public PieceColor getFirstPlayerColor() {
		return firstPlayerColor;
	}

	public StartPosition getStartPosition() {
		return startPosition;
	}

	public enum StartPosition {
		START_POSITION, ALMOST_MATE
	}
}
