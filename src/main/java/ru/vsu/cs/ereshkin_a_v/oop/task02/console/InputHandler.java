package ru.vsu.cs.ereshkin_a_v.oop.task02.console;

import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.Coordinate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
	private final static Pattern validMove = Pattern.compile("([a-hA-H][1-8])(-)([a-hA-H][1-8])", Pattern.CASE_INSENSITIVE);
	private final BoardMapper mapper;

	public InputHandler(){
		mapper = new BoardMapper();
	}

	public Coordinate parse(String val){
		int x = mapper.map(val.charAt(0));
		int y = mapper.map(Integer.parseInt(String.valueOf(val.charAt(1))));

		return new Coordinate(x, y);
	}

	public Coordinate getFrom(String val){
		Matcher matcher = validMove.matcher(val);
		matcher.matches();
		String coords = matcher.group(1);

		return parse(coords);
	}

	public Coordinate getTo(String val){
		Matcher matcher = validMove.matcher(val);
		matcher.matches();
		String coords =  matcher.group(3);

		return parse(coords);
	}

	public boolean isValid(String val){
		Matcher matcher = validMove.matcher(val);

		return matcher.matches();
	}
}
