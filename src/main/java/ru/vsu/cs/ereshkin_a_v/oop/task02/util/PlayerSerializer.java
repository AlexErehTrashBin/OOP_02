package ru.vsu.cs.ereshkin_a_v.oop.task02.util;

import com.google.gson.*;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.BotPlayer;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.Player;
import ru.vsu.cs.ereshkin_a_v.oop.task02.chess.model.player.RealPlayer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PlayerSerializer implements JsonSerializer<Player>, JsonDeserializer<Player> {
	private Map<String, Class<? extends Player>> map;
	public PlayerSerializer() {
		setMap();
	}

	private void setMap() {
		map = new HashMap<>();
		map.put("Bot", BotPlayer.class);
		map.put("Real", RealPlayer.class);
	}

	@Override
	public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonElement elem = json.getAsJsonObject().get("Player");
		JsonObject object = elem.getAsJsonObject();
		String type = context.deserialize(object.getAsJsonPrimitive("type"), String.class);
		return context.deserialize(elem, map.get(type));
	}

	@Override
	public JsonElement serialize(Player src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		JsonElement elem = null;
		switch (src.getType()) {
			case "Real" -> {
				RealPlayer player = (RealPlayer) src;
				elem = context.serialize(player);
			}
			case "Bot" -> {
				BotPlayer player = (BotPlayer) src;
				elem = context.serialize(player);
			}
			default -> System.err.println("Player serialization error!");
		}
		result.add("Player", elem);
		return result;
	}
}
