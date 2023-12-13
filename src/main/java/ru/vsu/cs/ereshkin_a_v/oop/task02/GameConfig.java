package ru.vsu.cs.ereshkin_a_v.oop.task02;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.PieceColor;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;

public class GameConfig {
	private Player firstPlayer;
	private Player secondPlayer;
	private PieceColor firstPlayerColor;

	public GameConfig(Player firstPlayer, Player secondPlayer, PieceColor firstPlayerColor) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.firstPlayerColor = firstPlayerColor;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public PieceColor getFirstPlayerColor() {
		return firstPlayerColor;
	}

	public void setFirstPlayerColor(PieceColor firstPlayerColor) {
		this.firstPlayerColor = firstPlayerColor;
	}
}
