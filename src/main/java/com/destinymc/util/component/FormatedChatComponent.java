package com.destinymc.util.component;

import com.destinymc.util.C;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public final class FormatedChatComponent {

    private TextComponent text = new TextComponent("");

    /**
     * Create new text with color specified with '&amp;' (like Essentials)
     *
     * @param pText The text to set
     */
    public FormatedChatComponent(String pText) {
        text = new TextComponent(pText);
    }

    /**
     * Send text to player
     *
     * @param player The player to display the text
     */
    public void showTo(Player player) {
        player.sendMessage(C.translate(getFormatText()));
    }

    /**
     * Get text
     *
     * @return The text set in constructor
     */
    public String getFormatText() {

        return text.toLegacyText();
    }
}
