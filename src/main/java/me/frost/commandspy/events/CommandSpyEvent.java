package me.frost.commandspy.events;

import me.frost.commandspy.CommandSpy;
import me.frost.commandspy.utils.Formatting;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSpyEvent implements Listener {
    private final CommandSpy plugin;

    public CommandSpyEvent(CommandSpy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Bukkit.getServer().getOnlinePlayers().stream().filter(player -> plugin.commandSpy.contains(player.getUniqueId()))
                .forEach(player -> player.sendMessage(Formatting.colorize(plugin.getConfig().getString("commandspy-format")
                .replace("%player%", event.getPlayer().getName()).replace("%command%", event.getMessage()))));
    }
}