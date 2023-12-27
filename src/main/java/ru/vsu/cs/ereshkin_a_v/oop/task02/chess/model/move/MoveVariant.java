package ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MoveVariant implements Cloneable{
	private int x;
	private int y;

	@Override
	public MoveVariant clone() {
		try {
			MoveVariant clone = (MoveVariant) super.clone();
			clone.x = this.x;
			clone.y = this.y;
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}