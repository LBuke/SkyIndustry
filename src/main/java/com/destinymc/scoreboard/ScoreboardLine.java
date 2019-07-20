package com.destinymc.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

public class ScoreboardLine {

	private final ChatColor color; // the chatcolor name of our entry
	private final int line; // the line that the team will be on
	private final Team team; // the actual team itself

	public ScoreboardLine(ChatColor color, int line, Team team) {
		this.color = color;
		this.line = line;
		this.team = team;
	}

	public ChatColor getColor() {
		return color;
	}

	public int getLine() {
		return line;
	}

	public Team getTeam() {
		return team;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[color=" + color.getChar() + ",line=" + line + ",team=" + team.getName() + "]";
	}

}
