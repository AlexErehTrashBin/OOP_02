package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.service.checkmatechecker;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;

public interface CheckMateTester {

	boolean isCheck(Player player);

	boolean isMate(Player player, Player underCheckPlayer);
}
