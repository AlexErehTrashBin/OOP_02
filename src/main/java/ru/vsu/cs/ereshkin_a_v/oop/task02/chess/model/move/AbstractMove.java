package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public abstract class AbstractMove implements Move {
	protected final String type;
}
