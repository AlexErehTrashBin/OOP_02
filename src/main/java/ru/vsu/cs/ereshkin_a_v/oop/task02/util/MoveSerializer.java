package ru.vsu.cs.ereshkin_a_v.oop.task02.util;

import com.google.gson.*;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.DefaultMove;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.move.Move;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MoveSerializer implements JsonSerializer<Move>, JsonDeserializer<Move> {
	private final Map<String, Class<? extends Move>> map;

	public MoveSerializer() {
		map = new HashMap<>();
		map.put("DefaultMove", DefaultMove.class);
	}

	@Override
	public Move deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonElement elem = json.getAsJsonObject().get("Move");
		JsonObject object = elem.getAsJsonObject();
		String type = context.deserialize(object.getAsJsonPrimitive("type"), String.class);
		return context.deserialize(elem, map.get(type));
	}

	@Override
	public JsonElement serialize(Move src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		JsonElement elem = null;
		switch (src.getType()) {
			case "DefaultMove" -> {
				DefaultMove move = (DefaultMove) src;
				elem = context.serialize(move);
			}
			default -> System.err.println("Move serialization error!");
		}
		result.add("Move", elem);
		return result;
	}
}
