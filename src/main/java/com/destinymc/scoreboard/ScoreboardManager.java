package com.destinymc.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardManager {

	private final JavaPlugin PLUGIN;
	private final Map<Player, AbstractScoreboard> SCOREBOARDS = new HashMap<>();

	public ScoreboardManager(JavaPlugin plugin) {
		PLUGIN = plugin;
	}

	public void show(Player player, AbstractScoreboard scoreboard) {
		remove(player);
		player.setScoreboard(scoreboard.scoreboard);
		SCOREBOARDS.put(player, scoreboard);
	}

	public void remove(Player player) {
		if (!SCOREBOARDS.containsKey(player))
			return;

		player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		SCOREBOARDS.remove(player);
	}

	public void unload() {
		SCOREBOARDS.forEach((player, scoreboard) -> {
			player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		});

		SCOREBOARDS.clear();
	}

}
