package ru.vsu.cs.ereshkin_a_v.oop.task02.util;

import com.google.gson.*;
import ru.vsu.cs.ereshkin_a_v.oop.task02.Main;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.piece.*;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.serial.SerialGameConfig;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class PieceSerializer implements JsonSerializer<Piece>, JsonDeserializer<Piece> {
	private Map<String, Class<? extends Piece>> map;

	public PieceSerializer() {
		setMap();
	}

	@Override
	public Piece deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonElement elem = json.getAsJsonObject().get("Piece");
		JsonObject object = elem.getAsJsonObject();
		String pieceType = context.deserialize(object.getAsJsonPrimitive("name"), String.class);
		return context.deserialize(elem, map.get(pieceType));
	}

	@Override
	public JsonElement serialize(Piece src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		JsonElement elem = null;
		switch (src.getName()) {
			case "Pawn" -> {
				Pawn pawn = (Pawn) src;
				elem = context.serialize(pawn);
			}
			case "Bishop" -> {
				Bishop bishop = (Bishop) src;
				elem = context.serialize(bishop);
			}
			case "King" -> {
				King king = (King) src;
				elem = context.serialize(king);
			}
			case "Knight" -> {
				Knight knight = (Knight) src;
				elem = context.serialize(knight);
			}
			case "Queen" -> {
				Queen queen = (Queen) src;
				elem = context.serialize(queen);
			}
			case "Rook" -> {
				Rook rook = (Rook) src;
				elem = context.serialize(rook);
			}
			default -> System.err.println("Piece serialization error!");
		}
		result.add("Piece", elem);
		return result;
	}

	private void setMap() {
		map = new HashMap<>();
		map.put("Pawn", Pawn.class);
		map.put("Bishop", Bishop.class);
		map.put("King", King.class);
		map.put("Knight", Knight.class);
		map.put("Queen", Queen.class);
		map.put("Rook", Rook.class);
	}
}
