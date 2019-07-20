package com.destinymc.profile;

import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.destinymc.packet.out.WrapperPlayServerPlayerListHeaderFooter;
import com.destinymc.util.C;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created at 19/02/2019
 *
 * @author Luke Bingham
 */
public final class ProfileHandler implements Listener {

    private final JavaPlugin plugin;

    public ProfileHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    protected final void onPlayerPreJoin(AsyncPlayerPreLoginEvent event) {
        if (event.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED)
            return;

        Profile profile = ProfileManager.getProfile(event.getUniqueId());
        if (profile == null) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(C.ITALIC + "There was an issue.. Try logging in again.");
            return;
        }
    }

    @EventHandler
    protected final void onPlayerJoin(PlayerJoinEvent event) {
        WrapperPlayServerPlayerListHeaderFooter packet = new WrapperPlayServerPlayerListHeaderFooter();
        packet.setHeader(WrappedChatComponent.fromText(event.getPlayer().getName()));
        packet.setFooter(WrappedChatComponent.fromText(event.getPlayer().getName()));
        packet.sendPacket(event.getPlayer());
    }

    @EventHandler
    protected final void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player == null)
            return;

        ProfileManager.invalidate(player.getUniqueId());
    }
}
