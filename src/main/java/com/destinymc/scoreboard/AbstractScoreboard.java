package com.destinymc.scoreboard;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractScoreboard implements Runnable {

	public final Scoreboard scoreboard;
	private final Objective objective;
	public BukkitTask task;
	public int interval;

	private final Map<Integer, ScoreboardLine> boardLines = new HashMap<>();

	public AbstractScoreboard(String displayName, String... lines) {
		this(displayName, 1, lines);
	}

	public AbstractScoreboard(String displayName, int interval, String... lines) {
		Validate.isTrue(lines.length <= 16, "Too many lines!");

		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = scoreboard.registerNewObjective("Mutinies", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		this.interval = interval;

		for (int i = 0; i < 16; i++) {
			ChatColor color = ChatColor.getByChar(Integer.toHexString(i));
			Team team = scoreboard.registerNewTeam("line" + i);
			team.addEntry(color.toString());
			boardLines.put(i, new ScoreboardLine(color, i, team));
		}

		for (int i = 0; i < lines.length; i++) {
			setValue(lines.length - i - 1, lines[i]);
		}
	}

	private ScoreboardLine getLine(int line) {
		return boardLines.getOrDefault(line, null);
	}
	
	protected int getLines() {
		return boardLines.size();
	}

	public void setValue(int line, String value) {
		final ScoreboardLine boardLine = getLine(line);
		Validate.notNull(boardLine, "Unable to find ScoreboardLine with index of " + line + ".");

		objective.getScore(boardLine.getColor().toString()).setScore(line);
		value = ChatColor.translateAlternateColorCodes('&', value);

		if (value.length() <= 16) {
			boardLine.getTeam().setPrefix(ChatColor.translateAlternateColorCodes('&', value));
			if (!boardLine.getTeam().getSuffix().isEmpty()) {
				boardLine.getTeam().setSuffix("");
			}
		}

		else {
			String prefix = value.substring(0, 15);
			String suffix = ChatColor.getLastColors(prefix) + value.substring(15);
			if (suffix.length() > 16) {
				suffix = suffix.substring(0, 15);
			}
			boardLine.getTeam().setPrefix(ChatColor.translateAlternateColorCodes('&', prefix));
			boardLine.getTeam().setSuffix(ChatColor.translateAlternateColorCodes('&', suffix));
		}
	}

	public void removeLine(int line) {
		final ScoreboardLine boardLine = getLine(line);
		if (boardLine == null) {
			System.out.println("Unable to find ScoreboardLine with index of " + line + ".");
			return;
		}
		scoreboard.resetScores(boardLine.getColor().toString());
	}

	public abstract void update();

	@Override
	public void run() {
		update();
	}

}
